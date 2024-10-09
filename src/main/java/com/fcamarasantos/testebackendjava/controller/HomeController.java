package com.fcamarasantos.testebackendjava.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeController {

    @Value("${springdoc.swagger-ui.path}")
    private String documentationUrl;

    @GetMapping
    public ModelAndView redirectToDocumentation() {
        return new ModelAndView("redirect:" + documentationUrl);
    }
}
