package org.daming.hoteler.auth.service;

import org.daming.hoteler.common.domain.UserToken;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.auth.domain.User;

public interface ITokenService {

    UserToken createToken(String username, String password) throws HotelerException;

    UserToken refreshToken(String refreshToken) throws HotelerException;

    User verifyToken(String token) throws HotelerException;
}
