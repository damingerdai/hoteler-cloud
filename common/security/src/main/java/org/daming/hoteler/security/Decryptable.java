package org.daming.hoteler.security;

import org.daming.hoteler.common.exceptions.HotelerException;

public interface Decryptable {

    /**
     * 解密
     * @param encoderContent 待解密的字符
     * @return 解密后的字符
     * @throws HotelerException
     */
    String decrypt(final String encoderContent) throws HotelerException;
}
