package org.daming.hoteler.user.service;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.user.domain.User;
import org.daming.hoteler.user.domain.UserToken;

public interface ITokenService {

    UserToken createToken(String username, String password) throws HotelerException;

    UserToken refreshToken(String refreshToken) throws HotelerException;

    User verifyToken(String token) throws HotelerException;
}
