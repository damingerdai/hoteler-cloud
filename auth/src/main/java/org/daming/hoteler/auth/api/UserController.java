package org.daming.hoteler.auth.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.domain.request.CreateUserRequest;
import org.daming.hoteler.auth.service.ITokenService;
import org.daming.hoteler.auth.service.IUserService;
import org.daming.hoteler.common.errors.IErrorService;
import org.daming.hoteler.common.exceptions.ExceptionBuilder;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.common.response.DataResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

    private final IErrorService errorService;

    @PostMapping("")
    public DataResponse<User> create(@RequestBody CreateUserRequest cur) {
        if (Objects.isNull(cur.getPasswordType())) {
            cur.setPasswordType("noop");
        }
        var id  = this.userService.create(cur);
        var user = this.userService.get(id);
        user.setPassword(null);
        user.setPasswordType(null);

        return new DataResponse<>(user);
    }

    @GetMapping("/{id}")
    public DataResponse<User> get(@PathVariable int id) {
        var user = this.userService.get(id);
//        user.setPassword(null);
//        user.setPasswordType(null);

        return new DataResponse<>(user);
    }

    @GetMapping("")
    public DataResponse<User> get(@RequestHeader(value = "Authorization", required = false) String authorization, @RequestParam(required = false) String username) throws HotelerException {
        try {
            if (Objects.nonNull(authorization)) {
                var token = authorization.split("Bearer ")[1];
                var user = this.tokenService.verifyToken(token);
//                user.setPassword(null);
//                user.setPasswordType(null);

                return new DataResponse<>(user);
            } else if (Objects.nonNull(username)) {
                var user = this.userService.getUserByUsername(username);
//                user.setPassword(null);
//                user.setPasswordType(null);

                return new DataResponse<>(user);
            }

            throw ExceptionBuilder.buildException(600011, "Bad request error: authorization or username is required");
        } catch (HotelerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw this.errorService.createHotelerSystemException(ex.getMessage(), ex);
        }

    }

    @PutMapping("")
    public DataResponse<User> update(@RequestBody User user) {
        this.userService.update(user);

        return new DataResponse<>(null);
    }

    @DeleteMapping("/{id}")
    public DataResponse<String> delete(@PathVariable int id) {
        this.userService.delete(id);

        return new DataResponse<>("ok");
    }

    public UserController(IUserService userService, ITokenService tokenService, IErrorService errorService) {
        super();
        this.userService = userService;
        this.tokenService = tokenService;
        this.errorService = errorService;
    }
}
