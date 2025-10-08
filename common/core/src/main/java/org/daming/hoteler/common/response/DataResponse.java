package org.daming.hoteler.common.response;

import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2023-11-04 22:28
 */
public class DataResponse<T> extends CommonResponse {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DataResponse() {
        super();
    }

    public DataResponse(T data) {
        super();
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DataResponse.class.getSimpleName() + "[", "]")
                .add("data=" + data)
                .add("status=" + this.getStatus())
                .toString();
    }
}
