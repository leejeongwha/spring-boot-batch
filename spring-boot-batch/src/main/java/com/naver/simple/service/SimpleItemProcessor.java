package com.naver.simple.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.naver.simple.model.Person;

@Component
public class SimpleItemProcessor implements ItemProcessor<Person, Person> {
	private static final Logger log = LoggerFactory.getLogger(SimpleItemProcessor.class);

	@Override
	public Person process(final Person user) throws Exception {
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
