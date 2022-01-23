package com.solutions.vasylieva.services;

import com.solutions.vasylieva.dao.DaoUser;

import com.solutions.vasylieva.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DaoUser userDao;

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User by login " + login + " not found");
        }
        return user;
    }
}
