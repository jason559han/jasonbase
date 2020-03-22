package com.jason.base.entity;

import com.jason.base.tags.grid.annotation.JqGrid;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 字典数据
 * @author jason558han
 * @date 2020/3/16 4:20 下午
 */
@Table(name = "system_dict_data")
public class SysDictData implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    @JqGrid(displayName = "账号",hidden = true,search = false)
    private Integer id;

    @Column(name = "dict_name")
    @JqGrid(displayName = "字典数据名字",serial = 1)
    private String dictName;

    @Column(name = "dict_code")
    @JqGrid(displayName = "字典数据编码",serial = 2)
    private String dictCode;

    @Column(name = "dict_order")
    @JqGrid(displayName = "字典顺序",serial = 3)
    private Integer dictOrder;

    @Column(name = "dict_remark")
    @JqGrid(displayName = "字典数据说明",serial = 4)
    private String dictRemark;

    @Column(name = "dict_type_id")
    @JqGrid(displayName = "字典类型id",serial = 5,hidden = true,search = false)
    private Integer dictTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public Integer getDictOrder() {
        return dictOrder;
    }

    public void setDictOrder(Integer dictOrder) {
        this.dictOrder = dictOrder;
    }

    public String getDictRemark() {
        return dictRemark;
    }

    public void setDictRemark(String dictRemark) {
        this.dictRemark = dictRemark;
    }

    public Integer getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(Integer dictTypeId) {
        this.dictTypeId = dictTypeId;
    }
}
