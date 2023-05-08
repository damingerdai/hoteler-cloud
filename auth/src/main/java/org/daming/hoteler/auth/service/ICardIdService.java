package org.daming.hoteler.auth.service;

import org.daming.hoteler.common.exceptions.HotelerException;

/**
 * @author daming
 * @version 2023-05-07 22:50
 **/
public interface ICardIdService {

    String encode(String content) throws HotelerException;

    String decode(String encryptHex) throws HotelerException;
}
