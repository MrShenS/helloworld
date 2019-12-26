package com.zhenshen.ByDatebase.dao;

import com.zhenshen.ByDatebase.bean.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ShiroDao {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shiro?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
                    "shenzhen", "shenzhen");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 通过用户名获取密码
     *
     * @param username
     * @return
     */
    public static String getPassword(String username) {
        String sql = "select password from user where name = ?";
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            if (rs.next())
                return rs.getString("password");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Set<String> getRoles(String username) {
        String sql = "select role.name "
                + "from role,user_role,user "
                + "where user.id=user_role.uid "
                + "and user_role.rid=role.id "
                + "and user.name = ?";
        ResultSet rs = null;
        Set<String> set = new HashSet<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                set.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return set;
    }

    public static Set<String> getPermits(String username) {
        String sql = "select permission.name "
                + "from"
                + " permission,role_permission,role	,user_role,user "
                + "where "
                + "permission.id = role_permission.pid "
                + "and role_permission.rid = role.id "
                + "and role.id = user_role.rid "
                + "and user_role.uid = user.id "
                + "and user.name = ?";
        ResultSet rs = null;
        Set<String> set = new HashSet<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                set.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return set;
    }
/**
 * @Author zhen.shen
 * @Description //TODO 注册用户
 * @Date 20:53 2019/12/18
 * @Param
 * @return
 **/
    public static boolean registerUser(String username,String passward){
        //获取盐值
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        //加密次数
        int times=3;
        //加墨方式
        String type ="md5";
        //加密后的密码
        String lastPassword = new SimpleHash(type, passward, salt, times).toString();
        /*******************加密结束****************/
        String sql="insert into user(name,password,salt)VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,lastPassword);
            preparedStatement.setString(3,salt);
            if(preparedStatement.execute()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static User getUser(String username){
        String sql="select * from user where name = ?";
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setSalt(resultSet.getString("salt"));
            }
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public static void main(String[] args) {
        System.out.println("Object的角色：" + new ShiroDao().getRoles("Object"));
        System.out.println("Reader的角色：" + new ShiroDao().getRoles("Reader"));
        System.out.println("Object的权限："+new ShiroDao().getPermits("Object"));
        System.out.println("Reader的权限："+new ShiroDao().getPermits("Reader"));
        System.out.println(getPassword("Reader"));
        registerUser("shenzhen","shenzhen");

    }
}