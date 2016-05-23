package com.naver;

import java.util.Set;

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

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Set<Job> jobs;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootBatchApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// 커맨드 라인 첫번째 파라미터로 job 결정
		if (arg0.length == 0) {
			log.info("선택된 job이 없습니다.");
		} else {
			for (Job job : jobs) {
				if (job.getName().equals(arg0[0])) {
					JobParameters param = new JobParametersBuilder().toJobParameters();
					jobLauncher.run(job, param);
				}
			}
		}
	}
}
