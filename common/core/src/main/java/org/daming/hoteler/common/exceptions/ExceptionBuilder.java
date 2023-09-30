package org.daming.hoteler.common.exceptions;

import org.daming.hoteler.common.logger.LoggerManager;

import java.util.Objects;

public class ExceptionBuilder {

    public static HotelerException buildException(int code, String message, Throwable clause) {
        HotelerException ex = new HotelerException(code, message, clause);
        if (Objects.isNull(clause)) {
            LoggerManager.getErrorLogger().error("error: code {}, message: {}", code, message);
        } else {
            LoggerManager.getErrorLogger().error("error: code {}, message: {}, cause: {}", code, message, clause);
        }

        return ex;
    }

    public static HotelerException buildException(int code, String message) {
        return buildException(code, message, null);
    }

}
