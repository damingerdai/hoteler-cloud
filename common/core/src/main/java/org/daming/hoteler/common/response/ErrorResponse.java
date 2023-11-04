package org.daming.hoteler.common.response;

import org.daming.hoteler.common.pojo.ApiError;

/**
 * @author gming001
 * @version 2023-11-04 22:29
 */
public class ErrorResponse extends CommonResponse {

    private ApiError error;

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    public ErrorResponse() {
        super();
        this.setStatus(-1);
    }

    public ErrorResponse(ApiError error) {
        super();
        this.setStatus(-1);
        this.error = error;
    }

}
