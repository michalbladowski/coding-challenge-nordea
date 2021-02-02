package com.interview.demo.services;

import com.interview.demo.data.PersonRepository;
import com.interview.demo.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="persons")
public class PersonService {
    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Cacheable
    public List<Person> getAllPersons() {
        LOG.debug("Running getAllPersons in PersonService");
        return personRepository.findAll();
    }
}
