package org.daming.hoteler.security.service.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.security.service.IPasswordService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA(Secure Hash Algorithm，安全散列算法)
 * 数字签名等密码学应用中重要的工具，被广泛地应用于电子商务等信息安全领域
 */
public class ShaPasswordService implements IPasswordService {

    private static final String ALGORITHM = "SHA";

    @Override
    public String decrypt(String encoderContent) throws HotelerException {
        throw new RuntimeException("org.daming.hoteler.security.service.impl.ShaPasswordService#encrypt method is not supported");
    }

    @Override
    public String encrypt(String content) throws HotelerException {
        try {
            var sha = MessageDigest.getInstance(ALGORITHM);
            byte[] bytes = sha.digest(content.getBytes());
            StringBuilder hexValue = new StringBuilder();
            for (byte b : bytes) {
                //将其中的每个字节转成十六进制字符串：byte类型的数据最高位是符号位，通过和0xff进行与操作，转换为int类型的正整数。
                String toHexString = Integer.toHexString(b & 0xff);
                hexValue.append(toHexString.length() == 1 ? "0" + toHexString : toHexString);
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ALGORITHM +" algorithm is not supported", ex);
        }
    }

    public ShaPasswordService() {
        super();
    }
}
