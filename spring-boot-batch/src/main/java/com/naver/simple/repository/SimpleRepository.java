package com.naver.simple.repository;

import java.util.List;

import com.naver.simple.model.Person;

public interface SimpleRepository {
	List<Person> getPersonList();

	List<Person> getPersonListPaging();

	void updatePerson(Person person);
}
