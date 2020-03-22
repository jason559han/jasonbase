package com.jason.base.tags.grid.utils;

import com.jason.base.common.dao.BaseDao;
import com.jason.base.common.utils.StringUtil;
import com.jason.base.exception.TransactionalException;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.bean.GridSearchdRule;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 有关jqgrid标签等dao层 共用工具类
 * @author jason558han
 * @date 2020年02月02日
 */
public class JqCommonDaoUtil {

	private static final Logger logger = LogManager.getLogger(JqCommonDaoUtil.class);

	/**
	 * 通用分页列表获取
	 * @param <T> 实体类
	 * @param baseDao 对应操作dao
	 * @param gpb 分页参数
	 * @param clzz 实体类class
	 * @return 表格列数据列表
	 */
	public static <T> List<T> getPageListByGpb(BaseDao<T> baseDao,GridPageBean gpb,Class<T> clzz) {
		Example example = new Example(clzz);
		RowBounds rowBounds = new RowBounds(gpb.getStart(), gpb.getLimit());
		Criteria criteria = example.createCriteria();

		editSearchCriteria(gpb, criteria);
		gpb.setTotal(Long.valueOf(baseDao.selectCountByExample(example)));
		if (StringUtil.isNotEmpty(gpb.getSort())) {//排序
			String sort = StringUtil.bigLetterToSmallAndBeforeAdd_(gpb.getSort());
			example.setOrderByClause(sort+" "+gpb.getDir());
		}
		return baseDao.selectByExampleAndRowBounds(example, rowBounds);
	}

	//操作搜索条件
	private static void editSearchCriteria(GridPageBean gpb, Criteria criteria) {
		if (gpb.getSearchField() != null && !"".equals(gpb.getSearchField())) {
			JqCommonDaoUtil.addSearchCriteria(criteria,
					new GridSearchdRule(gpb.getSearchField(), gpb.getSearchOper(), gpb.getSearchString()),
					JqGridConstants.GROUPOP_AND);
		}
		if (gpb.getFilters() != null && gpb.getFilters().getRules() != null && !gpb.getFilters().getRules().isEmpty()) {
			for (GridSearchdRule rule : gpb.getFilters().getRules()) {
				JqCommonDaoUtil.addSearchCriteria(criteria, rule, gpb.getFilters().getGroupOp());
			}
		}
	}

	/**
	 * 通用树状表格列表获取
	 * @param <T> 实体类
	 * @param baseDao 对应操作dao
	 * @param gpb note节点参数
	 * @param clzz 实体类class
	 * @param maxLevel 首次加载最大层数
	 * @return 表格列数据列表
	 */
	public static <T> List<T> getTreePageListByGpb(BaseDao<T> baseDao,GridPageBean gpb,Class<T> clzz,Integer maxLevel) {
		Example example = new Example(clzz);
		Criteria criteria = example.createCriteria();

		//根据节点id，判断要展开的节点
		if (StringUtil.isNotEmpty(gpb.getNoteid())) {
			criteria.andEqualTo(JqGridConstants.PARENT_FIELD_NAME, gpb.getNoteid());
		} else {
			if (maxLevel != null) {
				criteria.andGreaterThanOrEqualTo(JqGridConstants.LEVEL_FIELD_NAME, 0);
				criteria.andLessThanOrEqualTo(JqGridConstants.LEVEL_FIELD_NAME, maxLevel);
			} else {
				criteria.andEqualTo(JqGridConstants.LEVEL_FIELD_NAME, 0);
			}
		}

		editSearchCriteria(gpb, criteria);
		gpb.setTotal(Long.valueOf(baseDao.selectCountByExample(example)));
		if (gpb.getSort() != null && !"".equals(gpb.getSort())) {//排序
			example.setOrderByClause(gpb.getSort()+" "+gpb.getDir());
		}
		return baseDao.selectByExample(example);
	}

	/**
	 * 通用树状表格列表获取
	 * @param <T> 实体类
	 * @param baseDao 对应操作dao
	 * @param gpb note节点参数
	 * @param clzz 实体类class
	 * @return 编辑后的情况
	 */
	public static <T> List<T> getTreePageListByGpb(BaseDao<T> baseDao,GridPageBean gpb,Class<T> clzz) {
		return getTreePageListByGpb(baseDao, gpb, clzz, null);
	}

