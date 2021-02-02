package com.interview.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
public class JobLauncherController {
    private static final Logger LOG = LoggerFactory.getLogger(JobLauncherController.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${jms.queue.destination}")
    String destinationQueue;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public String putRequestOnQueue() {
        LOG.debug("In JobLauncherController. Putting trigger message on queue");
        jmsTemplate.convertAndSend(destinationQueue,"Trigger");
        return "Process triggered";
    }
}


