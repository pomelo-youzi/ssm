package com.itheima.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 对密码进行加密的工具类
 */
public class BCryptPasswordEncoderUtil {
    private static BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        String encode = bCryptPasswordEncoder.encode(password);
        return encode;
    }

    public static void main(String[] args) {
        System.out.println(encodePassword("123"));
//        $2a$10$BvyS1t95FvONyLrjv6FS1eevkO5MxBLjI8130l8SprsvB4g7UwqQ6
    }
}
