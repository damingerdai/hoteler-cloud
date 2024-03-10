package org.daming.hoteler.common.config.prop;

import org.daming.hoteler.common.pojo.ApiError;
import org.daming.hoteler.common.support.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.StringJoiner;

@Configuration
@ConfigurationProperties()
@PropertySource(value = "classpath:errors.yml", factory = YamlPropertySourceFactory.class)
public class ErrorProp {

    private List<ApiError> errors;

    public List<ApiError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiError> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ErrorProp.class.getSimpleName() + "[", "]")
                .add("errors=" + errors)
                .toString();
    }
}
