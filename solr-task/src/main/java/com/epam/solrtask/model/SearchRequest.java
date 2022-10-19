package com.epam.solrtask.model;

public class SearchRequest {
    private String field;
    private String value;
    private String facetField;
    private boolean fullText;
    private String q;

    public SearchRequest(String field, String value, String facetField, boolean fullText, String q) {
        this.field = field;
        this.value = value;
        this.facetField = facetField;
        this.fullText = fullText;
        this.q = q;
    }

    public SearchRequest() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFacetField() {
        return facetField;
    }

    public void setFacetField(String facetField) {
        this.facetField = facetField;
    }

    public boolean getFullText() {
        return fullText;
    }

    public void setFullText(boolean fullText) {
        this.fullText = fullText;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
