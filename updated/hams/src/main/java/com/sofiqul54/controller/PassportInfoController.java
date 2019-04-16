package com.sofiqul54.controller;

import com.sofiqul54.entity.PassportInfo;
import com.sofiqul54.entity.Role;
import com.sofiqul54.repo.PassportInfoRepo;
import com.sofiqul54.repo.PilgrimRepo;
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
@RequestMapping(value = "/passportinfo/")
public class PassportInfoController {

    @Autowired
    private PassportInfoRepo repo;

    @Autowired
    private PilgrimRepo pilgrimRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("passportinfo", new PassportInfo());
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        return "passports/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid PassportInfo passportInfo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "passports/add";
        }
        if (repo.existsByPassportNo(passportInfo.getPassportNo())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            passportInfo.setPassportNo(passportInfo.getPassportNo());
            this.repo.save(passportInfo);
            model.addAttribute("successMsg", "Successfully Saved!");
        }
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        model.addAttribute("passportinfo", new PassportInfo());
        return "passports/add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("passportInfo", repo.getOne(id));
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        return "passports/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid PassportInfo passportInfo, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "passports/edit";
        }
        Optional<PassportInfo> pas = this.repo.findByPassportNo(passportInfo.getPassportNo());
        if (pas.get().getId() != id) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
            return "passports/edit";
        } else {
            passportInfo.setId(id);
            passportInfo.setPassportNo(passportInfo.getPassportNo());
            model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
            this.repo.save(passportInfo);
        }
        return "redirect:/passportinfo/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/passportinfo/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("list", this.repo.findAll());
    return "passports/list";
    }
}