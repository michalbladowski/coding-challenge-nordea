package com.interview.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    private static final Logger LOG = LoggerFactory.getLogger(RequestService.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${jms.queue.destination}")
    String destinationQueue;

    public void sendMessage(String msg) {
        LOG.debug("In RequestService. Incoming message: " + msg);
        jmsTemplate.convertAndSend(destinationQueue, msg);
    }
}
