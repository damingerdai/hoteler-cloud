package org.daming.hoteler.auth.config.service;

public interface ISecretPropService {

    String getSalt();

    String getKey();

    String getPersonSalt();
}
