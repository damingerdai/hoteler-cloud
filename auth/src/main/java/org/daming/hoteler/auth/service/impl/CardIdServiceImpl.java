package org.daming.hoteler.auth.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import org.daming.hoteler.auth.config.service.ISecretPropService;
import org.daming.hoteler.auth.service.ICardIdService;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.common.logger.LoggerManager;
import org.springframework.stereotype.Service;

/**
 * @author daming
 * @version 2023-05-07 22:52
 **/
@Service
public class CardIdServiceImpl implements ICardIdService {

    private final ISecretPropService secretPropService;

    @Override
    public String encode(String content) throws HotelerException {
        try {
            var hex = this.secretPropService.getPersonSalt();
            return getKey(hex).encryptHex(content);
        } catch (Exception ex) {
            LoggerManager.getCommonLogger().warn(ex.getMessage());
            return null;
        }
    }

    @Override
    public String decode(String encryptHex) throws HotelerException {
        try {
            var hex = this.secretPropService.getPersonSalt();
            return getKey(hex).decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        } catch (Exception ex) {
            LoggerManager.getCommonLogger().warn(ex.getMessage());
            return null;
        }
    }

    private AES getKey(String hex) {
        byte[] key = Convert.hexToBytes(hex);
        return SecureUtil.aes(key);
    }

    public CardIdServiceImpl(ISecretPropService secretPropService) {
        this.secretPropService = secretPropService;
    }
}
