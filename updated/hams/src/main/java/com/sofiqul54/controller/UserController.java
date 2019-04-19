package com.sofiqul54.controller;


import com.sofiqul54.entity.User;
import com.sofiqul54.jasper.MediaTypeUtils;
import com.sofiqul54.jasper.UserService;
import com.sofiqul54.repo.RoleRepo;
import com.sofiqul54.repo.UserRepo;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



@Controller
@RequestMapping(value = "/user/")
public class UserController {
    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";


    @Autowired
    private ImageOptimizer imageOptimizer;

    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    ServletContext context;

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "users/add";
    }

    @PostMapping(value = "add")
    public String userSave(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "users/add";
        } else {
            if (user.getEmail() != null) {

                if (repo.existsByEmail(user.getEmail())) {
                    model.addAttribute("rejectMsg", "UserName allready exist");
                } else {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setRegiDate(new Date());
                    user.setEnabled(true);
                    user.setConfirmationToken(UUID.randomUUID().toString());
                    this.repo.save(user);
                    model.addAttribute("user", new User());
                    model.addAttribute("successMsg", "Congratulations! Data save sucessfully");
                }
            }
        }
        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "users/add";
    }


    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", repo.getOne(id));
        model.addAttribute("rolelist", this.roleRepo.findAll());
        return "users/edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String userEdit(@Valid User user, BindingResult bindingResult, @PathVariable("id") Long id, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rolelist", this.roleRepo.findAll());
            return "users/edit";
        }
        /*User eUser=repo.findByUserName(user.getUserName());
        eUser.setPassword(passwordEncoder.encode(user.getPassword()));
        eUser.setEnabled(user.isEnabled());
        eUser.setEmail(user.getEmail());
        eUser.setConfirmationToken(UUID.randomUUID().toString());
        eUser.setId(id);*/

        try {
            //////////////////////For Image Upload start /////////////////////
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);
            user.setFileName("new-" + file.getOriginalFilename());
            user.setFileSize(file.getSize());
            // user.setFile(file.getBytes());
            user.setFilePath("/images/" + "new-" + file.getOriginalFilename());
            user.setFileExtension(file.getContentType());
            //////////////////////For Image Upload end/////////////////////

            this.repo.save(user);
            model.addAttribute("user", new User());
            imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.3f, 100, 100);

        }catch (Exception e){
            e.printStackTrace();

        }

        model.addAttribute("rolelist", this.roleRepo.findAll());

        return "redirect:/user/list";
    }

    /*@PostMapping(value = "edit/{id}")
    public String edit(@Valid User user, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        Optional<User> rol = Optional.ofNullable(this.repo.findByUserName(user.getUserName()));
        if (rol.get().getId() != id) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
            return "users/edit";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRegiDate(new Date());
            user.setEnabled(true);
            user.setConfirmationToken(UUID.randomUUID().toString());
            user.setId(id);
            this.repo.save(user);
            model.addAttribute("rolelist", this.roleRepo.findAll());
        }
        return "redirect:/user/list";
    }*/

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/user/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "users/list";
    }

    ////////////////////////////JASPER/////////////////////////////////


    @RequestMapping(value = "userreport", method = RequestMethod.GET)
    public void report(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(userService.report());
        InputStream inputStream = this.getClass().getResourceAsStream("/userreport/report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }
    ////////////////pdf//////////////////////

    //    @RequestMapping(value = "/pdf", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_PDF_VALUE)
    public void userreportPdf() throws Exception {
        String source = "src\\main\\resources\\userreport\\report.jrxml";
        try {
            JasperCompileManager.compileReportToFile(source);
        } catch (JRException e) {
            e.printStackTrace();
        }
        String sourceFileName = "\\src\\main\\resources\\userreport\\report1.jasper";
        String printFileName = null;
        String destFileName = "src\\main\\resources\\userreport\\report.pdf";
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(userService.report());
        Map parameters = new HashMap();
        try {
            printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, dataSource);
            if (printFileName != null) {
                JasperExportManager.exportReportToPdfFile(printFileName, destFileName);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("userpdf")
    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {
        try {
            userreportPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName="src\\\\main\\\\resources\\\\userreport\\\\report.pdf";
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.context, fileName);

        File file=new File(fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
}