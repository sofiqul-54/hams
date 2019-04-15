package com.sofiqul54.controller;

import com.sofiqul54.entity.AccountHead;
import com.sofiqul54.repo.AccountHeadRepo;
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
        if (repo.existsAccountHeadByAccountname(accountHead.getAccountname())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            accountHead.setAccountname(accountHead.getAccountname().toUpperCase());
            this.repo.save(accountHead);
            model.addAttribute("successMsg", "Successfully Saved!");
        }
        return "redirect:/achead/add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("accountheadinfo", repo.getOne(id));
        return "accountheads/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid AccountHead accountHead, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "accountheads/edit";
        }
        Optional<AccountHead> ached = this.repo.findByAccountname(accountHead.getAccountname());
        if (ached.get().getId() != id) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
            return "accountheads/edit";
        } else {
            accountHead.setId(id);
            accountHead.setAccountname(accountHead.getAccountname().toUpperCase());
            this.repo.save(accountHead);
        }
        return "redirect:/achead/list";
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
    return "accountheads/list";
    }
}