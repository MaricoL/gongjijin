package com.ruanzhong.gongjijin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruanzhong.gongjijin.domain.Field;
import com.ruanzhong.gongjijin.domain.System;
import com.ruanzhong.gongjijin.domain.Table;
import com.ruanzhong.gongjijin.repository.FieldRepository;
import com.ruanzhong.gongjijin.repository.ProjectRepository;
import com.ruanzhong.gongjijin.repository.SystemRepository;
import com.ruanzhong.gongjijin.repository.TableRepository;
import com.ruanzhong.gongjijin.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ShowController {

    @Autowired
    private SystemRepository systemRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private TableRepository tableRepository;

    @RequestMapping("/show")
    public ModelAndView hello() {
        return new ModelAndView("show/excels", "models", null);
    }

    @RequestMapping("/projects")
    public String getProjects() throws JsonProcessingException {
        List<System> systemList = systemRepository.findAll();
        String s = new ObjectMapper().writeValueAsString(systemList);
        String s1 = s.replaceAll("systemName", "name")
                .replaceAll("projects", "children")
                .replaceAll("projectName", "name")
                .replaceAll("subProjects", "children")
                .replaceAll("subProjectName", "name")
                .replaceAll("tables", "children")
                .replaceAll("tableName", "name");

        return s1;
    }


    @RequestMapping("/getFields/{tableName}/{subProjectId}")
    public List<Field> getFields(@PathVariable String tableName, @PathVariable Integer subProjectId) {
        Table table = tableRepository.findByTableName(tableName, subProjectId);
        List<Field> fieldList = fieldRepository.getFieldsByTableId(table.getId());
        List<Field> redList = fieldRepository.findLikeString("用","用户","商用","已用","领用","费用");
        AjaxResult ajaxResult = new AjaxResult(fieldList);
        return fieldList;

    }
}
