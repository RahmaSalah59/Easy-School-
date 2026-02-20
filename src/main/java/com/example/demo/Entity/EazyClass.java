package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class")

public class EazyClass extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int ID;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "eazyClass" , fetch = FetchType.LAZY , cascade = CascadeType.ALL, targetEntity = Person.class)
    @ToString.Exclude
    private List<Person> person_list = new ArrayList<>();


}
