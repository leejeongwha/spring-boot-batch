package com.naver.simple.config;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.naver.simple.listener.SimpleJobCompletionNotificationListener;

@Configuration
@EnableBatchProcessing
public class SimpleJobConfiguration extends DefaultBatchConfigurer {
	private static final String JOB_NAME = "simpleJob";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Resource(name = "simpleStep")
	private Step simpleStep;

	@Autowired
	private SimpleJobCompletionNotificationListener listener;

	@Bean
	public Job simpleJob() {
		return jobBuilderFactory.get(JOB_NAME).listener(listener).start(simpleStep).build();
	}

	/*
	 * @Override protected JobRepository createJobRepository() throws Exception
	 * { return new MapJobRepositoryFactoryBean().getObject(); }
	 */
}
