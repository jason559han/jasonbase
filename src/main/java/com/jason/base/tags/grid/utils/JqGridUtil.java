package com.jason.base.tags.grid.utils;

import com.alibaba.fastjson.JSON;
import com.jason.base.common.Constants;
import com.jason.base.common.utils.DateUtil;
import com.jason.base.common.utils.StringUtil;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.annotation.JqGrid;
import com.jason.base.tags.grid.annotation.JqTreeGrid;
import com.jason.base.tags.grid.bean.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;

/**
 * jqGrid分页数据工具
 * @author jason558han
 * @date 2020年02月19日
 */
public class JqGridUtil {

	private static final Logger logger = LogManager.getLogger(JqGridUtil.class);

	/**
	 * 获取分页数据
	 * @return 分页参数
	 */
	public static GridPageBean getGridPageParams() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String apage = request.getParameter("page");//当前页数
		String arows = request.getParameter("rows");//每页行数
		int limit = Integer.parseInt(arows);
		String sort= request.getParameter("sidx");//排序字段
		String dir = request.getParameter("sord");//升序或降序
		int startpage = Integer.parseInt(apage);
		Integer start = (startpage - 1) * limit;
		GridPageBean gpb = new GridPageBean(start, limit, sort, dir);
		gpb.setRows(Integer.parseInt(arows));
		gpb.setPage(Integer.parseInt(apage));

		gpb.setSearchField(request.getParameter("searchField"));//搜索列名
		gpb.setSearchOper(request.getParameter("searchOper"));//搜索比较运算符
		gpb.setSearchString(request.getParameter("searchString"));//搜索内容

		//noteid，树状表格中会用到
		String noteid = request.getParameter("nodeid");
		if (StringUtil.isNotEmpty(noteid)) {
			gpb.setNoteid(noteid);
		}

