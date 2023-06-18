package org.daming.hoteler.auth.service;

import org.daming.hoteler.security.service.IPasswordService;

/**
 * @author gming001
 * @version 2023-06-18 11:33
 */
public interface IPasswordHelperService {

    IPasswordService getPasswordService(String passwordType);
}
