package org.daming.hoteler.security.service.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.security.service.IPasswordService;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * HMAC(Hash Message Authentication Code，散列消息鉴别码)
 */
public class HmacPasswordService implements IPasswordService {

    private static final String ALGORITHM = "HMAC";

    private Key key;

    protected SecretKey generateKey(String keyStr) throws HotelerException {
        return new SecretKeySpec(keyStr.getBytes(), ALGORITHM);
    }

    @Override
    public String decrypt(String encoderContent) throws HotelerException {
        throw new RuntimeException("org.daming.hoteler.security.service.impl.HmacPasswordService#encrypt method is not supported");
    }

    @Override
    public String encrypt(String content) throws HotelerException {
        try {
            var mac = Mac.getInstance(this.key.getAlgorithm());
            mac.init(this.key);
            return new String(mac.doFinal(content.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            throw new RuntimeException(ALGORITHM +" algorithm is not supported", ex);
        }
    }

    public HmacPasswordService(String secretKey) {
        super();
        this.key = this.generateKey(secretKey);
    }

}
