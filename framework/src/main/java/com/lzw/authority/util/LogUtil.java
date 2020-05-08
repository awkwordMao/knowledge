package com.lzw.authority.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 13
 * @Description:
 */
public class LogUtil {

    private static String saveFileDir;
    public static String WARNING_DIR_PATH;
    public static String ERROR_DIR_PATH;
    public static String SQL_DIR_PATH;
    public static String INSERT_SQL_DIR_PATH;
    public static String UPDATE_SQL_DIR_PATH;
    public static String DELETE_SQL_DIR_PATH;
    private static Map<String, List<String>> error = new LinkedHashMap();
    private static Map<String, List<String>> info = new LinkedHashMap();
    private static Map<String, List<String>> insertSql = new LinkedHashMap();
    private static Map<String, List<String>> updateSql = new LinkedHashMap();
    private static Map<String, List<String>> deleteSql = new LinkedHashMap();

    private LogUtil() {
    }

    public static void cleanFile() {
        File file = new File(saveFileDir);
        System.out.println("正在清理" + saveFileDir + "下面的文件...");
        FileUtil.deleteFile(file);
        System.out.println("清理完毕");
    }

    private static void initSysProperties(String saveFileDir) {
        LogUtil.saveFileDir = saveFileDir;
        WARNING_DIR_PATH = saveFileDir + File.separator + "warning";
        ERROR_DIR_PATH = saveFileDir + File.separator + "error";
        SQL_DIR_PATH = saveFileDir + File.separator + "sql";
        INSERT_SQL_DIR_PATH = SQL_DIR_PATH + File.separator + "insert";
        UPDATE_SQL_DIR_PATH = SQL_DIR_PATH + File.separator + "update";
        DELETE_SQL_DIR_PATH = SQL_DIR_PATH + File.separator + "delete";
    }

    public static void initFileDir(String saveFileDir) {
        initSysProperties(saveFileDir);
        cleanFile();
        File errorDir = new File(ERROR_DIR_PATH);
        File warningDir = new File(WARNING_DIR_PATH);
        File insertDir = new File(INSERT_SQL_DIR_PATH);
        File updateDir = new File(UPDATE_SQL_DIR_PATH);
        File deleteDir = new File(DELETE_SQL_DIR_PATH);
        if (!errorDir.exists()) {
            errorDir.mkdirs();
        }

        if (!warningDir.exists()) {
            warningDir.mkdirs();
        }

        if (!insertDir.exists()) {
            insertDir.mkdirs();
        }

        if (!updateDir.exists()) {
            updateDir.mkdirs();
        }

        if (!deleteDir.exists()) {
            deleteDir.mkdirs();
        }

    }

    private static void add(Map<String, List<String>> target, String name, String content) {
        if (!target.containsKey(name)) {
            List<String> list = new ArrayList();
            list.add(content);
            target.put(name, list);
        } else {
            List<String> list = (List)target.get(name);
            list.add(content);
            target.put(name, list);
        }

    }

    public static void addError(String name, String content) {
        add(error, name, content);
    }

    public static void addInfo(String name, String content) {
        add(info, name, content);
    }

    public static void addInsertSql(String name, String content) {
        add(insertSql, name, content);
    }

    public static void addUpdateSql(String name, String content) {
        add(updateSql, name, content);
    }

    public static void addDeleteSql(String name, String content) {
        add(deleteSql, name, content);
    }

    public static void writeLog() throws IOException {
        writeErrorLog();
        writeInfoLog();
        writeUpdateSqlLog();
        writeInsertSqlLog();
        writeDeleteSqlLog();
        writeSummarySqlLog();
        System.out.println("生成了" + error.size() + "条错误信息");
        System.out.println("生成了" + info.size() + "条警告信息");
    }

