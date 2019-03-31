package com.sofiqul54.controller;

import com.sofiqul54.entity.AccountHead;
import com.sofiqul54.entity.Role;
import com.sofiqul54.repo.AccountHeadRepo;
import com.sofiqul54.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/achead/")
public class AccountHeadController {

    @Autowired
    private AccountHeadRepo repo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("accountheadinfo", new AccountHead());
        return "accountheads/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid AccountHead accountHead, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "accountheads/add";
        }
        if (repo.existsByAccountname(accountHead.getAccountname())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            accountHead.setAccountname(accountHead.getAccountname().toUpperCase());
            this.repo.save(accountHead);
            model.addAttribute("successMsg", "Successfully Saved!");
        }
        return "accountheads/add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("role", repo.getOne(id));
        return "roles/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Role role, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "roles/edit";
        }
        Optional<Role> rol = this.repo.findByRoleName(role.getRoleName());
        if (rol.get().getId() != id) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
            return "roles/edit";
        } else {
            role.setId(id);
            role.setRoleName(role.getRoleName().toUpperCase());
            this.repo.save(role);
        }
        return "redirect:/role/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/role/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("list", this.repo.findAll());
    return "roles/list";
    }
}