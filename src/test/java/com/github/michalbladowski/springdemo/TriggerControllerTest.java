package com.github.michalbladowski.springdemo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.github.michalbladowski.springdemo.controller.JobLauncherController;
import com.github.michalbladowski.springdemo.services.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;

import java.nio.charset.StandardCharsets;

@WebMvcTest(JobLauncherController.class)
@WithMockUser
class TriggerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService requestService;
    @MockBean
    private JobRepository jobRepository;
    @MockBean
    private JmsTemplate jmsTemplate;
    @MockBean
    private PlatformTransactionManager transactionManager;

    @Test
    void putTriggerBatchTest() throws Exception {
        mockMvc.perform(post("/request/send")
                        .with(csrf())
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Process triggered"));
    }
}