	/**
	 * 添加搜索条件
	 * @param cri 查询条件集合
	 * @param rule 搜索规则
	 * @param groupOp and或or
	 */
	private static void addSearchCriteria(Criteria cri, GridSearchdRule rule, String groupOp) {
		if (rule != null && rule.getField() != null && !"".equals(rule.getField()) 
				&& rule.getOp() != null && !"".equals(rule.getOp())) {
			if (JqGridConstants.GROUPOP_AND.equals(groupOp)) {//and
				switch (rule.getOp()) {
				case JqGridConstants.OP_EQUAL://等于
					cri.andEqualTo(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_NOT_EQUAL://不等于
					cri.andNotEqualTo(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_LESS_THAN://小于
					cri.andLessThan(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_LESS_EQUAL://小于等于
					cri.andLessThanOrEqualTo(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_GREATER_THAN://大于
					cri.andGreaterThan(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_GREATER_EQUAL://大于等于
					cri.andGreaterThanOrEqualTo(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_BLOSSOM_HEAD_CONTAIN://以*打头
					cri.andLike(rule.getField(), rule.getData()+"%");
					break;
				case JqGridConstants.OP_NOT_BLOSSOM_HEAD_CONTAIN://不以*打头
					cri.andNotLike(rule.getField(), rule.getData()+"%");
					break;
				case JqGridConstants.OP_IN://在in
					String[] datas = rule.getData().split(",");
					if (datas.length > 0) {
						List<String> datasList = Arrays.asList(datas);
						cri.andIn(rule.getField(), datasList);
					}
					break;
				case JqGridConstants.OP_NOT_IN://不在 not in
					String[] datas2 = rule.getData().split(",");
					if (datas2.length > 0) {
						List<String> datasList = Arrays.asList(datas2);
						cri.andNotIn(rule.getField(), datasList);
					}
					break;
				case JqGridConstants.OP_BLOSSOM_END_CONTAIN://以*为结尾
					cri.andLike(rule.getField(), "%"+rule.getData());
					break;
				case JqGridConstants.OP_NOT_BLOSSOM_END_CONTAIN://不以*为结尾
					cri.andNotLike(rule.getField(), "%"+rule.getData());
					break;
				case JqGridConstants.OP_CONTAIN://包含
					cri.andLike(rule.getField(), "%"+rule.getData()+"%");
					break;
				case JqGridConstants.OP_NOT_CONTAIN://不包含
					cri.andNotLike(rule.getField(), "%"+rule.getData()+"%");
					break;
				}
			} else if (JqGridConstants.GROUPOP_OR.equals(groupOp)) {//or
				switch (rule.getOp()) {
				case JqGridConstants.OP_EQUAL://等于
					cri.orEqualTo(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_NOT_EQUAL://不等于
					cri.orNotEqualTo(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_LESS_THAN://小于
					cri.orLessThan(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_LESS_EQUAL://小于等于
					cri.orLessThanOrEqualTo(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_GREATER_THAN://大于
					cri.orGreaterThan(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_GREATER_EQUAL://大于等于
					cri.orGreaterThanOrEqualTo(rule.getField(), rule.getData());
					break;
				case JqGridConstants.OP_BLOSSOM_HEAD_CONTAIN://以*打头
					cri.orLike(rule.getField(), rule.getData()+"%");
					break;
				case JqGridConstants.OP_NOT_BLOSSOM_HEAD_CONTAIN://不以*打头
					cri.orNotLike(rule.getField(), rule.getData()+"%");
					break;
				case JqGridConstants.OP_IN://在 in
					String[] datas = rule.getData().split(",");
					if (datas.length > 0) {
						List<String> datasList = Arrays.asList(datas);
						cri.orIn(rule.getField(), datasList);
					}
					break;
				case JqGridConstants.OP_NOT_IN://不在 not in
					String[] datas2 = rule.getData().split(",");
					if (datas2.length > 0) {
						List<String> datasList = new ArrayList<>(Arrays.asList(datas2));
						cri.orNotIn(rule.getField(), datasList);
					}
					break;
				case JqGridConstants.OP_BLOSSOM_END_CONTAIN://以*为结尾
					cri.orLike(rule.getField(), "%"+rule.getData());
					break;
				case JqGridConstants.OP_NOT_BLOSSOM_END_CONTAIN://不以*为结尾
					cri.orNotLike(rule.getField(), "%"+rule.getData());
					break;
				case JqGridConstants.OP_CONTAIN://包含
					cri.orLike(rule.getField(), "%"+rule.getData()+"%");
					break;
				case JqGridConstants.OP_NOT_CONTAIN://不包含
					cri.orNotLike(rule.getField(), "%"+rule.getData()+"%");
					break;
				}
			}
		}
	}

	/**
	 * 默认jqgrid标签在数据库中实体编辑
	 * @param <T> 数据实体类
	 * @param map 参数map 注意key值oper是操作参数，注意不要在实体类中含有该属性(其中值有del，add，edit)
	 * @param baseDao 基础dao
	 * @param clzz 数据实体类class
	 * @param updateMap 特殊要修改等参数map
	 * @return 编辑后的情况
	 */
	public static <T> String defaultEditEntityByMap(Map<String,String[]> map,BaseDao<T> baseDao,Class<T> clzz,Map<String,Object> updateMap) {
		String msg;
		String opt = map.get("oper")[0];
		switch (opt) {
		case "del"://删除
			String id = map.get("id")[0];
			msg = defaultDeleteEntityById(baseDao, id);
			break;
		case "add"://添加
			msg = defaultAddEntityByMap(map, baseDao, clzz, updateMap);
			break;
		case "edit"://修改
			msg = defaultUpdateEntityByMap(map, baseDao, updateMap);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + opt);
		}
		return msg;
	}

	/**
	 * 默认jqgrid标签在数据库中实体编辑
	 * @param <T> 数据实体类
	 * @param map 参数map
	 * @param baseDao 基础dao
	 * @param clzz 数据实体类class
	 * @return 执行情况
	 */
	public static <T> String defaultEditEntityByMap(Map<String,String[]> map,BaseDao<T> baseDao,Class<T> clzz) {
		return defaultEditEntityByMap(map, baseDao, clzz, null);
	}

	/**
	 * 默认jqgrid标签在数据库中实体编辑
	 * @param <T> 数据实体类
	 * @param map 参数map 注意key值oper是操作参数，注意不要在实体类中含有该属性
	 * @param baseDao 基础dao
	 * @param clzz 数据实体类class
	 * @param updateMap 特殊要修改等参数map
	 * @return 执行情况
	 */
	public static <T> String defaultTreeEditEntityByMap(Map<String,String[]> map,BaseDao<T> baseDao,Class<T> clzz,Map<String,Object> updateMap) {
		String msg;
		String opt = map.get("oper")[0];
		switch (opt) {
		case "del"://删除
			msg = defaultTreeDeleteEntityById(map, baseDao, clzz);
			break;
		case "add"://添加
			msg = defaultTreeAddEntityByMap(map, baseDao, clzz, updateMap);
			break;
		case "edit"://修改
			msg = defaultUpdateEntityByMap(map, baseDao, updateMap);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + opt);
		}
		return msg;
	}

	/**
	 * 默认jqgrid标签在数据库中实体编辑
	 * @param <T> 数据实体类
	 * @param map 参数map 注意key值oper是操作参数，注意不要在实体类中含有该属性
	 * @param baseDao 基础dao
	 * @param clzz 数据实体类class
	 * @return 执行情况
	 */
	public static <T> String defaultTreeEditEntityByMap(Map<String,String[]> map,BaseDao<T> baseDao,Class<T> clzz) {
		return defaultTreeEditEntityByMap(map, baseDao, clzz, null);
	}

	//默认删除实体方法
	private static <T> String defaultDeleteEntityById(BaseDao<T> baseDao, String id) {
		String msg = "okok";
		if (StringUtil.isNotEmpty(id) && !id.contains(",")) {
			baseDao.deleteByPrimaryKey(id);
		} else {
			baseDao.deleteByIds(id);
		}
		return msg;
	}

	//默认树状删除实体方法
	private static <T> String defaultTreeDeleteEntityById(Map<String, String[]> map, BaseDao<T> baseDao, Class<T> clzz) {
		String msg = "okok";
		String id = map.get("id")[0];
		T t = baseDao.selectByPrimaryKey(id);

		try {
			deleteTreeEntities(baseDao, clzz, t);

			//判断是否有兄弟元素，如果没有，将父元素的isLeaf设置成true
			Example exp3 = new Example(clzz);
			Criteria cri3 = exp3.createCriteria();
			String parent = BeanUtils.getProperty(t, JqGridConstants.PARENT_FIELD_NAME);
			cri3.andEqualTo(JqGridConstants.PARENT_FIELD_NAME, parent);
			List<T> list = baseDao.selectByExample(exp3);

			if (list == null || list.isEmpty()) {
				T pt = baseDao.selectByPrimaryKey(parent);
				BeanUtils.setProperty(pt, JqGridConstants.ISLEAF_FIELD_NAME, true);
				baseDao.updateByPrimaryKey(pt);
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			String message = "默认树状实体类删除方法报错："+(clzz!=null ? "实体类：["+clzz.getName()+"]，":"");
			message += "errorMsg=["+e.getMessage()+"]";
			logger.error(message, e);
			throw new TransactionalException("默认树状实体类删除方法报错.", e);
		}
		return msg;
	}

	//删除树节点
	private static <T> void deleteTreeEntities(BaseDao<T> baseDao, Class<T> clzz, T t) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//如果是父元素，则删除所有子元素
		String isLeaf = BeanUtils.getProperty(t, JqGridConstants.ISLEAF_FIELD_NAME);
		if ("false".equals(isLeaf)) {
			Example exp2 = new Example(clzz);
			Criteria cri2 = exp2.createCriteria();
			String code = BeanUtils.getProperty(t, JqGridConstants.CODE_FIELD_NAME);

			cri2.andLike(JqGridConstants.CODE_FIELD_NAME, code+".%");
			baseDao.deleteByExample(exp2);
		}
		//删除操作
		baseDao.delete(t);
	}

	//默认添加实体类方法
	@SuppressWarnings("deprecation")
	private static <T> String defaultAddEntityByMap(Map<String, String[]> map, BaseDao<T> baseDao, Class<T> clzz,
			Map<String, Object> updateMap) {
		String msg = "okok";
		T t;
		try {
			t = clzz.newInstance();
			Map<String, String> attrMap = changeMapValues(map);
			BeanUtils.populate(t, attrMap);//页面传来参数赋值
			if (updateMap != null && !updateMap.isEmpty()) {
				BeanUtils.populate(t, updateMap);//后台操作参数赋值
			}
			baseDao.insert(t);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			String message = "默认实体类添加方法报错：" + ("实体类：[" + clzz.getName() + "]，");
			message += "errorMsg=["+map.toString()+"]";
			logger.error(message, e);
			throw new TransactionalException("默认实体类添加方法报错.", e);
		}
		return msg;
	}

	//默认树状添加实体类方法
	@SuppressWarnings("deprecation")
	private static <T> String defaultTreeAddEntityByMap(Map<String, String[]> map, BaseDao<T> baseDao, Class<T> clzz,
			Map<String, Object> updateMap) {
		String msg = "okok";
		T t;
		try {
			t = clzz.newInstance();
			Map<String, String> attrMap = changeMapValues(map);
			BeanUtils.populate(t, attrMap);//页面传来参数赋值
			
			//树状固定参数level,upId,isLeaf赋值操作
			String parent = map.get("parent")[0];
			int level = 0;
			Boolean isLeaf = true;
			if (StringUtil.isNotEmpty(parent)) {
				T pt = baseDao.selectByPrimaryKey(parent);
				//根据父元素计算level值
				level = Integer.valueOf(BeanUtils.getProperty(pt, JqGridConstants.LEVEL_FIELD_NAME))+1;
				//如果父元素是叶元素(isLeaf=true),则改成isLeaf=false
				if ("true".equals(BeanUtils.getProperty(pt, JqGridConstants.ISLEAF_FIELD_NAME))) {
					BeanUtils.setProperty(pt, JqGridConstants.ISLEAF_FIELD_NAME, false);
					baseDao.updateByPrimaryKey(pt);
				}
			}

			if (updateMap == null) {
				updateMap = new HashMap<>();
			}
			updateMap.put(JqGridConstants.LEVEL_FIELD_NAME, level);
			updateMap.put(JqGridConstants.ISLEAF_FIELD_NAME, isLeaf);
			if (StringUtil.isNotEmpty(parent)) {
				updateMap.put(JqGridConstants.PARENT_FIELD_NAME, parent);
			}
			
			BeanUtils.populate(t, updateMap);//后台操作参数赋值
			baseDao.insert(t);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NumberFormatException | NoSuchMethodException e) {
			String message = "默认树状实体类添加方法报错：" + ("实体类：[" + clzz.getName() + "]，");
			message += "errorMsg=["+e.getMessage()+"]";
			logger.error(message, e);
			throw new TransactionalException("默认树状实体类添加方法报错.", e);
		}
		return msg;
	}

	//默认修改实体类方法
	private static <T> String defaultUpdateEntityByMap(Map<String, String[]> map, BaseDao<T> baseDao,
			Map<String, Object> updateMap) {
		String msg = "okok";
		String id = map.get("id")[0];
		T t = baseDao.selectByPrimaryKey(id);
		Map<String, String> attrMap = changeMapValues(map);
		try {
			BeanUtils.populate(t, attrMap);
			//页面传来参数赋值
			if (updateMap != null && !updateMap.isEmpty()) {
				BeanUtils.populate(t, updateMap);//后台操作参数赋值
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			String message = "默认实体类修改方法报错：";
			message += "errorMsg=["+e.getMessage()+"]";
			logger.error(message,e);
			throw new TransactionalException("默认实体类修改方法报错。", e);
		}
		baseDao.updateByPrimaryKey(t);
		return msg;
	}

	//Map<String, String[]>转Map<String, String>
	private static Map<String, String> changeMapValues(Map<String, String[]> map) {
		Map<String, String> resultMap = new HashMap<>();
		if (map != null && !map.isEmpty()) {
			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				resultMap.put(entry.getKey(), entry.getValue()[0]);
			}
		}
		return resultMap;
	}
}
