package com.interview.demo;

import com.interview.demo.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;

@SpringBootTest
@EnableConfigurationProperties
@AutoConfigureMockMvc
public abstract class AbstractDemoApplicationTest {

	@Autowired
	private PersonService personService;
	@MockBean
	private JmsTemplate jmsTemplate;

	@Test
	void contextLoads() {
	}

}
