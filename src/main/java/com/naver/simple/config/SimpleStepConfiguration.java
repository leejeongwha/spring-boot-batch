package com.naver.simple.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.naver.simple.model.Person;
import com.naver.simple.service.JobFailTasklet;
import com.naver.simple.service.LogDeletingTasklet;
import com.naver.simple.service.SimpleItemProcessor;

@Configuration
public class SimpleStepConfiguration {
	private static final String STEP_NAME = "simpleStep";
	private static final String LOG_DELETE_STEP_NAME = "logDeleteStep";
	private static final String JOB_FAIL_STEP_NAME = "jobFailStep";

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;

	@Autowired
	private SimpleItemProcessor simpleItemProcessor;

	/**
	 * http://www.mybatis.org/spring/batch.html 의 reader 및 writer 사용
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "simpleStep")
	public Step simpleStep() throws Exception {
		// 특정 Exception에 대한 skipLimit 테스트 시 주석 해제

		// return stepBuilderFactory.get(STEP_NAME).<Person, Person>
		// chunk(100).faultTolerant()
		// .skip(RuntimeException.class).skipLimit(5).reader(simpleItemReader()).processor(simpleItemProcessor)
		// .writer(simpleItemWriter()).build();

		return stepBuilderFactory.get(STEP_NAME).<Person, Person> chunk(100).reader(simpleItemReader())
				.processor(simpleItemProcessor).writer(simpleItemWriter()).build();
	}

	@Bean
	public ItemReader<Person> simpleItemReader() throws Exception {
		MyBatisPagingItemReader<Person> reader = new MyBatisPagingItemReader<Person>();
		reader.setSqlSessionFactory(sqlSessionFactory.getObject());
		reader.setQueryId("getPersonListPaging");
		reader.setPageSize(5);

		return reader;
	}

	@Bean
	public ItemWriter<Person> simpleItemWriter() throws Exception {
		MyBatisBatchItemWriter<Person> writer = new MyBatisBatchItemWriter<Person>();
		writer.setSqlSessionFactory(sqlSessionFactory.getObject());
		writer.setStatementId("com.naver.simple.repository.SimpleRepository.updatePerson");

		return writer;
	}

	@Bean(name = "logDeleteStep")
	public Step logDeleteStep() throws Exception {
		return stepBuilderFactory.get(LOG_DELETE_STEP_NAME).tasklet(new LogDeletingTasklet()).build();
	}

	@Bean(name = "jobFailStep")
	public Step jobFailStep() throws Exception {
		return stepBuilderFactory.get(JOB_FAIL_STEP_NAME).tasklet(new JobFailTasklet()).build();
	}
}
