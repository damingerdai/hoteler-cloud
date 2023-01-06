package org.daming.hoteler.security.service.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.security.service.IPasswordService;

public class NoopPasswordService implements IPasswordService {

    @Override
    public String decrypt(String encoderContent) throws HotelerException {
        return encoderContent;
    }

    @Override
    public String encrypt(String content) throws HotelerException {
        return content;
    }
}
