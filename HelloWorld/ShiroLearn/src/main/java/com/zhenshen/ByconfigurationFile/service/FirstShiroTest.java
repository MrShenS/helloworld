package com.zhenshen.ByconfigurationFile.service;

import com.zhenshen.ByconfigurationFile.bean.User;

import java.util.LinkedList;

public class FirstShiroTest {

    public static void main(String[] args) {
        //创建用户 Object
        User object = new User();
        object.setName("Object");
        object.setPasspord("123456");
        //创建用户 reader
        User reader = new User();
        reader.setName("Reader");
        // 密码错误
        reader.setPasspord("654321");
        //设置未注册的用户
        User tom = new User();
        tom.setPasspord("123");
        tom.setName("shenzhen");
        //将用户添加到集合
        LinkedList<User> users = new LinkedList<>();
        users.add(object);
        users.add(tom);
        users.add(reader);
        //设置角色：
        String blogManager="BlogManager";
        String simpleReader="SimpleReader";
        //将角色条件到对应集合
        LinkedList<String> roles = new LinkedList<>();
        roles.add(blogManager);
        roles.add(simpleReader);
        //设置权限
        String addBlog="addBlog";
        String deleteBlog="deleteBlog";
        String modifyBlog="modifyBlog";
        String readBlog="readBlog";
        String commentBlog="commentBlog";
        //将权限添加的数据组权限集合里面
        LinkedList<String> permits = new LinkedList<>();
        permits.add(addBlog);
        permits.add(deleteBlog);
        permits.add(modifyBlog);
        permits.add(readBlog);
        permits.add(commentBlog);
        /******************************************开始测试*********************************/
        System.out.println("=======================验证用户是否登录成功=========================================================");
        for (User user:users) {
            if (ShiroService.login(user)){
                System.out.println("用户登录成功"+user.getName()+":"+user.getPasspord());
            }else{
                System.out.println("用户登录=== 失败"+user.getName()+":"+user.getPasspord());
            }
        }
        /*************************开始验证用户权限**********************/

        System.out.println("===============================开始验证用户角色=====================================================================");
        for (User u:users) {
            for (String role:roles) {
                if(ShiroService.login(u)){
                if (ShiroService.hasRole(u,role)){
                    System.out.println(u.getName()+"是"+role+"角色");
                }
            }
            }
        }
        System.out.println("=======================================验证用户权限=====================================================================");
        for (User user:users) {
                System.out.println("========================"+user.getName()+"权限=====================================================");
                for (String p:permits) {
                    if (ShiroService.login(user)){
                        if(ShiroService.isPermit(user,p)){
                    System.out.println(user.getName()+"用户具有"+p+"权限");
                }
                }
            }
        }




    }
}
