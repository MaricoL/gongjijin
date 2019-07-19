package com.ruanzhong.gongjijin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {

    @RequestMapping("/show")
    public ModelAndView hello() {

        return new ModelAndView("show/excels");
    }
}
