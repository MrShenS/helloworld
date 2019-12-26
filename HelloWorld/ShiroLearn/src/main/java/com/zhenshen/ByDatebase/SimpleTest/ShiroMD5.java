package com.zhenshen.ByDatebase.SimpleTest;

import com.zhenshen.ByDatebase.bean.User;
import com.zhenshen.ByDatebase.dao.ShiroDao;

import static com.zhenshen.ByDatebase.SimpleTest.ShiroTest.login;

/**
 * @ClassNamexx
 * @Description TODO
 * @Author zhen.zhen
 * @Date 2019/12/1821:17
 * @Version 1.0
 */
public class ShiroMD5 {
    public static void main(String[] args) {
        ShiroDao.registerUser("Object2", "321321");
        User object2 = new User();
        object2.setName("Object2");
        object2.setPassword("321321");
        if (login(object2)) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
    }
}
