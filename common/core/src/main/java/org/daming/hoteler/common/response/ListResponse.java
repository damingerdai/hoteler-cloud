package org.daming.hoteler.common.response;

import java.util.List;
import java.util.StringJoiner;

/**
 * list response
 *
 * @author gming001
 * @version 2023-11-04 22:32
 */
public class ListResponse<T> extends CommonResponse {

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public ListResponse(List<T> data) {
        super();
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ListResponse.class.getSimpleName() + "[", "]")
                .add("data=" + data)
                .toString();
    }

}
