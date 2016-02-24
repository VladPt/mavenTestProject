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

public interface UserService {

    public void addUser(User p);

    public void updateUser(User p);

    public List<User> listUsers();

    public List<User> listUsers(int offset, int maxResults);

    public List<User> findUser(String name);

    public User getUserById(int id);

    public void removeUser(int id);

    public Long count();
}
