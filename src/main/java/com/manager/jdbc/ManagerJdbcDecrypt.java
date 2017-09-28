package com.manager.jdbc;

import com.manager.jdbc.uitl.CryptUtil;
import com.util.FormatDate;
import com.util.SpringContext;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Map;

public class ManagerJdbcDecrypt {
    public static Logger LOG = Logger.getLogger(ManagerJdbcDecrypt.class);

    private String key = "97DC0D40FCFB425EA2A94C3B34ED99F9";
    private String encryptFilePath = "/encrypt/";
    private String decryptFilePath = "/decrypt/";
    private String validName = "jdbc.properties";
    private String format = "yyMMdd_HHmmss";
    private Map map;

    public ManagerJdbcDecrypt() {
        map = (Map<String, String>) SpringContext.getBean("managerJdbc");
    }

    public void decrypt() throws Exception {
        String rootPath = CryptUtil.getProjectRootPath();
        String sourceFilePath = rootPath + encryptFilePath;
        String outFilePath = rootPath + decryptFilePath;
        createFile(sourceFilePath);
        createFile(outFilePath);
        File sourceFile = new File(sourceFilePath + validName);
        if (!sourceFile.exists()) {
            LOG.info("文件不存在");
            return;
        }
        CryptUtil.decryptFile(sourceFile, outFilePath + validName + FormatDate.getDateStr(format), key);
    }

    public void encrypt() throws Exception {
        String rootPath = CryptUtil.getProjectRootPath();
        String sourceFilePath = rootPath + decryptFilePath;
        String outFilePath = rootPath + encryptFilePath;
        createFile(sourceFilePath);
        createFile(outFilePath);
        File sourceFile = new File(sourceFilePath + validName);
        if (!sourceFile.exists()) {
            LOG.info("文件不存在");
            return;
        }
        CryptUtil.decryptFile(sourceFile, outFilePath + validName + FormatDate.getDateStr(format), key);
    }
    public void createFile(String filePth) {
        File file = new File(filePth);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
