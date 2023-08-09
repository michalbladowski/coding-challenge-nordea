package com.github.michalbladowski.springdemo.services;

import com.github.michalbladowski.springdemo.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();
}
