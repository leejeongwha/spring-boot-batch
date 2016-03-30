package com.naver.simple.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.naver.simple.model.Person;

/**
 * The jobParameters bean can not actually be instantiated until the “Step”
 * starts. To fix it, late binding with a scope of “Step” is required.
 * 
 * @author ljk2491
 *
 */
@Component
@StepScope
public class SimpleItemProcessor implements ItemProcessor<Person, Person> {
	private static final Logger log = LoggerFactory.getLogger(SimpleItemProcessor.class);

	@Value("#{jobParameters[start]}")
	private String start;

	@Value("#{jobParameters[end]}")
	private String end;

	@Override
	public Person process(final Person user) throws Exception {
		log.info("jobParamters : start - {}, end -{}", start, end);

		// 강제로 RuntimeException 발생
		// if (user.getName().equals("person5")) {
		// throw new RuntimeException();
		// }

		String name = user.getName().toUpperCase();
		Integer age = user.getAge() + 100;

		Person transformedUser = new Person();
		transformedUser.setId(user.getId());
		transformedUser.setName(name);
		transformedUser.setAge(age);

		log.info("Converting (" + user + ") into (" + transformedUser + ")");

		return transformedUser;
	}
}
