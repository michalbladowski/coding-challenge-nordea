package com.github.michalbladowski.springdemo.controller;

import com.github.michalbladowski.springdemo.services.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/request")
@RequiredArgsConstructor
@Slf4j
public class JobLauncherController {

    @Autowired
    private final RequestService requestService;

    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Add new airplane")
    @ApiResponse(responseCode = "200", description = "Request accepted")
    public ResponseEntity<String> putRequestOnQueue() {
        log.debug("In JobLauncherController. Running RequestService");
        requestService.sendMessage("Trigger");
        return ResponseEntity.ok("Process triggered");
    }
}


