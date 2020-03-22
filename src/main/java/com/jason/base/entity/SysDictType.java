package com.jason.base.entity;

import com.jason.base.tags.grid.annotation.Editoptions;
import com.jason.base.tags.grid.annotation.Editrules;
import com.jason.base.tags.grid.annotation.JqGrid;
import com.jason.base.tags.grid.annotation.Searchoptions;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 字典类型
 * @author jason558han
 * @date 2020/3/16 4:19 下午
 */
@Table(name = "system_dict_type")
@JqGrid(displayName = "字典信息维护")
public class SysDictType implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    @JqGrid(displayName = "账号", hidden = true, search = false)
    private Integer id;

    @Column(name = "type_name")
    @JqGrid(displayName = "字典类型名称",serial = 1,width = 130,editable = true)
    @Editoptions(maxLength = 20,size = 18)
    @Editrules(required = true)
    @Searchoptions(sopt = {"cn","nc","bw","bn","ew","en"})
    private String typeName;

    @Column(name = "type_code")
    @JqGrid(displayName = "字典类型编码",serial = 2,width = 100,editable = true)
    @Editoptions(maxLength = 15,size = 16)
    @Editrules(required = true)
    @Searchoptions(sopt = {"cn","nc","bw","bn","ew","en"})
    private String typeCode;

    @Column(name = "type_remark")
    @JqGrid(displayName = "字典类型说明",serial = 3,width = 300,editable = true)
    @Editoptions(maxLength = 50, size = 20)
    @Searchoptions(sopt = {"cn","nc","bw","bn","ew","en"})
    private String typeRemark;

    public SysDictType() {
    }
    public SysDictType(String typeName, String typeCode) {
        this.typeName = typeName;
        this.typeCode = typeCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeRemark() {
        return typeRemark;
    }

    public void setTypeRemark(String typeRemark) {
        this.typeRemark = typeRemark;
    }
}
