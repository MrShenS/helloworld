package com.zhenshen.Second.dao;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.zhenshen.First.SimpleTest.FirstShiro;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.ILoggerFactory;
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
     * @Description 授权的方法
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
        //1.获取用户名和密码
       UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;
       //2.获取用户名
        String username = usernamePasswordToken.getUsername();
        logger.info("DatabaseRealm:"+username);
        // 获取密码
       String password = usernamePasswordToken.getPassword().toString();
        char[] password1 = usernamePasswordToken.getPassword();
        String s = new String(password1);
        System.out.println("++++++++++++++++++++++++++"+new String(password1));
        //获取数据库中的密码：
        String passwordInDatabase = ShiroDao.getPassword(username);
//        logger.info("DatabaseRealm:数据库密码："+passwordInDatabase);
//        logger.info("===================================Token中的密码和用户名："+username+":"+usernamePasswordToken.getPassword().toString());
//        logger.info("为转换的密码："+usernamePasswordToken.getPassword());
//        logger.info("对比结果："+password.equals(passwordInDatabase));
        password=passwordInDatabase;

        //
        if (null==passwordInDatabase||!s.equals(passwordInDatabase)){
            System.out.println("服了 是真行啊");
            throw new AuthenticationException();
        }
        //认证信息：放用户名和密码 getName()是父类的方法  返回当前类名
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return simpleAuthenticationInfo;
    }
}
