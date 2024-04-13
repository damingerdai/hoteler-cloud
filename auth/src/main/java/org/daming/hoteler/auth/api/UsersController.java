package org.daming.hoteler.auth.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.service.IUserService;
import org.daming.hoteler.common.response.ListResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Users Controller")
public class UsersController {

    private final IUserService userService;

    @GetMapping(path = "")
    public ListResponse<User> list() {
        var users = this.userService.list();

        return new ListResponse<>(users);
    }

    public UsersController(IUserService userService) {
        super();
        this.userService = userService;
    }
}
