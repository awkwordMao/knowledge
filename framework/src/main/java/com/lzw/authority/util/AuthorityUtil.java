package com.lzw.authority.util;

import com.lzw.authority.bean.Config;
import com.lzw.authority.bean.DBSource;
import com.lzw.authority.main.MainRun;
import com.lzw.authority.whitelist.IAuthorityWhiteList;

import java.io.IOException;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 11
 * @Description:
 */
public class AuthorityUtil {

    public static void start(DBSource source, Config config) throws ClassNotFoundException, IOException {
        MainRun.run(source, config);
    }

    public static void start(DBSource source, Config config, IAuthorityWhiteList authorityWhiteList) throws ClassNotFoundException, IOException {
        MainRun.setAuthorityWhiteListConfig(authorityWhiteList);
        MainRun.run(source, config);
    }
}
