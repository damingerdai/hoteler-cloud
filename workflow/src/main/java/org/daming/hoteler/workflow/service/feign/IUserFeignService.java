package org.daming.hoteler.workflow.service.feign;

import org.daming.hoteler.common.domain.User;
import org.daming.hoteler.common.response.DataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service")
public interface IUserFeignService {

    @GetMapping("/user")
    DataResponse<User> getUserByAccessToken(@RequestHeader(value = "Authorization") String authorization);
}
