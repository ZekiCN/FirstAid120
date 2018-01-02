package com.hc.trr.platform.aid.entity;

import java.io.Serializable;

public class Aid implements Serializable {
	
	private static final long serialVersionUID = 4646464655854646461L;
	                                             
	private String id;// 内码
	private String code;// 药品编码
	private String name;// 药品名称
	private String pingyin_code;// 拼音码
	private String usages;// 用法
	private String usages_code;//用法编码
	private String dose;// 剂量
	private String dose_unit;// 剂量单位
	private String category_code;// 药品类别编码,从字典表中提取
	private String category_name;// 药品类别编码
	private String is_ambulance;// 是否急救车药物；0：否，1：是
	private String is_antibiotics;// 是否抗生素；0：否，1：是
	private String creator_id;// 创建人内码
	private String creator_name;// 创建人姓名
	private String created_time;// 创建时间
	private String creator_user_id;// 创建用户内码
	private String creator_dept_id;// 创建人部门内码
	private String creator_corp_id;// 创建人法人内码
	private String last_modified_id;// 最后修改人内码
	private String last_modified_name;// 最后修改人姓名
	private String last_modified_time;// 最后修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPingyin_code() {
		return pingyin_code;
	}

	public void setPingyin_code(String pingyin_code) {
		this.pingyin_code = pingyin_code;
	}

	public String getUsages() {
		return usages;
	}

	public void setUsages(String usages) {
		this.usages = usages;
	}
	
	public String getUsages_code() {
		return usages_code;
	}

	public void setUsages_code(String usages_code) {
		this.usages_code = usages_code;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getDose_unit() {
		return dose_unit;
	}

	public void setDose_unit(String dose_unit) {
		this.dose_unit = dose_unit;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getIs_ambulance() {
		return is_ambulance;
	}

	public void setIs_ambulance(String is_ambulance) {
		this.is_ambulance = is_ambulance;
	}

	public String getIs_antibiotics() {
		return is_antibiotics;
	}

	public void setIs_antibiotics(String is_antibiotics) {
		this.is_antibiotics = is_antibiotics;
	}

	public String getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}

	public String getCreator_name() {
		return creator_name;
	}

	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getCreator_user_id() {
		return creator_user_id;
	}

	public void setCreator_user_id(String creator_user_id) {
		this.creator_user_id = creator_user_id;
	}

	public String getCreator_dept_id() {
		return creator_dept_id;
	}

	public void setCreator_dept_id(String creator_dept_id) {
		this.creator_dept_id = creator_dept_id;
	}

	public String getCreator_corp_id() {
		return creator_corp_id;
	}

	public void setCreator_corp_id(String creator_corp_id) {
		this.creator_corp_id = creator_corp_id;
	}

	public String getLast_modified_id() {
		return last_modified_id;
	}

	public void setLast_modified_id(String last_modified_id) {
		this.last_modified_id = last_modified_id;
	}

	public String getLast_modified_name() {
		return last_modified_name;
	}

	public void setLast_modified_name(String last_modified_name) {
		this.last_modified_name = last_modified_name;
	}

	public String getLast_modified_time() {
		return last_modified_time;
	}

	public void setLast_modified_time(String last_modified_time) {
		this.last_modified_time = last_modified_time;
	}

}
