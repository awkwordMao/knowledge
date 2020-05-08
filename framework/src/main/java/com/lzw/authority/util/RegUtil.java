package com.lzw.authority.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 13
 * @Description:
 */
public class RegUtil {
    public RegUtil() {
    }

    public static List<String> getAuthorities(String authorityText) {
        Matcher m = Pattern.compile("hasAuthority\\('([^']*)'\\)").matcher(authorityText);
        ArrayList authorities = new ArrayList();
        while(m.find()) {
            String authority = m.group(1);
            authorities.add(authority);
        }
        return authorities;
    }

    public static String findPackage(String javaContent) {
        Matcher m = Pattern.compile("package\\s+([^;]+)").matcher(javaContent);
        return m.find() ? m.group(1) : null;
    }

    public static void main(String[] args) {
        // 测试
        List<String> authorities = getAuthorities("hasAuthority('administrator') or hasAuthority('sc_area_config_selectAllConfig')");
        for (String authority : authorities) {
            System.out.println(authority);
        }
    }
}
