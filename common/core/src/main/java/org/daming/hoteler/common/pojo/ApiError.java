package org.daming.hoteler.common.pojo;

import java.util.StringJoiner;

/**
 * application error
 *
 * @author gming001
 * @version 2023-11-04 22:29
 */
public class ApiError {

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public ApiError setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiError setMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiError(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ApiError() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiError.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("message='" + message + "'")
                .toString();
    }

}
