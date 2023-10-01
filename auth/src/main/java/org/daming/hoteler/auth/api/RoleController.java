package org.daming.hoteler.auth.api;

/**
 * @author gming001
 * @version 2023-10-01 12:15
 */

import io.swagger.v3.oas.annotations.tags.Tag;
import org.daming.hoteler.auth.domain.Role;
import org.daming.hoteler.auth.service.IRoleService;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Role Controller")
public class RoleController {

    private IRoleService roleService;

    @GetMapping("roles")
    public List<Role> list() throws HotelerException {
        return this.roleService.list();
    }

    public RoleController(IRoleService roleService) {
        super();
        this.roleService = roleService;
    }
}
