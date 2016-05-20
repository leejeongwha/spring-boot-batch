package com.naver.second.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.naver.second.tasklet.SecondTasklet;

@Configuration
@EnableBatchProcessing
public class SecondJobConfiguration {
	private static final String JOB_NAME = "secondJob";
	private static final String STEP_NAME = "secondStep";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job secondJob() throws Exception {
		return jobBuilderFactory.get(JOB_NAME).start(secondStep()).build();
	}

	@Bean
	public Step secondStep() throws Exception {
		return stepBuilderFactory.get(STEP_NAME).tasklet(new SecondTasklet()).build();
	}
}
