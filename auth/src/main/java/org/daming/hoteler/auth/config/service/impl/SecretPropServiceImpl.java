package org.daming.hoteler.auth.config.service.impl;

import org.daming.hoteler.auth.config.props.SecretProp;
import org.daming.hoteler.auth.config.service.ISecretPropService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SecretPropServiceImpl implements ISecretPropService, InitializingBean  {
    private final SecretProp secretProp;

    @Override
    public String getSalt() {
        return this.secretProp.getSalt();
    }

    @Override
    public String getKey() {
        return this.secretProp.getKey();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (Objects.isNull(this.secretProp)) {
            throw new RuntimeException("secret prop error");
        }
    }

    public SecretPropServiceImpl(SecretProp secretProp) {
        super();
        this.secretProp = secretProp;
    }
}
