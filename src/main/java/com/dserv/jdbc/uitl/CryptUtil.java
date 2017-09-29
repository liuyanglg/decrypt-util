package com.dserv.jdbc.uitl;

import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Package : com.lyf.util
 * @Class : CryptUtil
 * @Description : AES加解密工具类
 * @Author : liuya
 * @CreateDate : 2017-08-28 星期一 14:30:10
 * @Version : V1.0.0
 * @Copyright : 2017 liuya Inc. All rights reserved.
 */
public class CryptUtil {
    public static Logger LOG = Logger.getLogger(CryptUtil.class);
    /**
     * @Method : initAESCipher
     * @Description : 初始化 AES Cipher
     * @param sKey :
     * @param cipherMode :
     * @return : javax.crypto.Cipher
     * @author : liuya
     * @CreateDate : 2017-08-28 星期一 14:30:35
     */
    public static Cipher initAESCipher(String sKey, int cipherMode) {
        //创建Key gen
        KeyGenerator keyGenerator = null;
        Cipher cipher = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(sKey.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] codeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(codeFormat, "AES");
            cipher = Cipher.getInstance("AES");
            //初始化
            cipher.init(cipherMode, key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidKeyException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return cipher;
    }

    /**
     * @Method : encryptFile
     * @Description : 对文件进行AES加密
     * @param sourceFile :
     * @param outFile :
     * @param sKey :
     * @return : java.io.File
     * @author : liuya
     * @CreateDate : 2017-08-28 星期一 14:30:58
     */
    public static File encryptFile(File sourceFile, String outFile, String sKey) {
        File encrypfile = null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            byte[] b = new byte[inputStream.available()];
            DesUtil des = new DesUtil();
            inputStream.read(b);
            String data = new String(b).toString();
//            LOG.info("定时任务项目jdbc原文："+data);
            String enData = des.encrypt(data);
//            LOG.info("定时任务项目jdbc加密："+enData);
            byte[] enBytes = enData.getBytes("UTF-8");
            encrypfile = new File(outFile);
            outputStream = new FileOutputStream(encrypfile);
            outputStream.write(enBytes);
            outputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return encrypfile;
    }

    /**
     * @Method : decryptFile
     * @Description : AES方式解密文件
     * @param sourceFile :
     * @param outFile :
     * @param sKey :
     * @return : java.io.File
     * @author : liuya
     * @CreateDate : 2017-08-28 星期一 14:31:32
     */
    public static File decryptFile(File sourceFile, String outFile, String sKey) {
        File decryptFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            decryptFile = new File(outFile);
            inputStream = new FileInputStream(sourceFile);
            byte[] b = new byte[inputStream.available()];
            DesUtil des = new DesUtil();
            inputStream.read(b);
            String data = new String(b).toString();
//            LOG.info("定时任务项目jdbc密文："+data);
            String deData = des.decrypt(data);
//            LOG.info("定时任务项目jdbc解密："+deData);
            byte[] enBytes = deData.getBytes("UTF-8");
            decryptFile = new File(outFile);
            outputStream = new FileOutputStream(decryptFile);
            outputStream.write(enBytes);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return decryptFile;
    }

    /**
     * @Method : getProjectRootPath
     * @Description : 获取工程跟路径
     * @return : java.lang.String
     * @author : liuya
     * @CreateDate : 2017-08-28 星期一 14:32:42
     */
    public static String getProjectRootPath() {
        File file = new File("");
        String projectPath = "";
        try {
            projectPath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projectPath;
    }
}

