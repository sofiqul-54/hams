package com.sofiqul54.controller;

import com.sofiqul54.entity.PassportInfo;
import com.sofiqul54.entity.VisaInfo;
import com.sofiqul54.repo.PassportInfoRepo;
import com.sofiqul54.repo.PilgrimRepo;
import com.sofiqul54.repo.VisaInfoRepo;
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
@RequestMapping(value = "/visainfo/")
public class VisaInfoController {

    @Autowired
    private VisaInfoRepo repo;

    @Autowired
    private PilgrimRepo pilgrimRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("visainfo", new VisaInfo());
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        return "visas/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid VisaInfo visaInfo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "visas/add";
        }
        if (repo.existsByVisaNo(visaInfo.getVisaNo())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            visaInfo.setVisaNo(visaInfo.getVisaNo());
            this.repo.save(visaInfo);
            model.addAttribute("successMsg", "Successfully Saved!");
        }
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        return "visas/add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("visaInfo", repo.getOne(id));
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        return "visas/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid VisaInfo visaInfo, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "visas/edit";
        }
        Optional<VisaInfo> pas = this.repo.findByVisaNo(visaInfo.getVisaNo());
        if (pas.get().getId() != id) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
            return "visas/edit";
        } else {
            visaInfo.setId(id);
            visaInfo.setVisaNo(visaInfo.getVisaNo());
            model.addAttribute("visainfo", new VisaInfo());
            model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
            this.repo.save(visaInfo);
        }
        return "redirect:/visainfo/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/visainfo/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("list", this.repo.findAll());
    return "visas/list";
    }
}