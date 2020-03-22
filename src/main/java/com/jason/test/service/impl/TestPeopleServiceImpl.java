package com.jason.test.service.impl;

import com.jason.base.common.dao.BaseDao;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqCommonDaoUtil;
import com.jason.test.entity.TestPeople;
import com.jason.test.service.TestPeopleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("testPeopleService")
public class TestPeopleServiceImpl implements TestPeopleService {

    @Resource
    private BaseDao<TestPeople> baseDao;

	@Override
	public List<TestPeople> getTestPeopleListByGpb(GridPageBean gpb) {
		return JqCommonDaoUtil.getPageListByGpb(baseDao, gpb, TestPeople.class);
	}

	@Override
	public String editTestPeopleByMap(Map<String, String[]> map) {
		Map<String, Object> updateMap = new HashMap<>();
		updateMap.put("editDate", new Date());
		return JqCommonDaoUtil.defaultEditEntityByMap(map, baseDao, TestPeople.class, updateMap);
	}
}
