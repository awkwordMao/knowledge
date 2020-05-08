package com.lzw.authority.service;

import com.lzw.authority.bean.Authority;
import com.lzw.authority.util.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 13
 * @Description:
 */
public class AuthoritySimpleService {
    public AuthoritySimpleService() {
    }

    /**
     * 功能描述: 获取该模块--历史权限数据
     * @param:  * 参数 serviceId
     * @auther: Rick  @date: 2020-05-08 14:20
     */
    public List<Authority> findByServiceId(String serviceId) {
        List<Authority> authorities = new ArrayList();
        /**
         * name 名字
         * code 权限
         * memo 描述
         * type 类型
         * path 请求路径
         * controller 请求控制层
         * method 请求方法类型---GET/POST
         */
        DBHelper db = new DBHelper("select id,service_id as serviceId,name,code,memo,type,path,controller,method from security_authority where service_id='" + serviceId + "'");
        try {
            ResultSet ret = db.pst.executeQuery();
            while(ret.next()) {
                Authority obj = new Authority();
                obj.setId(ret.getString(1));
                obj.setServiceId(ret.getString(2));
                obj.setName(ret.getString(3));
                obj.setCode(ret.getString(4));
                obj.setMemo(ret.getString(5));
                obj.setType(ret.getString(6));
                obj.setPath(ret.getString(7));
                obj.setController(ret.getString(8));
                obj.setMethod(ret.getString(9));
                authorities.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return authorities;
    }
}
