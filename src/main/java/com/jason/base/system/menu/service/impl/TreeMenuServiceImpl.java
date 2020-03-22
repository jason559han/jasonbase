package com.jason.base.system.menu.service.impl;

import com.jason.base.common.dao.BaseDao;
import com.jason.base.entity.SysPermissionsUrl;
import com.jason.base.entity.SysTreeMenu;
import com.jason.base.system.menu.service.TreeMenuService;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqCommonDaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理服务接口 impl
 * @author jason558han
 * @date 2020年02月09日
 */
@Transactional
@Service("treeMenuService")
public class TreeMenuServiceImpl implements TreeMenuService {

	@Autowired
	private BaseDao<SysTreeMenu> menuDao;
	@Autowired
	private BaseDao<SysPermissionsUrl> permissionsUrlDao;

	@Override
	public List<SysTreeMenu> getSysTreeMenuListByGpb(GridPageBean gpb) {
		return JqCommonDaoUtil.getTreePageListByGpb(menuDao, gpb, SysTreeMenu.class);
	}

	@Override
	public String editSysTreeMenuListByMap(Map<String, String[]> map) {
		String msg = JqCommonDaoUtil.defaultTreeEditEntityByMap(map, menuDao, SysTreeMenu.class);
		//当删除菜单时，对相关权限配置进行对应操作
		if ("okok".equals(msg) && "del".equals(map.get("oper")[0])) {
			String id = map.get("id")[0];
			//删除菜单相关权限配置明细
			if ("del".equals(map.get("oper")[0])) {
				Example example = new Example(SysPermissionsUrl.class);
				Criteria cri = example.createCriteria();
				cri.andEqualTo("menuId", id);
				permissionsUrlDao.deleteByExample(example);
			}
		}
		return msg;
	}

	@Override
	public List<SysPermissionsUrl> getPermissionsUrlListByMenuId(String menuId) {
		Example example = new Example(SysPermissionsUrl.class);
		Criteria cri = example.createCriteria();
		cri.andEqualTo("menuId", menuId);
		return permissionsUrlDao.selectByExample(example);
	}

	@Override
	public String insertPermissionsUrlList(String menuId, String[] permissionsUrl, String[] remark) {
		String msg = "okok";
		//先删除原来到
		Example example = new Example(SysPermissionsUrl.class);
		Criteria cri = example.createCriteria();
		cri.andEqualTo("menuId", menuId);
		permissionsUrlDao.deleteByExample(example);

		//再重新保存
		if(permissionsUrl != null && permissionsUrl.length > 0) {
			List<SysPermissionsUrl> permissUrlList = new ArrayList<>();
			for (int i=0; i<permissionsUrl.length; i++) {
				SysPermissionsUrl permissUrl = new SysPermissionsUrl();
				permissUrl.setMenuId(Integer.valueOf(menuId));
				permissUrl.setPermissionsUrl(permissionsUrl[i]);
				permissUrl.setRemark(remark[i]);
				permissUrlList.add(permissUrl);
			}
			if (!permissUrlList.isEmpty()) {
				permissionsUrlDao.insertList(permissUrlList);
			}
		}
		return msg;
	}
}
