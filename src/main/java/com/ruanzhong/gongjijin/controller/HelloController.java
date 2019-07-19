package com.ruanzhong.gongjijin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruanzhong.gongjijin.domain.Project;
import com.ruanzhong.gongjijin.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping("/show")
    public ModelAndView hello() {
        return new ModelAndView("show/excels");
    }

    @RequestMapping("/projects")
    public String getProjects() throws JsonProcessingException {
        List<Project> projectList = projectRepository.findAll();
        String s = new ObjectMapper().writeValueAsString(projectList);
        String s1 = s.replaceAll("projectName", "name")
                .replaceAll("subProjects", "children")
                .replaceAll("subProjectName", "name")
                .replaceAll("tables", "children")
                .replaceAll("tableName", "name");
        return s1;
    }
}
