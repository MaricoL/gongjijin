package com.ruanzhong.gongjijin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String projectName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "system_id")
    private System system;

    @OneToMany(mappedBy = "project",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<SubProject> subProjects;

    public Project() {
    }

    public Project(String projectName , System system) {
        this.projectName = projectName;
        this.system = system;
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

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }
}
