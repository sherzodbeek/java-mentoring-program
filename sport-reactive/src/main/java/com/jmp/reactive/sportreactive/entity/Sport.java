package com.jmp.reactive.sportreactive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "sport")
@JsonIgnoreProperties(value = "new")
public class Sport implements Persistable<Integer> {
    @Id
    private Integer id;

    @Column
    private String name;

    @Transient
    private boolean isNewSport;

    public Sport() {
    }

    public Sport(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sport(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public boolean isNewSport() {
        return isNewSport;
    }

    @Override
    public boolean isNew() {
        return this.isNewSport || id == null;
    }

    public Sport setAsNew() {
        this.isNewSport = true;
        return this;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
