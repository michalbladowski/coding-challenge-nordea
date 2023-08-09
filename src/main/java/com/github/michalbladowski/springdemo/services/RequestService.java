package com.github.michalbladowski.springdemo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RequestService {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${jms.queue.destination}")
    String destinationQueue;

    public void sendMessage(String msg) {
        log.debug("In RequestService. Incoming message: " + msg);
        jmsTemplate.convertAndSend(destinationQueue, msg);
    }
}
