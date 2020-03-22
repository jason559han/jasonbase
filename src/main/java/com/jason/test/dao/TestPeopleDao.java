package com.jason.test.dao;

import com.jason.base.common.dao.BaseDao;
import com.jason.test.entity.TestPeople;

import java.util.List;

public interface TestPeopleDao extends BaseDao<TestPeople> {

    TestPeople getTestPeopleById(Integer peopleId);

    List<TestPeople> getTestPeopleList();

    void insertTestPeople(TestPeople people);

    void updateTestPeople(TestPeople people);

    void deleteTestPeopleById(Integer id);
}
