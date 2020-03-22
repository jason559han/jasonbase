package com.jason.base.system.dictionary.service;

import com.jason.base.entity.SysDictData;
import com.jason.base.entity.SysDictType;
import com.jason.base.tags.grid.bean.GridPageBean;

import java.util.List;
import java.util.Map;

/**
 * 字典信息维护 service
 * @author jason558han
 * @date 2020/3/16 5:35 下午
 */
public interface DictionaryService {

    /**
     * 获得字典类型列表
     * @param gpb 分页参数
     * @return 字典类型列表
     */
    List<SysDictType> getSysDictTypeListByGpb(GridPageBean gpb);

    /**
     * 编辑字典类型信息
     * @param map 编辑参数
     * @return 编辑情况
     */
    String editSysDictTypeByMap(Map<String, String[]> map);

    /**
     * 获得字典数据列表
     * @param typeId 类型id
     * @param gpb 分页参数
     * @return 字典数据列表
     */
    List<SysDictData> getSysDictDataListByIdGpb(String typeId, GridPageBean gpb);

    /**
     * 编辑字典数据信息
     * @param map 编辑参数
     * @return 编辑情况
     */
    String editSysDictDataByMap(Map<String, String[]> map);

    /**
     * 根据字典类型id获取select字符串
     * @param typeId 字典类型id
     * @return select表单字符串
     */
    String getDictDataSelectStrByTypeId(String typeId);

    /**
     * 根据字典类型code获取select字符串
     * @param typeCode 字典类型code
     * @return select表单字符串
     */
    String getDictDataSelectStrByTypeCode(String typeCode);

    /**
     * 根据字典类型id获取字典数据列表
     * @param typeId 字典类型id
     * @return 字典数据列表
     */
    List<SysDictData> getSysDictDataListByTypeId(Integer typeId);

    /**
     * 根据字典类型code获取字典数据列表
     * @param typeCode 字典类型code
     * @return 字典数据列表
     */
    List<SysDictData> getSysDictDataListByTypeCode(String typeCode);
}
