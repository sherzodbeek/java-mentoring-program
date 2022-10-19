package com.epam.solrtask.model;

import java.util.Map;

public class Facet {
    private Long valueCount;
    private String value;
    private Map<String, String> field;
    private Map<String, String> key;

    public Facet(Long valueCount, String value, Map<String, String> field, Map<String, String> key) {
        this.valueCount = valueCount;
        this.value = value;
        this.field = field;
        this.key = key;
    }

    public Facet() {
    }

    public Long getValueCount() {
        return valueCount;
    }

    public void setValueCount(Long valueCount) {
        this.valueCount = valueCount;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, String> getField() {
        return field;
    }

    public void setField(Map<String, String> field) {
        this.field = field;
    }

    public Map<String, String> getKey() {
        return key;
    }

    public void setKey(Map<String, String> key) {
        this.key = key;
    }
}
