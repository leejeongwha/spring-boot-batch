package com.naver;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBatchApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(SpringBootBatchApplication.class);

	private static final String SIMPLE_JOB = "simpleJob";
	private static final String SECOND_JOB = "secondJob";

	@Autowired
	private JobLauncher jobLauncher;

	@Resource(name = "simpleJob")
	private Job simpleJob;

	@Resource(name = "secondJob")
	private Job secondJob;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootBatchApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		if (SIMPLE_JOB.equals(arg0[0])) {
			JobParameters param = new JobParametersBuilder().toJobParameters();
			jobLauncher.run(simpleJob, param);
		} else if (SECOND_JOB.equals(arg0[0])) {
			JobParameters param = new JobParametersBuilder().toJobParameters();
			jobLauncher.run(secondJob, param);
		} else {
			log.info("선택된 job이 없습니다.");
		}

	}
}
