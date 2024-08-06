package org.daming.hoteler.common.errors.impl;

import org.daming.hoteler.common.config.service.IErrorCodeService;
import org.daming.hoteler.common.constants.ErrorCodeConstants;
import org.daming.hoteler.common.errors.IErrorService;
import org.daming.hoteler.common.exceptions.ExceptionBuilder;
import org.daming.hoteler.common.exceptions.HotelerException;

public class ErrorServiceImpl implements IErrorService  {
    private IErrorCodeService errorCodeService;

    @Override
    public HotelerException createHotelerException(int code, Object[] params, Throwable cause) {
        var message = this.errorCodeService.getMessage(code, params);
        return ExceptionBuilder.buildException(code, message, cause);
    }

    @Override
    public HotelerException createHotelerSystemException(String message, Throwable cause) {
        return ExceptionBuilder.buildException(ErrorCodeConstants.SYSTEM_ERROR_CODEE, message, cause);
    }

    @Override
    public HotelerException createHotelerException(int code, Throwable cause) {
        var message = this.errorCodeService.getMessage(code);
        return ExceptionBuilder.buildException(code, message, cause);
    }

    @Override
    public HotelerException createHotelerException(int code, Object[] params) {
        var message = this.errorCodeService.getMessage(code, params);
        return ExceptionBuilder.buildException(code, message);
    }

    @Override
    public HotelerException createHotelerException(int code) {
        var message = this.errorCodeService.getMessage(code);
        return ExceptionBuilder.buildException(code, message);
    }

    @Override
    public HotelerException createSqlHotelerException(Exception ex, Object... params) {
        var message = this.errorCodeService.getMessage(ErrorCodeConstants.SQL_ERROR_CODE, params);
        return ExceptionBuilder.buildException(ErrorCodeConstants.SQL_ERROR_CODE, message, ex);
    }



    public ErrorServiceImpl(IErrorCodeService errorCodeService) {
        super();
        this.errorCodeService = errorCodeService;
    }
}
