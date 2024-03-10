package org.daming.hoteler.common.config.service.impl;

import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.daming.hoteler.common.config.prop.ErrorProp;
import org.daming.hoteler.common.config.service.IErrorCodeService;
import org.daming.hoteler.common.constants.ErrorCodeConstants;
import org.daming.hoteler.common.exceptions.ExceptionBuilder;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.common.pojo.ApiError;

import java.util.Map;
import java.util.stream.Collectors;

public class ErrorCodeServiceImpl implements IErrorCodeService {

    private ErrorProp errorProp;

    private Map<Integer, String> cache;

    @Override
    public String getMessage(int code) throws HotelerException {
        var message = this.doGetMessage(code);
        return message;
    }

    @Override
    public String getMessage(int code, Object... params) throws HotelerException {
        var message = this.doGetMessage(code);
        return String.format(message, params);
    }

    private String doGetMessage(int code) throws HotelerException {
        var message = this.cache.get(code);
        if (StringUtils.isBlank(message)) {
            throw ExceptionBuilder.buildException(ErrorCodeConstants.SYSTEM_ERROR_CODEE, "系统异常：'" + code + "' 是未知错误代码");
        }
        return message;
    }

    public ErrorCodeServiceImpl(ErrorProp errorProp) {
        super();
        this.errorProp = errorProp;
    }

    @PostConstruct
    private void init() {
        this.cache = this.errorProp.getErrors().stream()
                .filter(apiError -> StringUtils.isNumeric(apiError.getCode()))
                .collect(Collectors.toMap((apiError) -> Integer.valueOf(apiError.getCode()), ApiError::getMessage));
    }
}
