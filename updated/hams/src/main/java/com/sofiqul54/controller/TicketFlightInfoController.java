package com.sofiqul54.controller;

import com.sofiqul54.entity.PassportInfo;
import com.sofiqul54.entity.TicketFlightInfo;
import com.sofiqul54.repo.PassportInfoRepo;
import com.sofiqul54.repo.PilgrimRepo;
import com.sofiqul54.repo.TicketFlightRepo;
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
@RequestMapping(value = "/ticketflightinfo/")
public class TicketFlightInfoController {

    @Autowired
    private TicketFlightRepo repo;

    @Autowired
    private PilgrimRepo pilgrimRepo;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("ticketflightinfo", new TicketFlightInfo());
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        return "ticketflights/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid TicketFlightInfo ticketFlightInfo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ticketflights/add";
        }
        if (repo.existsByTicketNo(ticketFlightInfo.getTicketNo())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
        } else {
            ticketFlightInfo.setTicketNo(ticketFlightInfo.getTicketNo());
            this.repo.save(ticketFlightInfo);
            model.addAttribute("successMsg", "Successfully Saved!");
        }
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        return "redirect:/ticketflightinfo/list";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("ticketflightinfo", repo.getOne(id));
        model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
        return "ticketflights/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid TicketFlightInfo ticketFlightInfo, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "ticketflights/edit";
        }
        Optional<TicketFlightInfo> tic = this.repo.findByTicketNo(ticketFlightInfo.getTicketNo());
        if (tic.get().getId() != id) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
            return "ticketflights/edit";
        } else {
            ticketFlightInfo.setId(id);
            ticketFlightInfo.setTicketNo(ticketFlightInfo.getTicketNo());
            model.addAttribute("pilgrimlist", this.pilgrimRepo.findAll());
            this.repo.save(ticketFlightInfo);
        }
        return "redirect:/ticketflightinfo/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/ticketflightinfo/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
        model.addAttribute("list", this.repo.findAll());
    return "ticketflights/list";
    }
}