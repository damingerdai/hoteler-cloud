package org.daming.hoteler.security.service.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.security.service.IPasswordService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5(Message Digest Algorithm)加密算法
 * 是一种单向加密算法，只能加密不能解密
 */
@Service("md5PasswordService")
public class Md5PasswordService implements IPasswordService {

    private static String ALGORITHM = "md5";

    @Override
    public String decrypt(String encoderContent) throws HotelerException {
        throw new RuntimeException("org.daming.hoteler.security.service.impl.Md5PasswordService#encrypt method is not supported");
    }

    @Override
    public String encrypt(String content) throws HotelerException {
        try {
            var md5 = MessageDigest.getInstance(ALGORITHM);
            var digest = md5.digest(content.getBytes());
            StringBuilder md5code = new StringBuilder(new BigInteger(1, digest).toString(16));
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code.insert(0, "0");
            }
            return md5code.toString();
        } catch (NoSuchAlgorithmException ne) {
            throw new RuntimeException("md5 algorithm is not supported", ne);
        }
    }

    public Md5PasswordService() {
        super();
    }
}
