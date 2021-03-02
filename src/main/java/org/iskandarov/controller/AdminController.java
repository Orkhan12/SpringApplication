package org.iskandarov.controller;

import org.iskandarov.entities.Role;
import org.iskandarov.entities.User;
import org.iskandarov.repositories.RoleRepository;
import org.iskandarov.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userRepository.findAll());
        model.addAttribute("listRole", this.roleRepository.findAll());
        return "admin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUsers(@ModelAttribute("admin") User user, @RequestParam(value = "select_role", required = false) Integer role) {
        Set<Role> set = new HashSet<>();
        if (role == null) {

            set.add(this.roleRepository.getOne(2));
        } else {
            set.add(this.roleRepository.getOne(role));
        }
        user.setRoles(set);
        if (user.getId() == null) {
            this.userRepository.saveAndFlush(user);
        } else {
            this.userRepository.saveAndFlush(user);
        }
        return "redirect:/admin";
    }

//    @RequestMapping("remove/{id}")
//    public String removeUser(@PathVariable("id") int id, @ModelAttribute("user") User user) {
//        this.userService.removeUser(id);
//
//        return "redirect:/admin";
//    }
//
//    @RequestMapping("edit/{id}")
//    public String editUser(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", this.userService.getUserById(id));
//        model.addAttribute("listUsers", this.userService.listUser());
//        model.addAttribute("listRole", this.userService.listRole());
//
//        return "admin";
//
//    }
//
//    @RequestMapping("userdata/{id}")
//    public String userData(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", this.userService.getUserById(id));
//
//        return "userdata";
//
//    }

}
