package com.interview.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Represents the JMS client which listens on specified queue and starts predefined Batch process when Trigger comes
 */
@Component
public class RequestReceiver {
    private static final Logger LOG = LoggerFactory.getLogger(RequestReceiver.class);

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job triggerCachingJob;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(String msg) {
        LOG.debug("In RequestReceiver. Incoming trigger message: " + msg);
        LOG.debug("Starting the job");
        try {
            long start = System.nanoTime();
            jobLauncher.run(triggerCachingJob, new JobParameters());
            long finish = System.nanoTime();
            long timeElapsed = finish - start;
            LOG.debug("In RequestController. Finished the job in time: " + timeElapsed);
        } catch (JobExecutionAlreadyRunningException
                | JobRestartException
                | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
