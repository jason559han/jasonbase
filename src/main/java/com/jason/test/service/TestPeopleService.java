package com.jason.test.service;

import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.test.entity.TestPeople;

import java.util.List;
import java.util.Map;

public interface TestPeopleService {

	/**
	 * 获取分页数据
	 * @param gpb 分页参数
	 * @return 测试人员列表
	 */
	List<TestPeople> getTestPeopleListByGpb(GridPageBean gpb);

	/**
	 * 编辑测试人员
	 * @param map 参数map
	 * @return msg
	 */
	String editTestPeopleByMap(Map<String, String[]> map);
}
