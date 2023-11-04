package org.daming.hoteler.common.response;

import org.daming.hoteler.common.domain.UserToken;

import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2023-11-04 22:33
 */
public class UserTokenResponse extends CommonResponse {
    private UserToken userToken;

    public UserToken getUserToken() {
        return userToken;
    }

    public void setUserToken(UserToken userToken) {
        this.userToken = userToken;
    }

    public UserTokenResponse(UserToken userToken) {
        super();
        this.userToken = userToken;
    }

    public UserTokenResponse() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserTokenResponse.class.getSimpleName() + "[", "]")
                .add("userToken=" + userToken)
                .toString();
    }

}
