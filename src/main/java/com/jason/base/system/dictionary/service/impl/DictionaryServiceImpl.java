package com.jason.base.system.dictionary.service.impl;

import com.jason.base.common.dao.BaseDao;
import com.jason.base.entity.SysDictData;
import com.jason.base.entity.SysDictType;
import com.jason.base.system.dictionary.service.DictionaryService;
import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.bean.GridPageBean;
import com.jason.base.tags.grid.utils.JqCommonDaoUtil;
import com.jason.base.tags.grid.utils.JqGridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 字典信息维护 service 实例
 * @author jason558han
 * @date 2020/3/16 10:27 下午
 */
@Transactional
@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private BaseDao<SysDictType> dictTypeDao;
    @Autowired
    private BaseDao<SysDictData> dictDataDao;

    @Override
    public List<SysDictType> getSysDictTypeListByGpb(GridPageBean gpb) {
        return JqCommonDaoUtil.getPageListByGpb(dictTypeDao, gpb, SysDictType.class);
    }

    @Override
    public String editSysDictTypeByMap(Map<String, String[]> map) {
        return JqCommonDaoUtil.defaultEditEntityByMap(map, dictTypeDao, SysDictType.class);
    }

    @Override
    public List<SysDictData> getSysDictDataListByIdGpb(String id, GridPageBean gpb) {
        JqGridUtil.addAndSearchRuleToGpb(gpb,"dictTypeId", JqGridConstants.OP_EQUAL, id);
        return JqCommonDaoUtil.getPageListByGpb(dictDataDao, gpb, SysDictData.class);
    }

    @Override
    public String editSysDictDataByMap(Map<String, String[]> map) {
        return JqCommonDaoUtil.defaultEditEntityByMap(map, dictDataDao, SysDictData.class);
    }

    @Override
    public String getDictDataSelectStrByTypeId(String typeId) {
        List<SysDictData> dictDataList = getSysDictDataListByTypeId(Integer.valueOf(typeId));
        StringBuilder builder = new StringBuilder("<select>");
        if (dictDataList != null && !dictDataList.isEmpty()) {
            for (SysDictData dictData : dictDataList) {
                builder.append("<option value='");
                builder.append(dictData.getId());
                builder.append("'>");
                builder.append(dictData.getDictName());
                builder.append("</option>");
            }
        }
        builder.append("</select>");
        return builder.toString();
    }

    @Override
    public String getDictDataSelectStrByTypeCode(String typeCode) {
        List<SysDictData> dictDataList = getSysDictDataListByTypeCode(typeCode);
        StringBuilder builder = new StringBuilder("<select>");
        if (dictDataList != null && !dictDataList.isEmpty()) {
            for (SysDictData dictData : dictDataList) {
                builder.append("<option value='");
                builder.append(dictData.getDictCode());
                builder.append("'>");
                builder.append(dictData.getDictName());
                builder.append("</option>");
            }
        }
        builder.append("</select>");
        return builder.toString();
    }

    @Override
    public List<SysDictData> getSysDictDataListByTypeId(Integer typeId) {
        Example example = new Example(SysDictData.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("dictTypeId", typeId);
        return dictDataDao.selectByExample(example);
    }

    @Override
    public List<SysDictData> getSysDictDataListByTypeCode(String typeCode) {
        List<SysDictData> dictDataList = new ArrayList<>();
        Example example = new Example(SysDictType.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("typeCode", typeCode);
        List<SysDictType> dictTypeList = dictTypeDao.selectByExample(example);
        if (dictTypeList != null && !dictTypeList.isEmpty()) {
            dictDataList = getSysDictDataListByTypeId(dictTypeList.get(0).getId());
        }
        return dictDataList;
    }
}
