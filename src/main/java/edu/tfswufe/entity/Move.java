package edu.tfswufe.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 员工调动记录表
 * @author ZHT
 *
 */
@TableName("move")
public class Move extends Model<Move> implements Serializable{

	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;
	private Integer personnelNumber;
	private Integer before;
	private Integer after;
	private Date time;
	private String manager;
	private String notes;

	@TableField(exist=false)
	private Personnel personnel;
	@TableField(exist=false)
	private Department department;
	@TableField(exist=false)
	private Department department2;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPersonnelNumber() {
		return personnelNumber;
	}
	public void setPersonnelNumber(Integer personnelNumber) {
		this.personnelNumber = personnelNumber;
	}
	public Integer getBefore() {
		return before;
	}
	public void setBefore(Integer before) {
		this.before = before;
	}
	public Integer getAfter() {
		return after;
	}
	public void setAfter(Integer after) {
		this.after = after;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
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
	public Department getDepartment2() {
		return department2;
	}
	public void setDepartment2(Department department2) {
		this.department2 = department2;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	@Override
	public String toString() {
		return "Move [id=" + id + ", personnelNumber=" + personnelNumber + ", before=" + before + ", after=" + after
				+ ", time=" + time + ", manager=" + manager + ", notes=" + notes + ", personnel=" + personnel
				+ ", department=" + department + ", department2=" + department2 + "]";
	}


}
