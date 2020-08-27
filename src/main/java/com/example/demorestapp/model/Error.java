package com.example.demorestapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "error")
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "message")
    private String message;

    @Column(name = "created", updatable = false)
    private Timestamp created = new Timestamp(System.currentTimeMillis());

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("msg")
    public String getMessage() {
        return message;
    }

    @JsonProperty("msg")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty
    public Timestamp getCreated() {
        return created;
    }

    @JsonIgnore
    private void setCreated(Timestamp created) {
        this.created = created;
    }
}