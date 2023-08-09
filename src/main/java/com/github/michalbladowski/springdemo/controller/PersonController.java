package com.github.michalbladowski.springdemo.controller;

import com.github.michalbladowski.springdemo.model.Person;
import com.github.michalbladowski.springdemo.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PersonController
 *
 * Additional controller that handles direct requests for Person-type objects
 */
@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    @Autowired
    private final PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Add new airplane")
    @ApiResponse(responseCode = "200", description = "All persisted objects of type Person")
    @ApiResponse(responseCode = "404", description = "Content not found")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> allPersonsResponse = personService.getAllPersons();
        if(!allPersonsResponse.isEmpty()) {
            return ResponseEntity.ok(allPersonsResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
