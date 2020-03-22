package com.jason.test.entity;

import com.jason.base.tags.grid.JqGridConstants;
import com.jason.base.tags.grid.annotation.Editoptions;
import com.jason.base.tags.grid.annotation.Editrules;
import com.jason.base.tags.grid.annotation.JqGrid;
import com.jason.base.tags.grid.annotation.Searchoptions;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "test_people")
@JqGrid(displayName = "人员列表")
public class TestPeople implements Serializable {

	private static final long serialVersionUID = -8279206827092660956L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@JqGrid(displayName="序号",serial=1,width=45,search=false)
	@Editoptions(readonly=true)
	private Integer id;

	@JqGrid(displayName="姓名",serial=2,align="center",width=90,editable=true)
	@Editoptions(size=10,maxLength=7)
	@Searchoptions(sopt={"cn","nc","bw","bn","ew","en"})
	private String name;

	@JqGrid(displayName="性别",serial=3,align="center",width=60,editable=true,edittype="select",formatter="select",stype="select")
	@Editoptions(value="0:待定;1:男;2:女")
	@Searchoptions(sopt={"eq"},value="0:待定;1:男;2:女")
	private String sex = "0";//性别 0待定 1男 2女

	@JqGrid(displayName="年龄",serial=4,align="right",width=50,editable=true)
	@Editoptions(size=5)
	@Editrules(required=true,number=true,minValue=10,maxValue=120,custom=true,custom_func="checkAge")
	@Searchoptions(sopt={"eq","ne","lt","le","gt","ge"})
	private Integer age;

	@JqGrid(displayName="密码",serial=5,hidden=true,editable=true,edittype="password",search=false)
	@Editrules(edithidden=true,required=true)
	@Editoptions(size=10,maxLength=10)
	private String password;

	@JqGrid(displayName="出生日期",serial=6,width=70,editable=true)
	@Editoptions(size=12,dataInit=JqGridConstants.TAG_DATE_INIT_FUNC)
	@Searchoptions(dataInit=JqGridConstants.TAG_DATE_INIT_FUNC,sopt={"eq","ne","lt","le","gt","ge"})
	private Date birthday;

	@JqGrid(displayName="启用状态",serial=7,width=50,formatter="select",stype="select")
	@Editoptions(value="0:未启用;1:启用")
	@Searchoptions(sopt={"eq"},value="0:未启用;1:启用")
	private String status = "0";//状态 0未启用 1启用 

	@Column(name="edit_date")
	@JqGrid(displayName="更新日期",serial=8,width=70)
	@Searchoptions(dataInit=JqGridConstants.TAG_DATE_INIT_FUNC,sopt={"eq","ne","lt","le","gt","ge"})
	private Date editDate;//修改时间 

	@JqGrid(displayName="开关",serial=9,width=50,formatter="select",stype="select")
	@Editoptions(value="false:关闭;true:开启")
	@Searchoptions(sopt={"eq"},value="false:关闭;true:开启")
	private Boolean onoff = false;

	@JqGrid(displayName="字典编码",serial=10,width=50,editable=true,edittype="select",stype="select")
	@Editoptions(dataUrl="/dictionary/dictDataUrlCodeNamesByTypeCode.do?typeCode=001")
	@Searchoptions(sopt={"eq"},dataUrl="/dictionary/dictDataUrlCodeNamesByTypeCode.do?typeCode=001")
	private String dictCode;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public Boolean getOnoff() {
		return onoff;
	}
	public void setOnoff(Boolean onoff) {
		this.onoff = onoff;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
}
