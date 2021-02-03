package com.interview.demo.controller;

import com.interview.demo.services.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
public class JobLauncherController {
    private static final Logger LOG = LoggerFactory.getLogger(JobLauncherController.class);

    private final RequestService requestService;

    @Autowired
    public JobLauncherController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public String putRequestOnQueue() {
        LOG.debug("In JobLauncherController. Running RequestService");
        requestService.sendMessage("Trigger");
        return "Process triggered";
    }
}