    private static void writeSummarySqlLog() throws IOException {
        File file = new File(saveFileDir + File.separator + "all.sql");
        List<String> list = new ArrayList();
        Iterator var2;
        String name;
        if (insertSql != null && insertSql.size() > 0) {
            list.add("-- 新增的操作码");
            var2 = insertSql.keySet().iterator();

            while(var2.hasNext()) {
                name = (String)var2.next();
                list.add("-- " + name);
                list.addAll((Collection)insertSql.get(name));
            }

            list.add("\r\n\r\n");
        }

        if (updateSql != null && updateSql.size() > 0) {
            list.add("-- 修改的操作码");
            var2 = updateSql.keySet().iterator();

            while(var2.hasNext()) {
                name = (String)var2.next();
                list.add("-- " + name);
                list.addAll((Collection)updateSql.get(name));
            }

            list.add("\r\n\r\n");
        }

        if (deleteSql != null && deleteSql.size() > 0) {
            list.add("-- 废弃的操作码");
            var2 = deleteSql.keySet().iterator();

            while(var2.hasNext()) {
                name = (String)var2.next();
                list.add("-- " + name);
                list.addAll((Collection)deleteSql.get(name));
            }
        }

        writeLog(file, list);
    }

    public static void writeErrorLog() throws IOException {
        if (error != null && error.size() != 0) {
            Iterator var0 = error.keySet().iterator();

            while(var0.hasNext()) {
                String name = (String)var0.next();
                File f = new File(ERROR_DIR_PATH + File.separator + name + ".txt");
                writeLog(f, (List)error.get(name));
            }

        }
    }

    public static void writeInfoLog() throws IOException {
        if (info != null && info.size() != 0) {
            Iterator var0 = info.keySet().iterator();

            while(var0.hasNext()) {
                String name = (String)var0.next();
                File f = new File(WARNING_DIR_PATH + File.separator + name + ".txt");
                writeLog(f, (List)info.get(name));
            }

        }
    }

    public static void writeUpdateSqlLog() throws IOException {
        if (updateSql != null && updateSql.size() != 0) {
            List<String> allUpdateSql = new ArrayList();
            File all = new File(UPDATE_SQL_DIR_PATH + File.separator + "all.sql");
            Iterator var2 = updateSql.keySet().iterator();

            while(var2.hasNext()) {
                String name = (String)var2.next();
                File f = new File(UPDATE_SQL_DIR_PATH + File.separator + name + ".sql");
                writeLog(f, (List)updateSql.get(name));
                allUpdateSql.addAll((Collection)updateSql.get(name));
            }

            writeLog(all, allUpdateSql);
        }
    }

    public static void writeInsertSqlLog() throws IOException {
        if (insertSql != null && insertSql.size() != 0) {
            List<String> allInsertSql = new ArrayList();
            File all = new File(INSERT_SQL_DIR_PATH + File.separator + "all.sql");
            Iterator var2 = insertSql.keySet().iterator();

            while(var2.hasNext()) {
                String name = (String)var2.next();
                File f = new File(INSERT_SQL_DIR_PATH + File.separator + name + ".sql");
                writeLog(f, (List)insertSql.get(name));
                allInsertSql.addAll((Collection)insertSql.get(name));
            }

            writeLog(all, allInsertSql);
        }
    }

    public static void writeDeleteSqlLog() throws IOException {
        if (deleteSql != null && deleteSql.size() != 0) {
            List<String> allDeleteSql = new ArrayList();
            File all = new File(DELETE_SQL_DIR_PATH + File.separator + "all.sql");
            Iterator var2 = deleteSql.keySet().iterator();

            while(var2.hasNext()) {
                String name = (String)var2.next();
                File f = new File(DELETE_SQL_DIR_PATH + File.separator + name + ".sql");
                writeLog(f, (List)deleteSql.get(name));
                allDeleteSql.addAll((Collection)deleteSql.get(name));
            }

            writeLog(all, allDeleteSql);
        }
    }

    private static void writeLog(File file, List<String> contents) throws IOException {
        FileUtil.saveFile(file, contents);
    }
}
