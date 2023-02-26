package org.daming.hoteler.security.service.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.security.service.IPasswordService;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * DES(Data Encryption Standard)对称加密/解密
 * 数据加密标准算法,和BASE64最明显的区别就是有一个工作密钥，该密钥既用于加密、也用于解密，并且要求密钥是一个长度至少大于8位的字符串
 * 本实现可能存在错误，但是确认是可逆
 */
@Service("desPasswordService")
public class DESPasswordService implements IPasswordService {

    private static String CHARSETNAME = "UTF-8";
    private static String ALGORITHM = "DES";

    private Key key;

    protected Key generateKey(String keyStr) throws HotelerException {
        Key key = null;
        try {
            // 生成DES算法对象
            var generator = KeyGenerator.getInstance(ALGORITHM);
            // 运用SHA1安全策略
            var secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // 设置密钥
            secureRandom.setSeed(keyStr.getBytes());
            // 初始化基于SHA1的算法对象
            generator.init(secureRandom);
            // 生成密钥对象
            key = generator.generateKey();
            generator = null;
        } catch (NoSuchAlgorithmException ne) {
            throw new RuntimeException(ALGORITHM +" algorithm is not supported", ne);
        }
        return key;
    }

    @Override
    public String decrypt(String encoderContent) throws HotelerException {
        try {
            var decoder = Base64.getDecoder();
            var bytes = decoder.decode(encoderContent);
            var cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            var doFinal = cipher.doFinal(bytes);
            return new String(doFinal, CHARSETNAME);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ALGORITHM +" algorithm is not supported", ex);
        }
    }

    @Override
    public String encrypt(String content) throws HotelerException {
        try {
            var encoder = Base64.getEncoder();
            var bytes = content.getBytes();
            var cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.key);
            var doFinal = cipher.doFinal(bytes);
            return encoder.encodeToString(doFinal);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new RuntimeException(ALGORITHM +" algorithm is not supported", ex);
        }
    }

    public DESPasswordService(String secretKey) {
        super();
        this.key = this.generateKey(secretKey);
    }
}
