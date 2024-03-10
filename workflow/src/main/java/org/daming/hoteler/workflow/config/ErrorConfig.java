package org.daming.hoteler.workflow.config;

import org.daming.hoteler.common.config.ErrorAutoConfig;
import org.daming.hoteler.common.config.service.IErrorCodeService;
import org.daming.hoteler.common.errors.IErrorService;
import org.daming.hoteler.common.errors.impl.ErrorServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ErrorAutoConfig.class)
public class ErrorConfig {

    @Bean
    public IErrorService errorService(IErrorCodeService errorCodeService) {
        return new ErrorServiceImpl(errorCodeService);
    }
}
