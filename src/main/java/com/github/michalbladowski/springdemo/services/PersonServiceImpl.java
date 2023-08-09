package com.github.michalbladowski.springdemo.services;

import com.github.michalbladowski.springdemo.data.PersonRepository;
import com.github.michalbladowski.springdemo.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="persons")
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    private final PersonRepository personRepository;

    @Cacheable
    public List<Person> getAllPersons() {
        log.debug("Running getAllPersons in PersonService");
        return personRepository.findAll();
    }
}
