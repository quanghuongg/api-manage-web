package com.mange.api.service.impl;

import com.mange.api.entity.Role;
import com.mange.api.entity.User;
import com.mange.api.entity.UserRole;
import com.mange.api.mapper.UserMapper;
import com.mange.api.service.ManagerService;
import com.mange.api.uitls.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.plaf.ListUI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service(value = "managerService")
public class ManagerServiceImpl implements ManagerService, UserDetailsService {
    private UserMapper userMapper;

    public ManagerServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User [" + username + "] not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private Set<SimpleGrantedAuthority> getAuthorities(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userMapper.findRoleByUserId(user.getId()).getName()));
        return authorities;
    }

    @Override
    public List<User> getAllUser(int type) {
        List<User> users = new ArrayList<>();
        List<User> list = userMapper.findUserAll();
        for (User user : list) {
            Role role = userMapper.findRoleByUserId(user.getId());
            if (role.getId() == type) {
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public int save(User user) {
        user.setPassword(ServiceUtils.encodePassword(user.getPassword()));
        user.setStatus(1);
        user.setCreated(System.currentTimeMillis());
        userMapper.insertUser(user);
        //role_id =3 : ADMIN
        user.setRole(userMapper.findRoleById(3));
        UserRole userRole = new UserRole(user.getId(), 3);
        userMapper.insertUserRole(userRole);
        log.info("Create user admin {} success!", user.getUsername());
        return user.getId();
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findUserByName(username);
    }



}
