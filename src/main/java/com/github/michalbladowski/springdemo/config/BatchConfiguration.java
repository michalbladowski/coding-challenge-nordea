package com.github.michalbladowski.springdemo.config;

import com.github.michalbladowski.springdemo.batch.NoOpItemWriter;
import com.github.michalbladowski.springdemo.batch.PersonValidationProcessor;
import com.github.michalbladowski.springdemo.model.Person;
import com.github.michalbladowski.springdemo.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Batch configuration which aim is to pull data from Person repository and put it into Cache
 */
@Configuration
@Slf4j
public class BatchConfiguration {
    private static final int CHUNK_SIZE = 2;

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private PersonService personService;

    @Bean
    @Lazy
    public Job triggerCachingJob() {
        log.debug("In Job");
        return new JobBuilder("triggerCachingJob", jobRepository)
                .start(handlePersonStepReaderWriter())
                .build();
    }

    // Example using Tasklet. See below for ItemReader and ItemWriter implementation
    @Bean
    @Lazy
    @Deprecated(since = "spring-boot 3.0")
    public Step handlePersonStepUsingTasklet() {
        log.debug("In Step using Tasklet");
        return new StepBuilder("handlePersonStep", jobRepository)
                .tasklet((stepContribution, chunkContext) -> {
                    personService.getAllPersons();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    // Example using ItemReader and ItemWriter to do the same as in example using Tasklet
    @Bean
    @Lazy
    public Step handlePersonStepReaderWriter() {
        log.debug("In Step using ItemReader and ItemWriter");
        return new StepBuilder("handlePersonStep", jobRepository)
                .<Person, Person> chunk(CHUNK_SIZE, transactionManager)
                .reader(itemReader())
                .writer(new NoOpItemWriter())
                .build();
    }

    @Bean
    @Lazy
    public ListItemReader<Person> itemReader() {
        return new ListItemReader<>(personService.getAllPersons());
    }

    @Bean
    @Lazy
    public ItemProcessor<Person, Person> personProcessor() {
        log.debug("In ItemProcessor");
        return new PersonValidationProcessor();
    }
}
