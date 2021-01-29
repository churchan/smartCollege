package edu.tfswufe.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import edu.tfswufe.entity.Department;
import edu.tfswufe.entity.Personnel;
import edu.tfswufe.entity.History;
import edu.tfswufe.entity.Move;
import edu.tfswufe.entity.Position;
import edu.tfswufe.mapper.DepartmentMapper;
import edu.tfswufe.mapper.PersonnelMapper;
import edu.tfswufe.mapper.HistoryMapper;
import edu.tfswufe.mapper.MoveMapper;
import edu.tfswufe.mapper.PositionMapper;

@Service
public class PersonnelService{

	@Autowired
	private HistoryMapper historyMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private PositionMapper positionMapper;
	@Autowired
	private MoveMapper moveMapper;
	@Autowired
	private PersonnelMapper baseMapper;
	@Autowired
	private HistoryService historyService;

	/**
	 * 为personnel对象setDepartment setPosition
	 * @param personnel
	 * @return
	 */
	public Personnel setObject(Personnel personnel){
		Integer departmentNumber = personnel.getDepartmentNumber();
		Department department = departmentMapper.selectByNumber(departmentNumber);
		personnel.setDepartment(department);

		Integer positionNumber = personnel.getPositionNumber();
		Position position = positionMapper.selectByNumber(positionNumber);
		personnel.setPosition(position);
		return personnel;
	}

	public Personnel checkLogin(Integer personnelNumber, String password) {
		  Personnel personnel = baseMapper.checkLogin(personnelNumber, password);
		  if (personnel != null) {
			  //为personnel对象中setDepartment setPosition
			  setObject(personnel);
		  }
		  return personnel;
	}


	public List<Personnel> select(Integer personnelNumber, String password){
		 List<Personnel> eList = baseMapper.selectList(new EntityWrapper<Personnel>()
				.eq("personnel_number", personnelNumber)
				.eq("password", password));
		 return eList;
	}



	public Page<Personnel> selectListByPage(int pageNo) {
		Page<Personnel> page = new Page<Personnel>(pageNo, 4,"id");
		//是否为升序 ASC（ 默认： true ）
		page.setAsc(false);
		List<Personnel> eList = baseMapper.selectPage(page, null);
		for(Personnel e : eList){
			//为personnel对象中setDepartment setPosition
			setObject(e);
		}
		page.setRecords(eList);
		return page;
	}

	@Transactional
	public void addPersonnel(Personnel personnel) {
		String personnelNumber=historyService.selectMaxEmpnum();
		int i = Integer.parseInt( personnelNumber );
		//向personnel中插入记录
		personnel.setInTime(new Date());
		baseMapper.insert(personnel);
		//同时向history中插入记录
		History history = new History();
		history.setPersonnelNumber(personnel.getPersonnelNumber());
		history.setName(personnel.getName());
		history.setGender(personnel.getGender());
		history.setBirthday(personnel.getBirthday());
		history.setTelephone(personnel.getTelephone());
		history.setEmail(personnel.getEmail());
		history.setAddress(personnel.getAddress());
		history.setPhoto(personnel.getPhoto());
		history.setEducation(personnel.getEducation());
		history.setInTime(personnel.getInTime());
		history.setDepartmentNumber(personnel.getDepartmentNumber());
		history.setPositionNumber(personnel.getPositionNumber());
		history.setStatus("在读");
		history.setNotes(personnel.getNotes());
		historyMapper.insert(history);
	}


	public Personnel selectPersonnel(Integer id) {
		Personnel personnel = baseMapper.selectById(id);
	    //向personnel对象中setDepartment setPosition
		setObject(personnel);
		return personnel;
	}

	@Transactional
	public void updatePersonnel(Personnel personnel, String status, String manager) {
		//判断员工的在读状态是否改变
		if (status.equals("在读")) {
			//状态未改变，更新员工信息
			//获取员工原始信息，用于判断部门或职称是否改变
			Personnel personnel2 = baseMapper.selectById(personnel.getId());
			Move move = new Move();
			move.setPersonnelNumber(personnel.getPersonnelNumber());
			move.setTime(new Date());
			move.setManager(manager);
			System.out.println(personnel.getDepartmentNumber());
			//判断员工的部门是否改变，若改变向change中插入一条员工变动记录
			if(personnel.getDepartmentNumber()!=null&&!personnel.getDepartmentNumber().equals(personnel2.getDepartmentNumber())){
				move.setBefore(personnel2.getDepartmentNumber());
				move.setAfter(personnel.getDepartmentNumber());
				moveMapper.insert(move);
			}
			baseMapper.updateById(personnel);
		}else{
			//状态变为离职或退休
			//删除在读员工记录
			baseMapper.deleteById(personnel.getId());
			//更新员工档案的状态
			History history = historyMapper.selectByNumber(personnel.getPersonnelNumber());
			history.setStatus(status);
			history.setOutTime(new Date());
			historyMapper.updateById(history);
		}
	}


	public Personnel selectByNumber(Integer personnelNumber) {
		return baseMapper.selectByNumber(personnelNumber);
	}

	@Transactional
	public void deletePersonnel(Integer id) {
		//先查询再删除，否则NullPointerException
		Personnel personnel = baseMapper.selectById(id);
		//删除在读员工记录
		baseMapper.deleteById(id);
		//将员工档案表中的状态改为离职
		History history = historyMapper.selectByNumber(personnel.getPersonnelNumber());
		history.setStatus("离职");
		historyMapper.updateById(history);
	}


	public Page<Personnel> search(String input, int pageNo) {
		Page<Personnel> page = new Page<Personnel>(pageNo, 4, "id");
		//是否为升序 ASC（ 默认： true ）
		page.setAsc(false);
		List<Personnel> eList = baseMapper.selectPage(page, new EntityWrapper<Personnel>()
				.like("name", input));
		for(Personnel e : eList){
			//为personnel对象中setDepartment setPosition
			setObject(e);
		}
		page.setRecords(eList);
		return page;
	}

	public List<Personnel> selectList(){
		return  baseMapper.selectList(new EntityWrapper<Personnel>());
	}

	public boolean updateById(Personnel personnel) {
		int a=baseMapper.updateById(personnel);
		if(a!=0) {
			return true;
		}
		return false;
	}

	public boolean deleteById(int i) {
		int a=baseMapper.deleteById(i);
		if(a!=0) {
			return true;
		}
		return false;
	}


}
