package org.daming.hoteler.user.api;

import org.daming.hoteler.user.domain.User;
import org.daming.hoteler.user.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gming001
 * @version 2022-12-08 13:57
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    @PostMapping("")
    public User create(@RequestBody User user) {
        var id  = this.userService.create(user);
        user.setId(id);
        user.setPassword("");

        return user;
    }

    public UserController(IUserService userService) {
        this.userService = userService;
    }
}
