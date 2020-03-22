package com.jason.base.common.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface BaseDao<T> extends Mapper<T>, DeleteByIdsMapper<T>, InsertListMapper<T>, SelectByIdsMapper<T> {

}
