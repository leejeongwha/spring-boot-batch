package com.naver.simple.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class LogDeletingTasklet implements Tasklet {
	private static final Logger log = LoggerFactory.getLogger(LogDeletingTasklet.class);

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {

		log.info("파일 또는 디렉토리를 성공적으로 지웠습니다");

		return RepeatStatus.FINISHED;
	}
}
