package org.daming.hoteler.common.exceptions;

import jakarta.validation.constraints.NotNull;
import org.daming.hoteler.common.logger.LoggerManager;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gming001
 * @version 2023-09-30 20:40
 */
public class HotelerAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, @NotNull Object... params) {
        LoggerManager.getErrorLogger().error("Async failed:");
        LoggerManager.getErrorLogger().error("Async method: {}", method.getName());
        LoggerManager.getErrorLogger().error("Async params: {}", convertParams(params));
        LoggerManager.getErrorLogger().error("Async error: {}", ex.getMessage());
    }

    protected String convertParams(Object... params) {
        return Stream.of(params).map(Object::toString).collect(Collectors.joining(",", "", ""));
    }
}
