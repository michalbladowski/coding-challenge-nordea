package com.github.michalbladowski.springdemo.batch;

import com.github.michalbladowski.springdemo.model.Person;
import org.springframework.batch.item.ItemProcessor;

/**
 * Represents dummy ItemProcessor that can be applied during Job's Step execution
 */
public class PersonValidationProcessor implements ItemProcessor<Person, Person>
{
    public Person process(Person person)
    {
        if(person.getId() <= 0) {
            System.out.println("Invalid person ID: " + person.getId());
            return null;
        }
        return person;
    }
}
