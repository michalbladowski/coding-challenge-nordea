package com.interview.demo.model;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name="PERSON")
public class Person {
    @Id
    @NonNull
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="EMAIL")
    private String email;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="JOINED_DATE")
    private Date joinedDate;
    @Column(name="LAST_NAME")
    private String lastName;

    public Person() {
    }

}