		//搜索列表
		GridSearchFilters filters = JSON.parseObject(request.getParameter("filters"), GridSearchFilters.class);
		gpb.setFilters(filters);
		return gpb;
	}

	/**
	 * 列表数据加载
	 * @param <T> 数据实体类
	 * @param list 数据实体类数组
	 * @param clzz 数据实体类class
	 * @param gpb 搜索、分页等参数
	 * @param response 响应对象
	 * @param userdata 用户信息
	 * @param excludes 排除字段
	 */
	public static <T>void toGridJson(List<T> list, Class<T> clzz, GridPageBean gpb, HttpServletResponse response, Map<String, String> userdata, String[] excludes) {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			StringBuilder builder = new StringBuilder("{");
			//添加获取分页列表数据
			builder.append(getGridPageBeanJsonObject(gpb));
			builder.append("\"rows\":[");
			
			StringBuilder builders = new StringBuilder();
			//获取colModels数据
			Field[] fields = clzz.getDeclaredFields();
			if (fields.length > 0) {
				//获取id属性列
				Field idField = getIdFieldById(fields);
				//获取列表类型数据
				List<JqColModel> colModels = getColModelsByFields(fields, excludes);
				
				for (T t : list) {
					if (builders.length() > 0) {
						builders.append(",");
					}
					builders.append("{\"id\":\"").append(BeanUtils.getProperty(t, idField.getName())).append("\",");
					builders.append("\"cell\":[");
					//添加获取列表数据
					builders.append(getColModelJsonArray(colModels, t));
					builders.append("]}");
				}
			}
			builder.append(builders).append("]");
			//添加获取用户信息json对象
			builder.append(getUserDataJsonObject(userdata));
			
			builder.append("}");
			pw.write(builder.toString());
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | IOException e) {
			String message = "列表数据加载方法报错："+(clzz!=null ? "实体类：["+clzz.getName()+"]，":"");
			message += gpb != null ? "gpb=["+gpb.toString()+"]，" : "";
			message += "errorMsg=["+e.getMessage()+"]";
			logger.error(message, e);
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
	}

	/**
	 * 列表数据加载
	 * @param <T> 数据实体类
	 * @param list 数据实体类数组
	 * @param clzz 数据实体类class
	 * @param gpb 搜索、分页等参数
	 * @param response 响应对象
	 */
	public static <T>void toTreeGridJson(List<T> list, Class<T> clzz, GridPageBean gpb, HttpServletResponse response) {
		toTreeGridJson(list, clzz, gpb, response, null, null);
	}

	/**
	 * treegrid 树状列表数据加载
	 * @param <T> 数据实体类
	 * @param list 数据实体类数组
	 * @param clzz 数据实体类class
	 * @param gpb 搜索、分页等参数
	 * @param response  响应对象
	 * @param userdata 用户信息
	 * @param excludes 排除字段
	 */
	public static <T>void toTreeGridJson(List<T> list, Class<T> clzz, GridPageBean gpb, HttpServletResponse response, Map<String, String> userdata, String[] excludes) {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			StringBuilder builder = new StringBuilder("{");
			//添加获取分页列表数据
			builder.append(getGridPageBeanJsonObject(gpb));
			builder.append("\"rows\":[");
			
			StringBuilder builders = new StringBuilder();
			//获取colModels数据
			Field[] fields = clzz.getDeclaredFields();
			if (fields.length > 0) {
				//获取id属性列
				Field idField = getIdFieldById(fields);
				//获取列表类型数据
				List<JqColModel> colModels = getColModelsByFields(fields, excludes);
				//添加获取树状列model数据属性
				JqTreeColModel treeColModel = getTreeColModelByFields(fields);
				
				for (T t : list) {
					if (builders.length() > 0) {
						builders.append(",");
					}
					builders.append("{\"id\":\"").append(BeanUtils.getProperty(t, idField.getName())).append("\",");
					builders.append("\"cell\":[");
					//添加获取列表数据
					builders.append(getColModelJsonArray(colModels, t));
					//添加获取tree树状列表数据
					builders.append(getTreeColModelJsonArray(treeColModel, t));
					builders.append("]}");
				}
				builder.append(builders).append("]");
				//添加获取用户信息json对象
				builder.append(getUserDataJsonObject(userdata));
				
				builder.append("}");
				pw.write(builder.toString());
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | IOException e) {
			String message = "树状列表数据加载方法报错："+(clzz!=null ? "实体类：["+clzz.getName()+"]，":"");
			message += gpb != null ? "gpb=["+gpb.toString()+"]" : "";
			message += "errorMsg=["+e.getMessage()+"]";
			logger.error(message, e);
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
	}

	//添加分页数据json对象
	private static StringBuffer getGridPageBeanJsonObject(GridPageBean gpb) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\"total\":").append(Math.ceil((double)gpb.getTotal()/(double)gpb.getRows())).append(",");
		buffer.append("\"page\":\"").append(gpb.getPage()).append("\",");
		buffer.append("\"records\":\"").append(gpb.getTotal()).append("\",");
		return buffer;
	}

	//获取列model数据
	private static List<JqColModel> getColModelsByFields(Field[] fields, String[] excludes) {
		List<JqColModel> colModels = new ArrayList<>();
		roundField : for (Field field : fields) {
			//排除屏蔽字段
			if (StringUtil.isContainInArray(excludes, field.getName())) {
				continue roundField;
			}
			if (field.isAnnotationPresent(JqGrid.class)) {
				JqGrid jqGrid = field.getAnnotation(JqGrid.class);
				JqColModel jqColModel = new JqColModel(field.getName(), jqGrid.serial(), field, jqGrid);
				colModels.add(jqColModel);
			}
		}
		Collections.sort(colModels);
		return colModels;
	}

	//获取id列
	private static Field getIdFieldById(Field[] fields) {
		Field idField = null;
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)) {
				idField = field;
			}
		}
		//根据整理的colModels数据来进行json数据的生成
		if (idField == null) {
			throw new IllegalArgumentException("注解类没用注解id");
		}
		return idField;
	}

	//添加列表数据
	private static <T> StringBuffer getColModelJsonArray(List<JqColModel> colModels, T t) {
		StringBuffer cellBuffer = new StringBuffer();
		try {
			for (JqColModel colModel : colModels) {
				if (cellBuffer.length() > 0) {
					cellBuffer.append(",");
				}
				String cellValue = BeanUtils.getProperty(t, colModel.getField().getName());
				if (StringUtil.isNotEmpty(cellValue)) {
					if ("java.util.Date".equals(colModel.getField().getType().getName())) {
						cellValue = DateUtil.getDateStr(DateUtil.getDateByDateStr(cellValue), colModel.getJqGrid().datefmt());
					}
					cellValue = cellValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\"", "&quot;");
				} else {
					cellValue = "";
				}
				cellBuffer.append("\"").append(cellValue).append("\"");
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ParseException e) {
			String message = "添加列表数据方法报错："+(t!=null ? "实体类：["+t.toString()+"]，":"");
			message += "errorMsg=["+e.getMessage()+"]";
			logger.error(message, e);
			e.printStackTrace();
		}
		return cellBuffer;
	}

	//获取用户信息json对象
	private static StringBuffer getUserDataJsonObject(Map<String, String> userdata) {
		StringBuffer buffer = new StringBuffer();
		if (userdata != null && !userdata.isEmpty()) {
			buffer.append(",\"userdata\":{");
			for (Map.Entry<String, String> entry : userdata.entrySet()) {
				StringUtil.addJsonObjectStr(buffer, "\""+entry.getKey()+"\"", entry.getValue());
			}
			buffer.append("}");
		}
		return buffer;
	}

	//获取树状列model数据属性
	private static JqTreeColModel getTreeColModelByFields(Field[] fields) {
		JqTreeColModel treeColModel = new JqTreeColModel();
		for (Field field : fields) {
			//判断jqGrid注解renderName，获取field
			if (field.isAnnotationPresent(JqTreeGrid.class)) {
				String renderName = field.getAnnotation(JqTreeGrid.class).renderName();
				if ("level".equalsIgnoreCase(renderName)) {
					treeColModel.setLevelField(field);
				}
				if ("parent".equalsIgnoreCase(renderName)) {
					treeColModel.setParentField(field);
				}
				if ("isLeaf".equalsIgnoreCase(renderName)) {
					treeColModel.setIsLeafField(field);
				}
				if ("expanded".equalsIgnoreCase(renderName)) {
					treeColModel.setExpandedField(field);
				}
				if ("loaded".equalsIgnoreCase(renderName)) {
					treeColModel.setLoadedField(field);
				}
				if ("icon".equalsIgnoreCase(renderName)) {
					treeColModel.setIconField(field);
				}
			}
		}
		return treeColModel;
	}

	//添加列表数据
	private static <T> StringBuffer getTreeColModelJsonArray(JqTreeColModel treeModel, T t) {
		StringBuffer cellBuffer = new StringBuffer();

		int level = 0;//层级
		String parent = null;//父元素id
		boolean isLeaf = true;//是否是叶元素
		boolean expanded = false;//是否展开
		boolean loaded = false;//是否已经加载
		String icon = null;//图标

		try {
			if (treeModel.getLevelField() != null) {
				level = Integer.parseInt(BeanUtils.getProperty(t, treeModel.getLevelField().getName()));
			}
			if (treeModel.getParentField() != null) {
				parent = BeanUtils.getProperty(t, treeModel.getParentField().getName());
			}
			if (treeModel.getIsLeafField() != null) {
				isLeaf = Boolean.parseBoolean(BeanUtils.getProperty(t, treeModel.getIsLeafField().getName()));
			}
			if (treeModel.getExpandedField() != null) {
				expanded = Boolean.parseBoolean(BeanUtils.getProperty(t, treeModel.getExpandedField().getName()));
			}
			if (treeModel.getLoadedField() != null) {
				loaded = Boolean.parseBoolean(BeanUtils.getProperty(t, treeModel.getLoadedField().getName()));
			}
			if (treeModel.getIconField() != null) {
				icon = BeanUtils.getProperty(t, treeModel.getIconField().getName());
			}
		} catch (NumberFormatException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}

		cellBuffer.append(",\"").append(level).append("\"");
		if (parent != null) {
			cellBuffer.append(",\"").append(parent).append("\"");
		} else {
			cellBuffer.append(",").append(parent);
		}
		cellBuffer.append(",\"").append(isLeaf).append("\"");
		cellBuffer.append(",\"").append(expanded).append("\"");
		if (loaded) {
			cellBuffer.append(",\"").append(loaded).append("\"");
		}
		if (tk.mybatis.mapper.util.StringUtil.isNotEmpty(icon)) {
			if (loaded) {
				cellBuffer.append(",\"").append(loaded).append("\"");
			}
			cellBuffer.append(",\"").append(icon).append("\"");
		}
		return cellBuffer;
	}

	/**
	 * 列表数据加载
	 * @param <T> 数据实体类
	 * @param list 数据实体类数组
	 * @param clzz 数据实体类class
	 * @param gpb 搜索、分页等参数
	 * @param response 响应对象
	 */
	public static <T>void toGridJson(List<T> list, Class<T> clzz, GridPageBean gpb, HttpServletResponse response) {
		toGridJson(list, clzz, gpb, response, null, null);
	}

	/**
	 * 列表数据加载
	 * @param <T> 数据实体类
	 * @param list 数据实体类数组
	 * @param clzz 数据实体类class
	 * @param gpb 搜索、分页等参数
	 * @param response 响应对象
	 * @param userdata 用户信息
	 */
	public static <T>void toGridJson(List<T> list, Class<T> clzz, GridPageBean gpb, HttpServletResponse response, Map<String, String> userdata) {
		toGridJson(list, clzz, gpb, response, userdata, null);
	}

	/**
	 * 列表数据加载
	 * @param <T> 数据实体类
	 * @param list 数据实体类数组
	 * @param clzz 数据实体类class
	 * @param gpb 搜索、分页等参数
	 * @param response 响应对象
	 * @param excludes 排除字段
	 */
	public static <T>void toGridJson(List<T> list, Class<T> clzz, GridPageBean gpb, HttpServletResponse response, String[] excludes) {
		toGridJson(list, clzz, gpb, response, null, excludes);
	}

	/**
	 * 添加列表搜索规则
	 * @param gpb 分页参数
	 * @param field 列名称
	 * @param op 关系符
	 * @param data 列内容
	 */
	public static void addAndSearchRuleToGpb(GridPageBean gpb, String field, String op, String data) {
		GridSearchFilters filters = gpb.getFilters() != null ? gpb.getFilters() : new GridSearchFilters(JqGridConstants.GROUPOP_AND);
		List<GridSearchdRule> rules = filters.getRules() != null ? filters.getRules() : new ArrayList<>();
		rules.add(new GridSearchdRule(field, op, data));
		filters.setRules(rules);
		gpb.setFilters(filters);
	}

	/**
	 * 添加日期区间搜索条件
	 * @param gpb 分页参数
	 * @param field 列名
	 * @param section 时间区间
	 */
	public static void addDateSectionToGpb(GridPageBean gpb, String field, String section) {
		Date secDate = DateUtil.getDateBySection(section);
		if (secDate != null) {
			JqGridUtil.addAndSearchRuleToGpb(gpb, field, JqGridConstants.OP_GREATER_EQUAL, DateUtil.getDateStr(secDate, Constants.DEFAULT_DATE_FORMAT));
		}
	}
}
