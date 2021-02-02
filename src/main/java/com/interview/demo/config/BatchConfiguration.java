package com.interview.demo.config;

import com.interview.demo.batch.NoOpItemWriter;
import com.interview.demo.batch.PersonValidationProcessor;
import com.interview.demo.data.PersonRepository;
import com.interview.demo.model.Person;
import com.interview.demo.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Batch configuration which aim is to pull data from Person repository and put it into Cache
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(BatchConfiguration.class);
    private static final int CHUNK_SIZE = 2;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    private PersonRepository personRepository;

    @Bean
    public ListItemReader<Person> itemReader() {
        return new ListItemReader<>(getPersonService().getAllPersons());
    }

    @Bean
    public PersonService getPersonService() {
        return new PersonService(this.personRepository);
    }

    @Bean
    public ItemProcessor<Person, Person> personProcessor() {
        LOG.debug("In ItemProcessor");
        return new PersonValidationProcessor();
    }

    @Bean
    public Job triggerCachingJob() {
        LOG.debug("In Job");
        return this.jobBuilderFactory.get("triggerCachingJob")
                .start(handlePersonStep())
                .build();
    }

    @Bean
    public Step handlePersonStep() {
        LOG.debug("In Step");
        return stepBuilderFactory.get("handlePersonStep")
                .<Person, Person> chunk(CHUNK_SIZE)
                .reader(itemReader())
                .writer(new NoOpItemWriter())
                .build();
    }

}
