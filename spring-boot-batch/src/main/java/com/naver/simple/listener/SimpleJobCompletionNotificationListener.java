package com.naver.simple.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naver.simple.model.Person;
import com.naver.simple.repository.SimpleRepository;

@Component
public class SimpleJobCompletionNotificationListener extends JobExecutionListenerSupport {
	private static final Logger log = LoggerFactory.getLogger(SimpleJobCompletionNotificationListener.class);

	@Autowired
	private SimpleRepository simpleRepository;

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			List<Person> results = simpleRepository.getPersonList();

			for (Person person : results) {
				log.info("Found <" + person + "> in the database.");
			}

		}
	}
}
