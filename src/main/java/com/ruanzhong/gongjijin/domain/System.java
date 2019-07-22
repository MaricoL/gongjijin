package com.ruanzhong.gongjijin.domain;

import javax.persistence.*;
import java.util.List;

@Entity(name = "system")
public class System {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String systemName;
    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> projects;

    public System() {
    }

    public System(String systemName) {
        this.systemName = systemName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
