package org.daming.hoteler.common.errors;

import org.daming.hoteler.common.exceptions.HotelerException;

public interface IErrorService {


    HotelerException createHotelerException(int code, Object[] params, Throwable cause);

    HotelerException createHotelerSystemException(String message, Throwable cause);

    HotelerException createHotelerException(int code, Throwable cause);

    HotelerException createHotelerException(int code);

    HotelerException createSqlHotelerException(Exception ex, Object...params);
}
