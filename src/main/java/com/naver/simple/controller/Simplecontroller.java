package com.naver.simple.controller;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Simplecontroller {
	@Autowired
	JobLauncher jobLauncher;

	@Resource(name = "simpleJob")
	Job job;

	@RequestMapping("/simpleJob")
	@ResponseBody
	public String handle(@RequestParam("start") String start, @RequestParam("end") String end) throws Exception {
		JobParameters param = new JobParametersBuilder().addString("start", start).addString("end", end)
				.toJobParameters();
		JobExecution run = jobLauncher.run(job, param);
		return "It takes " + Long.toBinaryString(run.getEndTime().getTime() - run.getStartTime().getTime())
				+ " milliseconds";
	}
}
