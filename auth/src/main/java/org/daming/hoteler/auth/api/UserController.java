package org.daming.hoteler.auth.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.auth.domain.User;
import org.daming.hoteler.auth.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private IUserService userService;

    @PostMapping("")
    public User create(@RequestBody User user) {
        var id  = this.userService.create(user);
        user.setId(id);
        user.setPassword(null);
        user.setPasswordType(null);

        return user;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        var user = this.userService.get(id);
        user.setPassword(null);
        user.setPasswordType(null);

        return user;
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

    public UserController(IUserService userService) {
        this.userService = userService;
    }
}
