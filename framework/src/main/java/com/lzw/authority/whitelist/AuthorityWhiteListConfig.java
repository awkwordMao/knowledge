package com.lzw.authority.whitelist;

import com.lzw.authority.bean.AuthorityObjectInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 13
 * @Description:
 */

public class AuthorityWhiteListConfig implements IAuthorityWhiteList {
    private Map<String, List<AuthorityObjectInfo>> map = new LinkedHashMap();

    public boolean addWhiteList(String code, String objectName, String methodName) {
        if (!this.hasWhiteList(code)) {
            this.map.put(code, new ArrayList());
        }

        if (this.isWhiteList(code, objectName, methodName) == -1) {
            List<AuthorityObjectInfo> list = (List)this.map.get(code);
            list.add(new AuthorityObjectInfo(objectName, methodName));
            return true;
        } else {
            System.out.println("警告:重复的白名单数据[" + code + "," + objectName + "," + methodName + "]");
            return false;
        }
    }

    public int isWhiteList(String code, String objectName, String methodName) {
        if (!this.hasWhiteList(code)) {
            return -1;
        } else {
            AuthorityObjectInfo aoi = new AuthorityObjectInfo(objectName, methodName);
            List<AuthorityObjectInfo> list = (List)this.map.get(code);
            return list != null ? list.indexOf(aoi) : -1;
        }
    }

    public AuthorityObjectInfo getAuthorityObjectInfo(String code, int index) {
        if (!this.hasWhiteList(code)) {
            throw new RuntimeException("未找到'" + code + "'的白名单");
        } else {
            List<AuthorityObjectInfo> list = (List)this.map.get(code);
            if (list != null) {
                return (AuthorityObjectInfo)list.get(index);
            } else {
                throw new RuntimeException("未找到'" + code + "'的白名单");
            }
        }
    }

    public boolean hasWhiteList(String code) {
        return this.map.containsKey(code);
    }

    public List<AuthorityObjectInfo> getAuthorityObjectInfo(String code) {
        return (List)(this.hasWhiteList(code) ? (List)this.map.get(code) : new ArrayList());
    }

    public int size() {
        return this.map.size();
    }

    public Map<String, List<AuthorityObjectInfo>> getAuthorityObjectInfo() {
        return this.map;
    }
}

