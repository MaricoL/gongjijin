package com.ruanzhong.gongjijin.domain;

import javax.persistence.*;
import java.util.List;

@Entity(name = "subproject")
public class SubProject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String subProjectName;
    @OneToMany(mappedBy = "subProject",cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    private List<Table> tables;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public SubProject() {
    }

    public SubProject(String subProjectName, Project project) {
        this.subProjectName = subProjectName;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
