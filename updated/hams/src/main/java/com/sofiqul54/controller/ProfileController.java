package com.sofiqul54.controller;



import com.sofiqul54.entity.User;
import com.sofiqul54.repo.RoleRepo;
import com.sofiqul54.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;


    @GetMapping(value = "/profile")
    public String profileView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username",auth.getName());
        User user=this.repo.findByUserName(auth.getName());
        model.addAttribute("name", user.getUserName());
        model.addAttribute("user", user);

        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "profile";

    }


}
