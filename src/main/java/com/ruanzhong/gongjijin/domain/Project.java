package com.ruanzhong.gongjijin.domain;

import javax.persistence.*;
import java.util.List;

@Entity(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String projectName;

    @OneToMany(mappedBy = "project",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<SubProject> subProjects;

    public Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<SubProject> getSubProjects() {
        return subProjects;
    }

    public void setSubProjects(List<SubProject> subProjects) {
        this.subProjects = subProjects;
    }


}
