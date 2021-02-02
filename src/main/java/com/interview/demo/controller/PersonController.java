package com.interview.demo.controller;

import com.interview.demo.model.Person;
import com.interview.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PersonController
 *
 * Additional controller that handles direct requests for Person-type objects
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }
}
