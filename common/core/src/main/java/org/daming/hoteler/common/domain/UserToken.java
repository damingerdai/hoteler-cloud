package org.daming.hoteler.common.domain;

import java.util.StringJoiner;

/**
 * @author gming001
 * @version 2023-11-04 22:34
 */
public class UserToken {

    private String accessToken;

    private String refreshToken;

    private long exp;

    public String getAccessToken() {
        return accessToken;
    }

    public UserToken setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public UserToken setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public long getExp() {
        return exp;
    }

    public UserToken setExp(long exp) {
        this.exp = exp;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserToken.class.getSimpleName() + "[", "]")
                .add("accessToken='" + accessToken + "'")
                .add("refreshToken='" + refreshToken + "'")
                .add("exp=" + exp)
                .toString();
    }
}
