package com.zhenshen.ByconfigurationFile.service;

import com.zhenshen.ByconfigurationFile.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;


public class ShiroService {
    /**
     * 获取当前用户
     */
    public static Subject getSubject(){
        //加载配置文件
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //从工厂中获取SecurityManager对象
        SecurityManager instance = factory.getInstance();
        //通过SecurityUtil对象将SecurityManager对象放入全局对象
        SecurityUtils.setSecurityManager(instance);
        //全局对象通过SecurityUtilManager生成subject
        Subject subject = SecurityUtils.getSubject();
        return  subject;
    }

    /**
     * 登录方法
     */
    public static boolean login(User user){
        Subject subject = getSubject();
        //如果用户已经登录 则退出
        if(subject.isAuthenticated()){//判断用户是否登录
            subject.logout();
        }
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPasspord());
        //验证用户数据
        try {
            subject.login(token);
        }catch (Exception e){
            //登录失败
//            e.printStackTrace();
            return  false;
        }
        return subject.isAuthenticated();

    }

    /**
     * 判断用户是否拥有某种角色
     */
    public static boolean hasRole(User user,String role){
        Subject subject = getSubject();
        return  subject.hasRole(role);
    }


    /**
     * 判断用户是否拥有某种权限
     */

    public static boolean isPermit(User user,String permit){
        Subject subject = getSubject();
        return  subject.isPermitted(permit);
    }
}
