package com.naver;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBootBatchApplication.class)
public class SpringBootBatchApplicationTests {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job simpleJob;

	@Test
	public void step() throws Exception {
		Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		JobParameters jobParameters = new JobParameters(parameters);
		jobLauncher.run(simpleJob, jobParameters);
	}
}
