package com.poly.be_duan.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptUtil {
    public static String encrypt(String origin) {
        return BCrypt.hashpw(origin, BCrypt.gensalt());
    }

    /**
     * Kiểm tra password có hợp lệ hay không
     *
     * @param origin
     * @param encrypted
     * @return
     */
    public static boolean check(String origin, String encrypted) {
        return BCrypt.checkpw(origin, encrypted);
    }
}
