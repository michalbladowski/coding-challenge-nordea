package com.github.michalbladowski.springdemo.services;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RequestReceiver {

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job triggerCachingJob;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(String msg) {
        log.debug("In RequestReceiver. Incoming trigger message: " + msg);
        log.debug("Starting the job");
        try {
            long start = System.currentTimeMillis();
            jobLauncher.run(triggerCachingJob, new JobParameters());
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            log.debug("In JMS consumer. Finished the job in time: " + timeElapsed + "ms");
        } catch (JobExecutionAlreadyRunningException
                | JobRestartException
                | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
