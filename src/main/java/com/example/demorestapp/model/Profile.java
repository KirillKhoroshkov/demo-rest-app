package com.example.demorestapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Email
    @NotBlank
    @Column(name = "email")
    private String email;

    @Positive
    @Column(name = "age")
    private Integer age;

    @Column(name = "created", updatable = false)
    private Timestamp created = new Timestamp(System.currentTimeMillis());

    @JsonProperty
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty
    public Timestamp getCreated() {
        return created;
    }

    @JsonIgnore
    public void setCreated(Timestamp created) {
        this.created = created;
    }
}