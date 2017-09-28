package com.manager.login;

import com.manager.login.util.CryptUtils;
import com.util.SpringContext;
import org.apache.log4j.Logger;

import java.util.Map;

public class LoginDecrypt {
    public static Logger LOG = Logger.getLogger(LoginDecrypt.class);

    private  String key = "7041034e724f4d0b92df9bcc25f1890c";
    private  String enPassword = "DAD6A487E9DC593648A6B8D0571255A7";
    private  String dePassword = "";

    public void setKey(String key) {
        this.key = key;
    }

    public void setEnPassword(String enPassword) {
        this.enPassword = enPassword;
    }

    public void setDePassword(String dePassword) {
        this.dePassword = dePassword;
    }

    private Map map;

    public LoginDecrypt() {
        map = (Map<String,String>) SpringContext.getBean("managerLogin");
    }

    public   String decrypt() throws Exception{
        String tKey = (String) map.get("com.manager.login.key");
        String tPassword = (String) map.get("com.manager.login.encrypt.password");
        LOG.info("综合平台登录密码密文："+tPassword);
        if(tKey!=null&&tKey.length()>0){
            key = tKey;
        }
        if(tPassword!=null&&tPassword.length()>0){
            enPassword = tPassword;
        }
        byte[] deBytes = enPassword.getBytes("UTF-8");
        byte[] tBytes = CryptUtils.hex2byte(deBytes);
        byte[] enBytes = CryptUtils.decrypt(key, tBytes);
        String dePassword = new String(enBytes);
        LOG.info("综合平台登录密码解密："+tPassword);
        return dePassword;
    }

    public   String encrypt() throws Exception{
        String tKey = (String) map.get("com.manager.login.key");
        String tPassword = (String) map.get("com.manager.login.decrypt.password");
        LOG.info("综合平台登录密码原文："+tPassword);
        if(tKey!=null&&tKey.length()>0){
            key = tKey;
        }
        if(tPassword!=null&&tPassword.length()>0){
            dePassword = tPassword;
        }
        byte[] enBytes = dePassword.getBytes("UTF-8");
        enPassword = CryptUtils.byte2hex(enBytes);
        LOG.info("综合平台登录密码加密："+enPassword);
        return enPassword;
    }

}
