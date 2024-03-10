package org.daming.hoteler.common.config.service;

import org.daming.hoteler.common.exceptions.HotelerException;

public interface IErrorCodeService {

    String getMessage(int code) throws HotelerException;

    String getMessage(int code, Object...params) throws HotelerException;
}
