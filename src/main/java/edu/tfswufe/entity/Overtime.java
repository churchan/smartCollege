package edu.tfswufe.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 加班表
 * @author ZHT
 *
 */
@TableName("overtime")
public class Overtime extends Model<Overtime> implements Serializable{

	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;
	private Integer departmentNumber;
	private Integer personnelNumber;
	private Date day;
	private Date startTime;
	private Date endTime;
	private String notes;

	@TableField(exist=false)
	private Personnel personnel;
	@TableField(exist=false)
	private Department department;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDepartmentNumber() {
		return departmentNumber;
	}
	public void setDepartmentNumber(Integer departmentNumber) {
		this.departmentNumber = departmentNumber;
	}
	public Integer getPersonnelNumber() {
		return personnelNumber;
	}
	public void setPersonnelNumber(Integer personnelNumber) {
		this.personnelNumber = personnelNumber;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


}
