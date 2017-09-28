package com.manager.login;

import org.junit.Test;

public class LoginDecryptTest {
    private static String key = "7041034e724f4d0b92df9bcc25f1890c";
    private static String password = "DAD6A487E9DC593648A6B8D0571255A7";
    @Test
    public void decrypt() throws Exception {
        LoginDecrypt.decrypt(password,key);
    }

}