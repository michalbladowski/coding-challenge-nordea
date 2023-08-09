package com.github.michalbladowski.springdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@Entity
@Table(name="PERSON")
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="EMAIL")
    private String email;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="JOINED_DATE")
    private LocalDate joinedDate;

    @Column(name="LAST_NAME")
    private String lastName;
}
