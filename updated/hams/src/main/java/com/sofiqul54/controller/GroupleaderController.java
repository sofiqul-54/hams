package com.sofiqul54.controller;

import com.sofiqul54.entity.Groupleader;
import com.sofiqul54.jasper.GroupleaderService;
import com.sofiqul54.jasper.MediaTypeUtils;
import com.sofiqul54.repo.GroupleaderRepo;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping(value = "/groupleader/")
public class GroupleaderController {
    @Autowired
    private ImageOptimizer imageOptimizer;

    @Autowired
    private GroupleaderRepo repo;

    @Autowired
    private GroupleaderService groupleaderService;

    @Autowired
    ServletContext context;



    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("groupleader", new Groupleader());
        return "groupleaders/add";
    }

    @PostMapping(value = "add")
    public String userSave(@Valid Groupleader groupleader, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "groupleaders/add";
        } else {
            if (groupleader.getEmail() != null) {

                if (repo.existsByEmail(groupleader.getEmail())) {
                    model.addAttribute("rejectMsg", "Data allready exist");
                } else {
                    groupleader.setRegiDate(new Date());
                    this.repo.save(groupleader);
                    model.addAttribute("groupleader", new Groupleader());
                    model.addAttribute("successMsg", "Congratulations! Data save sucessfully");
                }
            }
        }
        return "groupleaders/add";
    }


    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("groupleader", repo.getOne(id));
        return "groupleaders/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Groupleader groupleader, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "groupleaders/edit";
        }
        Optional<Groupleader> rol = this.repo.findByLeaderName(groupleader.getEmail());
        if (rol.get().getId() != id) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
            return "groupleaders/edit";
        } else {
            groupleader.setRegiDate(new Date());
            groupleader.setId(id);
            this.repo.save(groupleader);
        }
        return "redirect:/groupleader/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/groupleader/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "groupleaders/list";
    }
	
	@RequestMapping(value = "report", method = RequestMethod.GET)
    public void report(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(groupleaderService.report());
        InputStream inputStream = this.getClass().getResourceAsStream("/report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }

    /*@RequestMapping(value = "/pdf", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public void reportPdf(HttpServletResponse response) throws Exception {
        String source = "E:\\J2EE_ALL_(OWN)\\Git_Own\\hams\\hams\\src\\main\\resources\\groupleaderreport.jrxml";
        try {
                JasperCompileManager.compileReportToFile(source);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String sourceFileName = "E:\\J2EE_ALL_(OWN)\\Git_Own\\hams\\hams\\src\\main\\resources\\groupleaderreport1.jasper";

        String printFileName = null;
        String destFileName = "E:\\J2EE_ALL_(OWN)\\Git_Own\\hams\\hams\\src\\main\\resources\\groupleaderreport.pdf";
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(groupleaderService.report());
        Map parameters = new HashMap();
        try {
            printFileName = JasperFillManager.fillReportToFile(sourceFileName,
                    parameters, dataSource);
            if (printFileName != null) {
                JasperExportManager.exportReportToPdfFile(printFileName,
                        destFileName);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }*/

    ////////////////pdf//////////////////////


    public void reportPdf() throws Exception {
        String source = "src\\main\\resources\\report.jrxml";
        try {
            JasperCompileManager.compileReportToFile(source);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String sourceFileName = "src\\main\\resources\\report1.jasper";
        String printFileName = null;
        String destFileName = "src\\main\\resources\\report.pdf";
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(groupleaderService.report());
        Map parameters = new HashMap();
        try {
            printFileName = JasperFillManager.fillReportToFile(sourceFileName,
                    parameters, dataSource);
            if (printFileName != null) {
                JasperExportManager.exportReportToPdfFile(printFileName,
                        destFileName);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("pdf")
    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {
        try {
            reportPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName="src\\main\\resources\\report.pdf";
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.context, fileName);

        File file = new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length())
                .body(resource);
    }
	
}