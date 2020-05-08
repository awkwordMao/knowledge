package com.lzw.authority.whitelist;

import com.lzw.authority.bean.AuthorityObjectInfo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 13
 * @Description:
 */
public interface IAuthorityWhiteList {
    boolean addWhiteList(String var1, String var2, String var3);

    int isWhiteList(String var1, String var2, String var3);

    boolean hasWhiteList(String var1);

    List<AuthorityObjectInfo> getAuthorityObjectInfo(String var1);

    AuthorityObjectInfo getAuthorityObjectInfo(String var1, int var2);

    int size();

    Map<String, List<AuthorityObjectInfo>> getAuthorityObjectInfo();
}
