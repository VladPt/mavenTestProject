/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventestproject.controllers;

/**
 *
 * @author Azog
 */
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.maventestproject.User;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addUser(User p) {
        Session session = this.sessionFactory.getCurrentSession();
        logger.info("User saved successfully, User Details=" + p);
        session.persist(p);
        logger.info("User saved successfully, User Details=" + p);
    }

    @Override
    public void updateUser(User p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("User updated successfully, User Details=" + p);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> usersList = session.createCriteria(User.class).setFirstResult(0).setMaxResults(10).list();
        for (User p : usersList) {
            logger.info("User List::" + p);
        }
        return usersList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listUsers(int offset, int maxResults) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> usersList = session.createCriteria(User.class).setFirstResult(offset).setMaxResults(maxResults).list();
        for (User p : usersList) {
            logger.info("User List::" + p);
        }
        return usersList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findUser(String name) {
        if (name == null) {
            name = "";
        }
        Session session = this.sessionFactory.getCurrentSession();
        List<User> usersList = session.createQuery(" FROM User WHERE lower(name) LIKE '%" + name.toLowerCase() + "%'").list();
        logger.info("User loaded successfully, Users found =" + usersList.size());
        return usersList;
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User p = (User) session.load(User.class, new Integer(id));
        logger.info("User loaded successfully, User details=" + p);
        return p;
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User p = (User) session.load(User.class, new Integer(id));
        if (null != p) {
            session.delete(p);
        }
        logger.info("User deleted successfully, user details=" + p);
    }

    @Override
    public Long count() {
        return (Long) sessionFactory.openSession()
                .createCriteria(User.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

}
