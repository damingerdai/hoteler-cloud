package org.daming.hoteler.common.config;

import org.daming.hoteler.common.config.prop.ErrorProp;
import org.daming.hoteler.common.config.service.IErrorCodeService;
import org.daming.hoteler.common.config.service.impl.ErrorCodeServiceImpl;
import org.daming.hoteler.common.support.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationPropertiesScan
@ConditionalOnResource(resources = "classpath:errors.yml")
@PropertySource(value = "classpath:errors.yml", factory = YamlPropertySourceFactory.class)
@EnableConfigurationProperties(ErrorProp.class)
public class ErrorAutoConfig {

    @Bean
    public YamlPropertySourceFactory yamlPropertySourceFactory() {
        return new YamlPropertySourceFactory();
    }

    @Bean
    // @ConditionalOnBean(type = "org.daming.hoteler.common.config.prop.ErrorProp")
    public IErrorCodeService errorCodeService(ErrorProp errorProp) {
        return new ErrorCodeServiceImpl(errorProp);
    }
}
