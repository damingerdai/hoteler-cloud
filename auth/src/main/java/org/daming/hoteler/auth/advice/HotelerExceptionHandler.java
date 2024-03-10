package org.daming.hoteler.auth.advice;

import org.daming.hoteler.common.constants.ErrorCodeConstants;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.common.logger.LoggerManager;
import org.daming.hoteler.common.pojo.ApiError;
import org.daming.hoteler.common.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HotelerExceptionHandler {

    @ExceptionHandler(value = HotelerException.class)
    public ErrorResponse baseErrorHandler(Exception e) throws Exception {
        HotelerException de = (HotelerException) e;
        LoggerManager.getErrorLogger().error("ERR-" + de.getCode() + ", " + de.getMessage(),e);
        var response = new ErrorResponse();
        var apiError = new ApiError().setCode("ERR-" + de.getCode()).setMessage(de.getMessage());
        response.setError(apiError);

        return response;
    }

    @ExceptionHandler(value = Exception.class)
    public ErrorResponse defaultErrorHandler(Exception e) throws Exception {
        LoggerManager.getErrorLogger().error("ERR-" + ErrorCodeConstants.SYSTEM_ERROR_CODEE + ", " + e.getMessage(),e);
        var response = new ErrorResponse();
        var error = new ApiError().setCode("ERR-" + ErrorCodeConstants.SYSTEM_ERROR_CODEE).setMessage(e.getMessage());
        response.setError(error);

        return response;
    }
}
