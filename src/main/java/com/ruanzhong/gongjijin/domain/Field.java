package com.ruanzhong.gongjijin.domain;

import javax.persistence.*;
import java.util.List;

@Entity(name = "field")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer sequence;
    private String fieldName;
    private String fieldType;
    private String length;
    private String digits;
    private String keyType;
    private String defaultValue;
    private String isNull;
    private String dictionaryName;
    private String description;
    private String comments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "table_field", joinColumns = {@JoinColumn(name = "field_id")}, inverseJoinColumns = {@JoinColumn(name = "table_id")})
    private List<Table> tables;

    public Field() {
    }

    public Field(Integer sequence, String fieldName, String fieldType, String length, String digits, String keyType, String defaultValue, String isNull, String dictionaryName, String description, String comments, List<Table> tables) {
        this.sequence = sequence;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.length = length;
        this.digits = digits;
        this.keyType = keyType;
        this.defaultValue = defaultValue;
        this.isNull = isNull;
        this.dictionaryName = dictionaryName;
        this.description = description;
        this.comments = comments;
        this.tables = tables;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDigits() {
        return digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
