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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.maventestproject.User;
import com.mycompany.maventestproject.UserService;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService ps) {
        this.userService = ps;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model, Integer offset, Integer maxResults) {
        model.addAttribute("user", new User());
        if (offset == null) {
            offset = 0;
            model.addAttribute("listUsers", this.userService.listUsers());
        } else {
            if (maxResults == null) {
                maxResults = 10;
            }
            model.addAttribute("listUsers", this.userService.listUsers(offset, maxResults));
        }
        model.addAttribute("count", userService.count());
        model.addAttribute("offset", offset);
        return "user";
    }

    //For add and update user both
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User p) {

        if (p.getId() == null || p.getId() == 0) {
            //new user, add it
            this.userService.addUser(p);
        } else {
            //existing user, call update
            this.userService.updateUser(p);
        }

        return "redirect:/users";

    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {

        this.userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model, Integer offset, Integer maxResults) {
        model.addAttribute("user", this.userService.getUserById(id));
        if (offset == null) {
            offset = 0;
            model.addAttribute("listUsers", this.userService.listUsers());
        } else {
            if (maxResults == null) {
                maxResults = 10;
            }
            model.addAttribute("listUsers", this.userService.listUsers(offset, maxResults));
        }
        model.addAttribute("count", userService.count());
        model.addAttribute("offset", offset);
        return "user";
    }
    
    @RequestMapping(value = "/user/find", method = RequestMethod.GET)
    public String addUser(Model model, String name) {
        if(name == null || name.length() == 0){
            return "redirect:/users";
        }
        User usr = new User();
        List<User> result = this.userService.findUser(name);
        model.addAttribute("searchFor", " searching for '"+name+"'");
        model.addAttribute("user", usr);
        model.addAttribute("listUsers", result);
        model.addAttribute("count", result.size());
        model.addAttribute("offset", 0);
        return "user";
    }

}
