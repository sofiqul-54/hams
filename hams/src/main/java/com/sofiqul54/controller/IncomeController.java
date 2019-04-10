package com.sofiqul54.controller;


import com.sofiqul54.entity.Income;
import com.sofiqul54.entity.Pilgrim;
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
import java.util.Optional;

@Controller
@RequestMapping(value = "/income/")
public class IncomeController {

    @Autowired
    private IncomeRepo repo;

    @Autowired
    private PilgrimRepo pilgrimRepo;

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private GroupleaderRepo groupleaderRepo;

    @Autowired
    private AccountHeadRepo accountHeadRepo;


    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("income", new Income());
        model.addAttribute("accountheadlist", this.accountHeadRepo.findAll());
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        model.addAttribute("packagelist", this.packageRepo.findAll());
        model.addAttribute("grouplist", this.groupleaderRepo.findAll());
        return "incomes/add";
    }

    @PostMapping(value = "add")
    public String userSave(@Valid Income income, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "incomes/add";
        } else {
            if (income.getPilgrim() != null){
                income.setcDate(new Date());
                this.repo.save(income);
                model.addAttribute("income", new Income());
                model.addAttribute("successMsg", "Congratulations! Data save sucessfully");
            }
        }

        model.addAttribute("accountheadlist", this.accountHeadRepo.findAll());
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        model.addAttribute("packagelist", this.packageRepo.findAll());
        model.addAttribute("grouplist", this.groupleaderRepo.findAll());
        return "incomes/add";
    }


    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("income", repo.getOne(id));
        model.addAttribute("accountheadlist", this.accountHeadRepo.findAll());
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        model.addAttribute("packagelist", this.packageRepo.findAll());
        model.addAttribute("grouplist", this.groupleaderRepo.findAll());

        return "incomes/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Income income, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "incomes/edit";
        }
        this.repo.save(income);
        model.addAttribute("accountheadlist", this.accountHeadRepo.findAll());
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        model.addAttribute("packagelist", this.packageRepo.findAll());
        model.addAttribute("grouplist", this.groupleaderRepo.findAll());

        return "redirect:/income/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/income/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "incomes/list";
    }
}