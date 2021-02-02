package com.interview.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.interview.demo.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@EnableConfigurationProperties
@AutoConfigureMockMvc
public class TriggerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PersonService personService;
    @MockBean
    private JmsTemplate jmsTemplate;

    @Test
    public void putTriggerBatchTest() throws Exception {
        mockMvc.perform(post("/request/send"))
                .andExpect(status().isOk())
                .andExpect(content().string("Process triggered"));
    }
}
