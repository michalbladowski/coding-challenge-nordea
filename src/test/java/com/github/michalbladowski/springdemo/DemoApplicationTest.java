package com.github.michalbladowski.springdemo;

import com.github.michalbladowski.springdemo.data.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootTest
class DemoApplicationTest {

	@MockBean
	private JobRepository jobRepository;

	@MockBean
	private PlatformTransactionManager platformTransactionManager;

	@MockBean
	private PersonRepository personRepository;

	@MockBean
	private JobLauncher jobLauncher;

	@MockBean
	private Job triggerCachingJob;

	@Test
	void contextLoads() {
	}

}
