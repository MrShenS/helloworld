package com.zhenshen.ByDatebase.SimpleTest;

import com.zhenshen.ByDatebase.bean.User;
import com.zhenshen.ByDatebase.dao.ShiroDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassNameShiroTest
 * @Description TODO
 * @Author zhen.zhen
 * @Date 2019/12/189:59
 * @Version 1.0
 */
public class ShiroTest {
    public static void main(String[] args) {
        // 用户Object
        User object = new User();
        object.setName("Object");
        object.setPassword("123456");

        // 用户Reader
        User reader = new User();
        reader.setName("Reader");
        // 错误的密码
        reader.setPassword("654321");

        // 不存在的用户
        User tom = new User();
        tom.setName("Tom");
        tom.setPassword("123456");

        List<User> users = new LinkedList<User>();
        users.add(object);
        users.add(reader);
        users.add(tom);

        // 角色：BlogManager
        String blogManager = "blogManager";
        // 角色：SimpleReader
        String simpleReader = "reader";

        List<String> roles = new LinkedList<String>();
        roles.add(blogManager);
        roles.add(simpleReader);

        // 权限
        String addBlog = "addBlog";
        String deleteBlog = "deleteBlog";
        String modifyBlog = "modifyBlog";
        String readBlog = "readBlog";
        String commentBlog = "commentBlog";
        List<String> permits = new LinkedList<String>();
        permits.add(addBlog);
        permits.add(deleteBlog);
        permits.add(modifyBlog);
        permits.add(readBlog);
        permits.add(commentBlog);

        /*********开始验证******/
        System.out.println("===========================开始验证=========================================");
        //验证用户是否登录
        for (User user:users) {
            System.out.println(user.getName()+":"+ShiroDao.getPassword(user.getName()));
            if(login(user)){
                System.out.println("用户登录成功"+user.getName()+":"+user.getPassword());

            }else {
                System.out.println("用户登录失败"+user.getName()+":"+user.getPassword());
            }

        }
        //检查用户角色
        System.out.println("================================用户角色验证============================================");
        for (User user:users){
            if (login(user)){
                for (String role: roles) {
                    if(hasRole(user,role)){
                        System.out.println(user.getName()+"具有"+role+"角色");
                    }
                }
            }
        }

        //检查用户权限
        System.out.println("===================================用户权限验证=======================================================");
        for (User user : users) {
            if(login(user)){
                System.out.println("用户"+user.getName()+"具有权限如下");
                for (String permit: permits) {
                    if(isPermitted(user,permit)){
                        System.out.println("用户"+user.getName()+"具有"+permit+"权限");
                    }
                }
            }
        }




    }

/**
 * @Author zhen.shen
 * @Description //TODO 获取Subject对象
 * @Date 10:28 2019/12/18
 * @Param
 * @return
 **/
    public static Subject getSubject(){
        //创建生产工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:DbShiro.ini");
        //通过工厂获取安全管理者实例
        SecurityManager instance = factory.getInstance();
        //将安全管理者放入全局对象中
        SecurityUtils.setSecurityManager(instance);
        //全局对象通过安全管理员获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        return  subject;
    }
/**
 * @Author zhen.shen
 * @Description //TODO 验证用户登录
 * @Date 10:29 2019/12/18
 * @Param
 * @return
 **/
    public static boolean login(User user){
        //获取Subject对象
        Subject subject = getSubject();
        //如果登录了就退出登录
        if(subject.isAuthenticated()){
           subject.logout();
        }
        //封装用户对象
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try {
            subject.login(token);
        }catch (Exception e){
            return  false;
        }
        return subject.isAuthenticated();
    }

/**
 * @Author zhen.shen
 * @Description //TODO 判断用户角色
 * @Date 11:06 2019/12/18
 * @Param 
 * @return 
 **/
    public static boolean hasRole(User user,String role){
        Subject subject = getSubject();
        return subject.hasRole(role);
    }
/*
 * @Author zhen.shen
 * @Description //TODO 判断用户权限
 * @Date 11:07 2019/12/18
 * @Param
 * @return
 **/
    public static boolean isPermitted(User user,String permitted){
        Subject subject = getSubject();
        return subject.isPermitted(permitted);

    }
}
