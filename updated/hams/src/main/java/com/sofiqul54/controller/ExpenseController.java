package com.sofiqul54.controller;


import com.sofiqul54.entity.Exprnses;
import com.sofiqul54.entity.Income;
import com.sofiqul54.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(value = "/expense/")
public class ExpenseController {
    @Autowired
    private ExpenseRepo repo;

    @Autowired
    private IncomeRepo incomeRepo;

    @Autowired
    private AccountHeadRepo accountHeadRepo;


    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("expense", new Exprnses());
        model.addAttribute("accountheadlist", this.accountHeadRepo.findAll());
        return "expenses/add";
    }

    @PostMapping(value = "add")
    public String userSave(@Valid Exprnses exprnses, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "expenses/add";
        } else {
            if (exprnses.getId() != null) {
                exprnses.setpDate(new Date());
                this.repo.save(exprnses);
                model.addAttribute("expense", new Exprnses());
                model.addAttribute("successMsg", "Congratulations! Data save sucessfully");
            }
        }

        model.addAttribute("accountheadlist", this.accountHeadRepo.findAll());
        return "expenses/add";
    }


    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("expense", repo.getOne(id));
        model.addAttribute("accountheadlist", this.accountHeadRepo.findAll());
        return "expenses/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Exprnses exprnses, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "expenses/edit";
        }
        this.repo.save(exprnses);
        model.addAttribute("accountheadlist", this.accountHeadRepo.findAll());
        return "redirect:/expense/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/expense/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "expenses/list";
    }
}