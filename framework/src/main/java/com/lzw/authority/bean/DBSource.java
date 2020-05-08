package com.lzw.authority.bean;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 11
 * @Description:
 */
public class DBSource {

    private String ip = "localhost";
    private Integer port = 3306;
    private String db = "lzw";
    private String url;
    private String name;
    private String user;
    private String password;

    public DBSource() {
        this.url = "jdbc:mysql://" + this.ip + "/" + this.db;
        this.name = "com.mysql.jdbc.Driver";
        this.user = "van";
        this.password = "zengli@92";
    }

    public DBSource(String ip, String db) {
        this.url = "jdbc:mysql://" + this.ip + "/" + this.db;
        this.name = "com.mysql.jdbc.Driver";
        this.user = "van";
        this.password = "zengli@92";
        this.ip = ip;
        this.db = db;
        this.url = "jdbc:mysql://" + ip + ":" + this.port + "/" + db + "?useUnicode=true&amp;characterEncoding=utf8";
    }

    public DBSource(String ip, String db, String user, String password) {
        this.url = "jdbc:mysql://" + this.ip + "/" + this.db;
        this.name = "com.mysql.jdbc.Driver";
        this.user = "van";
        this.password = "zengli@92";
        this.ip = ip;
        this.db = db;
        this.user = user;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + this.port + "/" + db + "?useUnicode=true&amp;characterEncoding=utf8";
    }

    public DBSource(String ip, Integer port, String db, String user, String password) {
        this.url = "jdbc:mysql://" + this.ip + "/" + this.db;
        this.name = "com.mysql.jdbc.Driver";
        this.user = "van";
        this.password = "zengli@92";
        this.ip = ip;
        this.port = port;
        this.db = db;
        this.user = user;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + db + "?useUnicode=true&amp;characterEncoding=utf8";
    }

    public String getIp() {
        return this.ip;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }
}
