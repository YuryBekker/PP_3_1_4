package ru.kata.pp_3_1_4.services;

import org.springframework.stereotype.Service;
import ru.kata.pp_3_1_4.models.Role;
import ru.kata.pp_3_1_4.repositories.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void create(Role role) {
        roleRepository.save(role);
    }

    public List<Role> rolesWithoutPrefix() {
        return roleRepository.findAll().stream().map(w -> new Role(w.getId(), w.getName().substring(5))).collect(Collectors.toList());
    }

    public String getRoleToString(Role role) {
        return role.getName().substring(5);
    }

    public String getRoleToString(String role) {
        return role.substring(5);
    }

    public String getRolesToString(List<Role> roles) {
        return roles.stream().map(t -> t.getName().substring(5)).collect(Collectors.joining(", "));
    }

}
