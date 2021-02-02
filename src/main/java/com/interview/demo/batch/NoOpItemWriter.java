package com.interview.demo.batch;

import com.interview.demo.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Represents dummy ItemWriter which can be executed during Job's Step
 */
public class NoOpItemWriter implements ItemWriter<Person> {
    private static final Logger LOG = LoggerFactory.getLogger(NoOpItemWriter.class);

    @Override
    public void write(List<? extends Person> list) throws Exception {
        LOG.debug("In ItemWriter");
        LOG.debug(list.toString());
    }
}
