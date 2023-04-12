package org.daming.hoteler.security.configuration;

import org.daming.hoteler.security.props.PasswordSecretProperties;
import org.daming.hoteler.security.service.IPasswordService;
import org.daming.hoteler.security.service.impl.Base64PasswordService;
import org.daming.hoteler.security.service.impl.DESPasswordService;
import org.daming.hoteler.security.service.impl.HmacPasswordService;
import org.daming.hoteler.security.service.impl.Md5PasswordService;
import org.daming.hoteler.security.service.impl.NoopPasswordService;
import org.daming.hoteler.security.service.impl.RSAPasswordService;
import org.daming.hoteler.security.service.impl.ShaPasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfiguration
@ConditionalOnClass(IPasswordService.class)
@EnableConfigurationProperties(PasswordSecretProperties.class)
public class PasswordConfiguration {

    private Logger logger = LoggerFactory.getLogger(PasswordConfiguration.class);

    private PasswordSecretProperties passwordTypeProperties;

    @Bean(name = "base64PasswordService")
    public Base64PasswordService base64PasswordService() {
        return new Base64PasswordService();
    }

    @Bean(name = "desPasswordService")
    public DESPasswordService desPasswordService() {
        return new DESPasswordService(this.passwordTypeProperties.getKey());
    }

    @Bean(name = "hmacPasswordService")
    public HmacPasswordService hmacPasswordService() {
        return new HmacPasswordService(this.passwordTypeProperties.getKey());
    }

    @Bean(name = "md5PasswordService")
    public Md5PasswordService md5PasswordService() {
        return new Md5PasswordService();
    }

    @Bean(name = "noopPasswordService")
    public NoopPasswordService noopPasswordService() {
        return new NoopPasswordService();
    }

    @Bean(name = "rsaPasswordService")
    public RSAPasswordService rsaPasswordService() {
        return new RSAPasswordService();
    }

    @Bean(name = "shaPasswordService")
    public ShaPasswordService shaPasswordService() {
        return new ShaPasswordService();
    }

    @Autowired
    public void setPasswordTypeProperties(PasswordSecretProperties passwordTypeProperties) {
        this.passwordTypeProperties = passwordTypeProperties;
    }
}
