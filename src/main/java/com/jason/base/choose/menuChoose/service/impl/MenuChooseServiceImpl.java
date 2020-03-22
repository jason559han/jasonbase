package com.jason.base.choose.menuChoose.service.impl;

import com.jason.base.common.dao.BaseDao;
import com.jason.base.entity.SysTreeMenu;
import com.jason.base.choose.menuChoose.bean.MenuChooseBean;
import com.jason.base.choose.menuChoose.service.MenuChooseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单选择service
 * @author jason558han
 * @date 2020年02月27日
 */
@Transactional
@Service("menuChooseService")
public class MenuChooseServiceImpl implements MenuChooseService {

	@Autowired
	private BaseDao<SysTreeMenu> menuDao;

	@Override
	public List<MenuChooseBean> getMenuChooseBeanListAll() throws InvocationTargetException, IllegalAccessException {
		List<MenuChooseBean> beanList = new ArrayList<>();
		List<SysTreeMenu> menuList = getSysTreeMenuListAll();
		for (SysTreeMenu menu : menuList) {
			MenuChooseBean bean = new MenuChooseBean();
			BeanUtils.copyProperties(bean, menu);
			beanList.add(bean);
		}
		return beanList;
	}

	private List<SysTreeMenu> getSysTreeMenuListAll() {
		Example example = new Example(SysTreeMenu.class);
		Criteria cri = example.createCriteria();
		cri.andEqualTo("state", "0");//显示
		example.orderBy("code");
		return menuDao.selectByExample(example);
	}
}
