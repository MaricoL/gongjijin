package com.ruanzhong.gongjijin.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "_table")
public class Table {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String tableName;
    @JsonIgnore
    private String description;
    @JsonIgnore
    private Date recordDate;
    @JsonIgnore
    private String manager;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sub_project_id")
    private SubProject subProject;

    @JsonIgnore
    @ManyToMany(mappedBy = "tables")
    private List<Field> fields;

    public Table() {
    }

    public Table(String tableName, String description, SubProject subProject, String manager, Date recordDate) {
        this.tableName = tableName;
        this.description = description;
        this.subProject = subProject;
        this.manager = manager;
        this.recordDate = recordDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubProject getSubProject() {
        return subProject;
    }

    public void setSubProject(SubProject subProject) {
        this.subProject = subProject;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
