package com.github.michalbladowski.springdemo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.michalbladowski.springdemo.controller.PersonController;
import com.github.michalbladowski.springdemo.model.Person;
import com.github.michalbladowski.springdemo.services.PersonService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

@WebMvcTest(PersonController.class)
@WithMockUser
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void getPersonsTest() throws Exception {
        when(personService.getAllPersons()).thenReturn(Collections.singletonList(createPerson()));
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.equalTo("Joe")));
    }

    private Person createPerson() {
        return Person.builder()
                .id(123L)
                .email("c@example.com")
                .firstName("Joe")
                .lastName("Black")
                .joinedDate(LocalDate.now())
                .build();
    }
}
