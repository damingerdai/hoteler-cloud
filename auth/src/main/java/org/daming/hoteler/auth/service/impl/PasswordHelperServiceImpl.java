package org.daming.hoteler.auth.service.impl;

import org.daming.hoteler.auth.service.IPasswordHelperService;
import org.daming.hoteler.security.service.IPasswordService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author gming001
 * @version 2023-06-18 11:34
 */
@Service
public class PasswordHelperServiceImpl implements IPasswordHelperService, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public IPasswordService getPasswordService(String passwordType) {
        var defaultPasswordType = "noop";
        var beanName = Objects.requireNonNull(passwordType, defaultPasswordType) + "PasswordService";
        return Objects.requireNonNull(this.applicationContext).getBean(beanName, IPasswordService.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
