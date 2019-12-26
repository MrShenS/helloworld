package com.zhenshen.ByDatebase.dao;

import com.zhenshen.ByDatebase.bean.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @ClassNameDatabaseRealm
 * @Description TODO
 * @Author zhen.zhen
 * @Date 2019/12/189:18
 * @Version 1.0
 */
public class DatabaseRealm extends AuthorizingRealm {
    private static final transient Logger logger = LoggerFactory.getLogger(DatabaseRealm.class);
    /**
     * @Author zhen.shen
     * @Description 对用户授权
     * @Date 9:22 2019/12/18
     * @Param
     * @return
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //只用认证成功了 shiro才会调用这个方法进行授权
        //1.获取用户
        String username = (String) principalCollection.getPrimaryPrincipal();
        //2.获取权限和角色列表
        Set<String> permits = ShiroDao.getPermits(username);
        Set<String> roles = ShiroDao.getRoles(username);
        //3.开始授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permits);
        return authorizationInfo;
    }

    /**
     * @Author zhen.shen
     * @Description //TODO 用于验证用户名密码是否正确
     * @Date 9:22 2019/12/18
     * @Param
     * @return
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       /* //1.获取用户名和密码
       UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;
       //2.获取用户名
        String username = usernamePasswordToken.getUsername();
        logger.info("打印用户名 DatabaseRealm:"+username);
        // 获取密码
        char[] password1 = usernamePasswordToken.getPassword();
        String password = new String(password1);
        System.out.println("明文密码："+password);
        //获取数据库中的用户：
       // String passwordInDatabase = ShiroDao.getPassword(username);
        User user = ShiroDao.getUser(username);
        //将将铭文密码加盐加密
        System.out.println("数据库中的密码："+user.getPassword());
        String md5Passwd = new SimpleHash("md5", password, user.getSalt(), 3).toString();
        System.out.println("salt:"+user.getSalt());
        System.out.println("密文密码:"+md5Passwd);
        System.out.println("正在验证中......");
        String userPassword = user.getPassword();
        //在条件判断里面先判空 在使用短路或‘||’ 来防止后面的判断语句出现控制针问题
        if (null==user.getPassword()||!md5Passwd.equals(userPassword)){
            System.out.println("====================="+!md5Passwd.equals(userPassword));
            throw new AuthenticationException();
        }
//            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, getName());


        //认证信息：放用户名和密码 getName()是父类的方法  返回当前类名
        SimpleAuthenticationInfo simpleAuthenticationInfo
                = new SimpleAuthenticationInfo(username, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return simpleAuthenticationInfo;*/



       //博主
        //1.获取用户名密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //获取用户名
        String username = usernamePasswordToken.getUsername();
        String principal = (String)usernamePasswordToken.getPrincipal();
        System.out.println("principal:"+principal);
        DatabaseRealm databaseRealm = new DatabaseRealm();
        //获取密码
        String password = new String(usernamePasswordToken.getPassword());
        System.out.println("明文密码:"+password);
        //获取数据库中的用户
        User user = ShiroDao.getUser(usernamePasswordToken.getUsername());
        //String passwordInDatabase = ShiroDao.getPassword(username);


        //将用户输入的密码做一个加密后与数据库中的进行比对
        System.out.println("数据库中密码："+user.getPassword());
        String passwordMd5 = new SimpleHash("md5", password, user.getSalt(), 3).toString();
        System.out.println("salt:"+user.getSalt());
        System.out.println("密文密码:"+passwordMd5);
        System.out.println("正在验证中......");
        /*
         * //为空则表示没有当前用户，密码不匹配表示密码错误 if(null ==
         * user.getPassword()||!passwordMd5.equals(user.getPassword())) { throw new
         * AuthenticationException(); }
         */
        //认证信息：放用户名密码 getName()是父类的方法，返回当前类名
        //参数说明： 1.登录输入的用户名 2 登录输入的密码 3.数据库的加密盐值 4，当前类类名
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,user.getPassword(),ByteSource.Util.bytes(user.getSalt()),getName());
        return simpleAuthenticationInfo;
    }


}
