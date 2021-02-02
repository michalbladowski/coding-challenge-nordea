package com.interview.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    private static final Logger LOG = LoggerFactory.getLogger(RequestService.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${jms.queue.destination}")
    String destinationQueue;

    @JmsListener(destination = "${jms.queue.destination}")
    public void sendMessage(String msg) {
        LOG.debug("In RequestService. Msg: " + msg);
        jmsTemplate.convertAndSend(destinationQueue, msg);
    }
}
