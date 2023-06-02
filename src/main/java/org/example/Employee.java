package org.example;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees") // możesz nazwać tabelę
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column
    private String name;
    @Column
    private LocalDate birthday;
    @Column
    private String email;

    @ManyToOne(fetch = FetchType.EAGER) // Employee podpinamy pod Department, bo employee bedzie mial do którego departamentu przynalezy, a nie departament do którego pracownika przynalezy
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {
    }

    public Employee(String name, LocalDate birthday, String email) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                '}';
    }

}
