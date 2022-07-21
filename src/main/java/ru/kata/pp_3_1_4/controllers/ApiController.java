package ru.kata.pp_3_1_4.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.pp_3_1_4.models.User;
import ru.kata.pp_3_1_4.models.views.ViewStartup;
import ru.kata.pp_3_1_4.models.views.ViewUser;
import ru.kata.pp_3_1_4.services.RoleService;
import ru.kata.pp_3_1_4.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiController {
    final UserService userService;
    final RoleService roleService;

    public ApiController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/api/startup")
    public ViewStartup admin(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return new ViewStartup(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(t -> t.getName().substring(5)).collect(Collectors.joining(", ")),
                userService.userIsRoles(user.getUsername(), "ADMIN"),
                roleService.rolesWithoutPrefix());
    }


    @GetMapping("/api/admin/users")
    public List<ViewUser> users() {
        return userService.viewUsers();
    }

    @GetMapping("/api/profile")
    public ViewUser profile(@RequestParam(name = "id") long userId) {
        return userService.viewUser(userId);
    }

    @PostMapping("/api/admin/edit")
    public void edit(User user) {
        userService.edit(user);
    }

    @PostMapping("/api/admin/delete")
    public void delete(long id) {
        userService.delete(id);
    }

    @PostMapping("/api/admin/create")
    public void create(User user) {
        userService.create(user);
    }
}