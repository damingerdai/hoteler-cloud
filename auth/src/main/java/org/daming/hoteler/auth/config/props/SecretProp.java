package org.daming.hoteler.auth.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
@ConfigurationProperties(prefix = "secret")
public class SecretProp {

    private String salt;

    private String key;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SecretProp() {
        super();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SecretProp.class.getSimpleName() + "[", "]")
                .add("salt='" + salt + "'")
                .add("key='" + key + "'")
                .toString();
    }
}
