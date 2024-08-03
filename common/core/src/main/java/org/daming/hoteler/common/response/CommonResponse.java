package org.daming.hoteler.common.response;

/**
 * @author gming001
 * @version 2023-11-04 22:27
 */
public class CommonResponse {

    public static final int SUCCESS_STATUS = 200;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CommonResponse() {
        super();
        this.status = SUCCESS_STATUS;
    }
}
