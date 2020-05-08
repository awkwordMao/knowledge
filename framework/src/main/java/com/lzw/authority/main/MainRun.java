package com.lzw.authority.main;

import com.lzw.authority.bean.Authority;
import com.lzw.authority.bean.AuthorityObjectInfo;
import com.lzw.authority.bean.Config;
import com.lzw.authority.bean.DBSource;
import com.lzw.authority.service.AuthoritySimpleService;
import com.lzw.authority.util.DBHelper;
import com.lzw.authority.util.FileUtil;
import com.lzw.authority.util.LogUtil;
import com.lzw.authority.util.RegUtil;
import com.lzw.authority.whitelist.AuthorityWhiteListConfig;
import com.lzw.authority.whitelist.IAuthorityWhiteList;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 11
 * @Description:
 */
@Slf4j
public class MainRun {

    private static IAuthorityWhiteList authorityWhiteListConfig = new AuthorityWhiteListConfig();

    public static void run(DBSource dbSource, Config config) throws IOException, ClassNotFoundException {
        DBHelper.init(dbSource);
        List<File> files = FileUtil.getJavaFile(config.getScanPath());
        validateAuthorityWhiteList(files);
        String insertTemplate = config.getInsertSql();
        String updateTemplate = config.getUpdateSql();
        String deleteTemplate = config.getDeleteSql();
        LogUtil.initFileDir(config.getSaveFileDir());
        Map<String, Authority> dbAuthorityMap = getAuthorities(config.getServiceCode());
        List<String> authorityHistory = new ArrayList();
        for (File file : files) {
            String className = getClassName(file.getName());
            Class<?> c = loadClass(file);
            Method[] methods = c.getMethods();
            int length = methods.length;
            for (int i = 0; i < length; i++) {
                Method method = methods[0];
                RequestMapping rm = (RequestMapping) AnnotationUtils.findAnnotation(method, RequestMapping.class);
                PreAuthorize pa = (PreAuthorize) AnnotationUtils.findAnnotation(method, PreAuthorize.class);
                ApiOperation api = (ApiOperation) AnnotationUtils.findAnnotation(method, ApiOperation.class);
//                AuthorizationLevel authorizationLevel = (AuthorizationLevel)AnnotationUtils.findAnnotation(method, AuthorizationLevel.class);
                if (rm != null) {
                    if (pa != null) {
                        LogUtil.addInfo(className, method.getName() + "未添加操作码");
                    }else {
                        // 默认类型
                        String type = "1";
//                        if (authorizationLevel != null) {
//                            type = authorizationLevel.value();
//                        }
                        List<String> authorities = RegUtil.getAuthorities(pa.value());
                        if (authorities.size() == 0) {
                            LogUtil.addError(className, method.getName() + "获取操作码失败!");
                        } else if (!authorities.contains("administrator") && !authorities.contains("parent") && !authorities.contains("teacher") && !authorities.contains("student")) {
                            LogUtil.addError(className, method.getName() + "缺少" + "administrator" + "操作码");
                        } else {
                            authorities.remove("administrator");
                            authorities.remove("parent");
                            authorities.remove("teacher");
                            authorities.remove("student");
                            RequestMethod[] rms = rm.method();
                            String[] paths = rm.value();
                            if (ObjectUtils.isEmpty(paths)) {
                                List<String> _paths = new ArrayList();
                                GetMapping getMapping = (GetMapping) AnnotationUtils.findAnnotation(method, GetMapping.class);
                                if (getMapping != null && !ObjectUtils.isEmpty(getMapping.value())) {
                                    String[] values = getMapping.value();
                                    _paths.addAll(Arrays.asList(values));
                                }

                                PutMapping putMapping = (PutMapping) AnnotationUtils.findAnnotation(method, PutMapping.class);
                                if (putMapping != null && !ObjectUtils.isEmpty(putMapping.value())) {
                                    String[] values = putMapping.value();
                                    _paths.addAll(Arrays.asList(values));
                                }

                                PostMapping postMapping = (PostMapping) AnnotationUtils.findAnnotation(method, PostMapping.class);
                                if (postMapping != null && !ObjectUtils.isEmpty(postMapping.value())) {
                                    String[] values = postMapping.value();
                                    _paths.addAll(Arrays.asList(values));
                                }

                                DeleteMapping delMapping = (DeleteMapping) AnnotationUtils.findAnnotation(method, DeleteMapping.class);
                                if (delMapping != null && !ObjectUtils.isEmpty(delMapping.value())) {
                                    String[] values = delMapping.value();
                                    _paths.addAll(Arrays.asList(values));
                                }

                                paths = (String[]) _paths.toArray(new String[_paths.size()]);
                            }
                            for (String authority : authorities) {
                                int length1 = rms.length;
                                for (int j = 0; j < length1; j++) {
                                    RequestMethod requestMethod = rms[j];
                                    int length2 = paths.length;
                                    for (int k = 0; k < length2; k++) {
                                        String path = paths[k];
                                        if (getAuthorityWhiteListConfig().hasWhiteList(authority)) {
                                            int index = getAuthorityWhiteListConfig().isWhiteList(authority, className, method.getName());
                                            if (index > 0) {
                                                continue;
                                            }
                                            if (index < 0) {
                                                throw new RuntimeException(className + "." + method.getName() + "的操作码'" + authority + "'未在白名单中,出现了重复!!!");
                                            }
                                            if (index != 0 || authorityHistory.contains(authority)) {
                                                throw new RuntimeException(authority + "操作码重复!!!");
                                            }
                                            authorityHistory.add(authority);
                                        } else {
                                            if (authorityHistory.contains(authority)) {
                                                throw new RuntimeException(authority + "操作码重复!!!");
                                            }
                                            authorityHistory.add(authority);
                                        }

                                        if (!path.startsWith("/")) {
                                            path = "/" + path;
                                            LogUtil.addInfo(className, method.getName() + "访问路径缺少‘/’");
                                        }
                                        if (!dbAuthorityMap.containsKey(authority)) {
                                            String sql = insertTemplate.replace("${id}", UUID.randomUUID().toString()).replace("${serviceId}", config.getServiceCode()).replace("${name}", api != null ? api.value() : "").replace("${code}", authority).replace("${memo}", api != null && api.notes() != null ? api.notes().replace("'", "\"") : "").replace("${path}", config.getApp() + path).replace("${controller}", className).replace("${method}", requestMethod.name()).replace("${version}", config.getVersion()).replace("${type}", type);
                                            LogUtil.addInsertSql(className, sql);
                                        } else {
                                            Authority auth = new Authority();
                                            auth.setCode(authority);
                                            auth.setController(className);
                                            auth.setMemo(api != null && api.notes() != null ? api.notes().replace("'", "\"") : "");
                                            auth.setMethod(requestMethod.name());
                                            auth.setName(api != null ? api.value() : "");
                                            auth.setType(type);
                                            auth.setVersion(config.getVersion());
                                            auth.setPath(config.getApp() + path);
                                            auth.setServiceId(config.getServiceCode());
                                            Authority dbAuth = (Authority) dbAuthorityMap.get(authority);
                                            Map<String, String> contrastFields = dbAuth.contrast(auth);
                                            if (contrastFields != null && contrastFields.size() > 0) {
                                                String target = updateTemplate.replaceAll("\\$\\{code\\}", "code = '" + auth.getCode() + "'");
                                                int l = 1;
                                                for (Map.Entry<String, String> entry : contrastFields.entrySet()) {
                                                    l++;
                                                    String key = entry.getKey();
                                                    Method m = ReflectionUtils.findMethod(auth.getClass(), getMethodName(key));
                                                    target = target.replaceAll("\\$\\{" + key + "\\}", (String) contrastFields.get(key) + " = '" +
                                                            ReflectionUtils.invokeMethod(m, auth).toString() + "'" + (l < contrastFields.size() ? "," : ""));
                                                }
                                                target = target.replaceAll("\\$\\{[^\\}]+\\}", "");
                                                LogUtil.addUpdateSql(className, target);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (String code : authorityHistory) {
            dbAuthorityMap.remove(code);
        }
        if(dbAuthorityMap.size()>0){
            for (String code : dbAuthorityMap.keySet()) {
                LogUtil.addDeleteSql(((Authority)dbAuthorityMap.get(code)).getController(), deleteTemplate.replace("${code}", code));
            }
        }
        LogUtil.writeLog();
        log.info("完成,文件已生成" + config.getSaveFileDir());
    }

    public static void setAuthorityWhiteListConfig(IAuthorityWhiteList authorityWhiteList) {
        if (null != authorityWhiteListConfig) {
            MainRun.authorityWhiteListConfig = authorityWhiteListConfig;
        } else {
            MainRun.authorityWhiteListConfig = new AuthorityWhiteListConfig();
        }
    }

    // 获取历史数据
    public static Map<String, Authority> getAuthorities(String serviceCode) {
        AuthoritySimpleService authoritySimpleService = new AuthoritySimpleService();
        List<Authority> dbAuthorities = authoritySimpleService.findByServiceId(serviceCode);
        Map<String, Authority> map = new HashMap();
        if (!CollectionUtils.isEmpty(dbAuthorities)) {
            map = dbAuthorities.stream().collect(Collectors.toMap(Authority::getCode, s -> s));
        }
        return map;
    }

    // 校验白名单
    private static void validateAuthorityWhiteList(List<File> files) throws ClassNotFoundException {
        if (getAuthorityWhiteListConfig().size() > 0) {
            Map<String, Class<?>> classMap = new HashMap();
            for (File file : files) {
                String className = getClassName(file.getName());
                Class<?> c = loadClass(file);
                classMap.put(className, c);
            }
            boolean flag = true;
            log.info("************白名单功能已启用**************");
            Map<String, List<AuthorityObjectInfo>> map = authorityWhiteListConfig.getAuthorityObjectInfo();
            for (String authority : map.keySet()) {
                List<AuthorityObjectInfo> aoiList = (List)map.get(authority);
                log.info("白名单：{}"+authority);
                for (AuthorityObjectInfo authorityObjectInfo : aoiList) {
                    if(authorityObjectInfo==null){
                        continue;
                    }
                    String objectMethod = authorityObjectInfo.getObjectName() + "." + authorityObjectInfo.getMethodName() + "()\t";
                    if (classMap.containsKey(authorityObjectInfo.getObjectName())) {
                        Class<?> c = (Class)classMap.get(authorityObjectInfo.getObjectName());
                        Method[] methods = c.getMethods();
                        boolean f = false;
                        for(int i = 0; i < methods.length; ++i) {
                            Method method = methods[i];
                            if (method.getName().equals(authorityObjectInfo.getMethodName())) {
                                RequestMapping rm = (RequestMapping) AnnotationUtils.findAnnotation(method, RequestMapping.class);
                                PreAuthorize pa = (PreAuthorize)AnnotationUtils.findAnnotation(method, PreAuthorize.class);
                                if (rm != null && pa != null) {
                                    List<String> authorities = RegUtil.getAuthorities(pa.value());
                                    if (authorities.contains(authority)) {
                                        f = true;
                                        log.info("√  {} 验证成功"+ objectMethod);
                                        break;
                                    }
                                }
                            }
                        }
                        if (!f) {
                            log.info("× " + objectMethod + "未找到" + authorityObjectInfo.getMethodName() + "方法或者未在方法上找到'" + authority + "'操作码");
                            flag = false;
                        }
                    } else {
                        log.info("× " + objectMethod + "未找到" + authorityObjectInfo.getObjectName() + ".java");
                        flag = false;
                    }
                }
            }
            if (!flag) {
                throw new RuntimeException("白名单设置出现异常,请检查无误后再次生成操作码!!!(在控制台的白名单输出中将所有‘×’的按照提示进行检查)");
            }
        }
    }

    public static String getPackagePath(File javaFile) {
        String javaContent = FileUtil.readFileByChars(javaFile);
        String fileName = javaFile.getName();
        String className = getClassName(fileName);
        String packagePath = RegUtil.findPackage(javaContent);
        return null != packagePath ? packagePath + "." + className : null;
    }

    public static String getClassName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static Class<?> loadClass(File javaFile) throws ClassNotFoundException {
        return loadClass(getPackagePath(javaFile));
    }

    public static Class<?> loadClass(String packageName) throws ClassNotFoundException {
        if (StringUtils.isBlank(packageName)) {
            throw new NullPointerException();
        } else {
            return Class.forName(packageName);
        }
    }

    public static String setMethodName(String name) {
        return "set" + toLowerCaseFirstOne(name);
    }

    public static String getMethodName(String name) {
        return "get" + toLowerCaseFirstOne(name);
    }

    public static String toLowerCaseFirstOne(String str) {
        if (null != str && !"".equals(str)) {
            return str.length() == 1 ? str.toUpperCase() : Character.toUpperCase(str.charAt(0)) + str.substring(1);
        } else {
            return "";
        }
    }

    public static IAuthorityWhiteList getAuthorityWhiteListConfig() {
        return authorityWhiteListConfig;
    }
}
