package com.lzw.authority.util;

import com.lzw.authority.bean.DBSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 13
 * @Description:
 */
public class DBHelper {
    private static DBSource dbSource = new DBSource();
    public Connection conn = null;
    public PreparedStatement pst = null;

    public static void init(DBSource dbSource) {
        DBHelper.dbSource = dbSource;
    }

    public DBHelper(String sql) {
        try {
            Class.forName(dbSource.getName());
            this.conn = DriverManager.getConnection(dbSource.getUrl(), dbSource.getUser(), dbSource.getPassword());
            this.pst = this.conn.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
