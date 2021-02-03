package com.interview.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class TriggerControllerTest extends AbstractDemoApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void putTriggerBatchTest() throws Exception {
        mockMvc.perform(post("/request/send"))
                .andExpect(status().isOk())
                .andExpect(content().string("Process triggered"));
    }
}
