package com.solutions.vasylieva.services;


import com.solutions.vasylieva.dao.DaoRole;
import com.solutions.vasylieva.dao.DaoUser;
import com.solutions.vasylieva.dto.UserAddDTO;
import com.solutions.vasylieva.model.Role;
import com.solutions.vasylieva.model.User;
import com.solutions.vasylieva.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private DaoRole roleDao;

    @Autowired
    private DaoUser userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userDao.findById(id);
    }

    public boolean create(UserAddDTO user) {
        User userToAdd = UserUtil.toUser(user);
        boolean exists = checkExistingUser(userToAdd);
        if (exists) {
            return false;
        }
        Role role = roleDao.findByName(user.getRole().getName());
        userToAdd.setRole(role);
        userToAdd.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.create(userToAdd);
        return true;
    }

    public void update(User user) {
        Role role = roleDao.findByName(user.getRole().getName());
        User userById = userDao.findById(user.getId());
        if (!user.getPassword().isEmpty()) {
            userById.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userById.setLogin(user.getLogin());
        userById.setEmail(user.getEmail());
        userById.setFirstName(user.getFirstName());
        userById.setLastName(user.getLastName());
        userById.setBirthday(user.getBirthday());
        userById.setRole(role);
        userDao.update(userById);
    }

    public void remove(User user) {
        userDao.remove(user);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public boolean checkExistingUser(User user) {
        User userByLogin = userDao.findByLogin(user.getLogin());
        return userByLogin != null;
    }
}
