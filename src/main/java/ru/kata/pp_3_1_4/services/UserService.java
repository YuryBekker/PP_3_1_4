package ru.kata.pp_3_1_4.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.pp_3_1_4.models.User;
import ru.kata.pp_3_1_4.models.views.ViewUser;
import ru.kata.pp_3_1_4.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean userIsRoles(String username, String role) {
        return userRepository.findByUsername(username).getRoles().stream().anyMatch(t -> roleService.getRoleToString(t.getName()).equals(role));
    }

    public ViewUser viewUser(long id) {
        User user = userRepository.findById(id).get();
        return new ViewUser(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roleService.getRolesToString(user.getRoles()));
    }

    public List<ViewUser> viewUsers() {
        return userRepository.findAll().stream().map(t ->
                new ViewUser(
                        t.getId(),
                        t.getUsername(),
                        t.getEmail(),
                        roleService.getRolesToString(t.getRoles()))).toList();
    }

    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void edit(User user) {
        User findUser = userRepository.findById(user.getId()).orElse(null);
        if (findUser != null) {
            findUser.setUsername(user.getUsername());
            findUser.setPassword(passwordEncoder.encode(user.getPassword()));
            findUser.setEmail(user.getEmail());
            findUser.setRoles(user.getRoles());
            userRepository.save(findUser);
        }
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
