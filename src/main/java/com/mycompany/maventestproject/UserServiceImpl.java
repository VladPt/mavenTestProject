/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventestproject;

/**
 *
 * @author Azog
 */
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.maventestproject.controllers.UserDAO;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void addUser(User p) {
        this.userDAO.addUser(p);
    }

    @Override
    @Transactional
    public void updateUser(User p) {
        this.userDAO.updateUser(p);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return this.userDAO.listUsers();
    }

    @Override
    @Transactional
    public List<User> listUsers(int offset, int maxResults) {
        return this.userDAO.listUsers(offset, maxResults);
    }

    @Override
    @Transactional
    public List<User> findUser(String name) {
        return this.userDAO.findUser(name);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDAO.removeUser(id);
    }

    @Override
    @Transactional
    public Long count() {
        return this.userDAO.count();
    }
}
