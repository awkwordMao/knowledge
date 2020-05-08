package com.lzw.authority.bean;

import com.lzw.authority.util.FileUtil;

import java.io.File;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 11
 * @Description:
 */
public class Config {

    private String scanPath;
    private String saveFileDir;
    private String projectName;
    private String serviceCode;
    private String app;
    private String version = "1.0.0";
    private static final String SCAN_PATH_BEFORE;
    private static final String SCAN_PATH_AFTER;
    private static final String insertSql = "insert into security_authority (id,service_id,name,code,memo,path,controller,method,version, type) values ('${id}','${serviceId}','${name}','${code}','${memo}','${path}','${controller}','${method}','${version}', '${type}');";
    private static final String updateSql = "update security_authority set ${serviceId}${type}${name}${memo}${path}${controller}${method} where ${code};";
    private static final String deleteSql = "delete from security_authority where code = '${code}';";

    public Config(String projectName, String packageName) {
        File directory = new File("");
        this.scanPath = directory.getAbsolutePath() + SCAN_PATH_BEFORE + packageName + SCAN_PATH_AFTER;
        this.projectName = projectName;
        this.serviceCode = "ecloud_" + projectName;
        this.app = "/api/" + projectName;
        this.saveFileDir = FileUtil.getFileRootString(directory) + "sql";
        this.print();
    }

    public Config(String projectName, String packageName, String version) {
        File directory = new File("");
        this.scanPath = directory.getAbsolutePath() + SCAN_PATH_BEFORE + packageName + SCAN_PATH_AFTER;
        this.projectName = projectName;
        this.serviceCode = "ecloud_" + projectName;
        this.app = "/api/" + projectName;
        this.saveFileDir = FileUtil.getFileRootString(directory) + "sql";
        this.version = version;
        this.print();
    }

    public Config(String scanPath, String projectName, String packageName, String version) {
        this.scanPath = scanPath;
        this.projectName = projectName;
        this.serviceCode = "ecloud_" + projectName;
        this.app = "/api/" + projectName;
        this.saveFileDir = FileUtil.getFileRootString(new File(scanPath)) + "sql";
        this.version = version;
        this.print();
    }

    public Config(String scanPath, String saveFileDir, String projectName, String packageName, String version) {
        this.scanPath = scanPath;
        this.saveFileDir = saveFileDir;
        this.projectName = projectName;
        this.serviceCode = "ecloud_" + projectName;
        this.app = "/api/" + projectName;
        this.version = version;
        this.print();
    }

    private void print() {
        System.out.println("***************扫描信息*****************");
        System.out.println("项目名称:" + this.projectName);
        System.out.println("项目code:" + this.serviceCode);
        System.out.println("项目Controller路径:" + this.scanPath);
    }

    public String getScanPath() {
        return this.scanPath;
    }

    public String getSaveFileDir() {
        return this.saveFileDir;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getServiceCode() {
        return this.serviceCode;
    }

    public String getApp() {
        return this.app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getInsertSql() {
        return "insert into security_authority (id,service_id,name,code,memo,path,controller,method,version, type) values ('${id}','${serviceId}','${name}','${code}','${memo}','${path}','${controller}','${method}','${version}', '${type}');";
    }

    public String getUpdateSql() {
        return "update security_authority set ${serviceId}${type}${name}${memo}${path}${controller}${method} where ${code};";
    }

    public String getDeleteSql() {
        return "delete from security_authority where code = '${code}';";
    }

    public String getVersion() {
        return this.version;
    }

    static {
        SCAN_PATH_BEFORE = File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "lzw" + File.separator + "microservice" + File.separator;
        SCAN_PATH_AFTER = File.separator + "web" + File.separator + "api";
    }
}
