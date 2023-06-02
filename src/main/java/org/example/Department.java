package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, // to sie daje tu gdzie lista
    mappedBy = "department")//po nazwie pola
    private List<Employee> employees;

    public Department() { //Entity potrzebuje pustego constructora
    }

    public Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
