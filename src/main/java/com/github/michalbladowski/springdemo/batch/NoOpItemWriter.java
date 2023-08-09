package com.github.michalbladowski.springdemo.batch;

import com.github.michalbladowski.springdemo.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

/**
 * Represents dummy ItemWriter which can be executed during Job's Step
 */
public class NoOpItemWriter implements ItemWriter<Person> {
    private static final Logger LOG = LoggerFactory.getLogger(NoOpItemWriter.class);

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        LOG.debug("In ItemWriter");
        LOG.debug(chunk.toString());
    }
}
