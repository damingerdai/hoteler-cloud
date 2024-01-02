package org.daming.hoteler.auth.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.service.ITokenService;
import org.daming.hoteler.auth.service.IUserService;
import org.daming.hoteler.common.response.DataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gming001
 * @version 2022-12-08 13:57
 */
@RestController
@RequestMapping("/user")
@Tag(name = "User Controller")
public class UserController {

    private final IUserService userService;
    private final ITokenService tokenService;

    @PostMapping("")
    public User create(@RequestBody User user) {
        var id  = this.userService.create(user);
        user.setId(id);
        user.setPassword(null);
        user.setPasswordType(null);

        return user;
    }

    @GetMapping("/{id}")
    public DataResponse<User>  get(@PathVariable int id) {
        var user = this.userService.get(id);
        user.setPassword(null);
        user.setPasswordType(null);

        return new DataResponse<>(user);
    }

    @GetMapping("")
    public DataResponse<User> get(@RequestHeader(value = "Authorization", required = true) String authorization) {
        var token = authorization.split("Bearer ")[1];
        var user = this.tokenService.verifyToken(token);
        user.setPassword(null);
        user.setPasswordType(null);

        return new DataResponse<>(user);
    }

    @PutMapping("")
    public User update(@RequestBody User user) {
        this.userService.update(user);

        var id  = this.userService.create(user);
        user.setId(id);
        user.setPassword(null);
        user.setPasswordType(null);

        return user;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        this.userService.delete(id);

        return ResponseEntity.ok("ok");
    }

    public UserController(IUserService userService, ITokenService tokenService) {
        super();
        this.userService = userService;
        this.tokenService = tokenService;
    }
}
