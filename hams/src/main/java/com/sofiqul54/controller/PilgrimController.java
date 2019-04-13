package com.sofiqul54.controller;


import com.sofiqul54.entity.BookingSummary;
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
@RequestMapping(value = "/pilgrim/")
public class PilgrimController {
    @Autowired
    private ImageOptimizer imageOptimizer;

    @Autowired
    private PilgrimRepo repo;

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private GroupleaderRepo groupleaderRepo;
@Autowired
private BookingSummaryRepo bookingSummaryRepo;

    @GetMapping(value = "add")
    public String viewAdd(Pilgrim pilgrim, Model model) {
        model.addAttribute("packagelist", this.packageRepo.findAll());
        model.addAttribute("grouplist", this.groupleaderRepo.findAll());
        return "pilgrims/add";
    }

    @PostMapping(value = "add")
    public String userSave(@Valid Pilgrim pilgrim, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pilgrims/add";
        } else {
            if (pilgrim.getEmail() != null) {

                if (repo.existsByEmail(pilgrim.getEmail())) {
                    model.addAttribute("rejectMsg", "UserName allready exist");
                } else {
                    pilgrim.setRegiDate(new Date());
                    this.repo.save(pilgrim);
                    double due= pilgrim.getPpackage().getPrice() - pilgrim.getBookingAmount();
                    double aol= pilgrim.getBookingAmount();

                    BookingSummary bookingSummary=new BookingSummary(pilgrim.getPpackage().getPrice(), pilgrim.getBookingAmount(), due, pilgrim,pilgrim.getPpackage());
                    bookingSummaryRepo.save(bookingSummary);
                    model.addAttribute("pilgrim", new Pilgrim());
                    model.addAttribute("successMsg", "Congratulations! Data save sucessfully");
                }
            }
        }
        model.addAttribute("packagelist", this.packageRepo.findAll());
        model.addAttribute("grouplist", this.groupleaderRepo.findAll());
        return "pilgrims/add";
    }


    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("pilgrim", repo.getOne(id));
        model.addAttribute("packagelist", this.packageRepo.findAll());
        model.addAttribute("grouplist", this.groupleaderRepo.findAll());

        return "pilgrims/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Pilgrim pilgrim, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "pilgrims/edit";
        }
        Optional<Pilgrim> pil = this.repo.findByEmail(pilgrim.getEmail());
        if (pil.get().getId() != id) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
            return "pilgrims/edit";
        } else {
            pilgrim.setRegiDate(new Date());
            pilgrim.setId(id);
            this.repo.save(pilgrim);
            model.addAttribute("packagelist", this.packageRepo.findAll());
            model.addAttribute("grouplist", this.groupleaderRepo.findAll());
        }
        return "redirect:/pilgrim/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/pilgrim/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "pilgrims/list";
    }
}