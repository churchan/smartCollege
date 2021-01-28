package edu.tfswufe.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import edu.tfswufe.entity.Department;
import edu.tfswufe.entity.Employee;
import edu.tfswufe.entity.History;
import edu.tfswufe.entity.Move;
import edu.tfswufe.entity.Position;
import edu.tfswufe.mapper.DepartmentMapper;
import edu.tfswufe.mapper.EmployeeMapper;
import edu.tfswufe.mapper.HistoryMapper;
import edu.tfswufe.mapper.MoveMapper;
import edu.tfswufe.mapper.PositionMapper;

@Service
public class EmployeeService{

	@Autowired
	private HistoryMapper historyMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private PositionMapper positionMapper;
	@Autowired
	private MoveMapper moveMapper;
	@Autowired
	private EmployeeMapper baseMapper;
	@Autowired
	private HistoryService historyService;

	/**
	 * 为employee对象setDepartment setPosition
	 * @param employee
	 * @return
	 */
	public Employee setObject(Employee employee){
		Integer departmentNumber = employee.getDepartmentNumber();
		Department department = departmentMapper.selectByNumber(departmentNumber);
		employee.setDepartment(department);

		Integer positionNumber = employee.getPositionNumber();
		Position position = positionMapper.selectByNumber(positionNumber);
		employee.setPosition(position);
		return employee;
	}

	public Employee checkLogin(Integer employeeNumber, String password) {
		  Employee employee = baseMapper.checkLogin(employeeNumber, password);
		  if (employee != null) {
			  //为employee对象中setDepartment setPosition
			  setObject(employee);
		  }
		  return employee;
	}


	public List<Employee> select(Integer employeeNumber, String password){
		 List<Employee> eList = baseMapper.selectList(new EntityWrapper<Employee>()
				.eq("employee_number", employeeNumber)
				.eq("password", password));
		 return eList;
	}



	public Page<Employee> selectListByPage(int pageNo) {
		Page<Employee> page = new Page<Employee>(pageNo, 4,"id");
		//是否为升序 ASC（ 默认： true ）
		page.setAsc(false);
		List<Employee> eList = baseMapper.selectPage(page, null);
		for(Employee e : eList){
			//为employee对象中setDepartment setPosition
			setObject(e);
		}
		page.setRecords(eList);
		return page;
	}

	@Transactional
	public void addEmployee(Employee employee) {
		String employeeNumber=historyService.selectMaxEmpnum();
		int i = Integer.parseInt( employeeNumber );
		//向employee中插入记录
		employee.setInTime(new Date());
		baseMapper.insert(employee);
		//同时向history中插入记录
		History history = new History();
		history.setEmployeeNumber(employee.getEmployeeNumber());
		history.setName(employee.getName());
		history.setGender(employee.getGender());
		history.setBirthday(employee.getBirthday());
		history.setTelephone(employee.getTelephone());
		history.setEmail(employee.getEmail());
		history.setAddress(employee.getAddress());
		history.setPhoto(employee.getPhoto());
		history.setEducation(employee.getEducation());
		history.setInTime(employee.getInTime());
		history.setDepartmentNumber(employee.getDepartmentNumber());
		history.setPositionNumber(employee.getPositionNumber());
		history.setStatus("在职");
		history.setNotes(employee.getNotes());
		historyMapper.insert(history);
	}


	public Employee selectEmployee(Integer id) {
		Employee employee = baseMapper.selectById(id);
	    //向employee对象中setDepartment setPosition
		setObject(employee);
		return employee;
	}

	@Transactional
	public void updateEmployee(Employee employee, String status, String manager) {
		//判断员工的在职状态是否改变
		if (status.equals("在职")) {
			//状态未改变，更新员工信息
			//获取员工原始信息，用于判断部门或职称是否改变
			Employee employee2 = baseMapper.selectById(employee.getId());
			Move move = new Move();
			move.setEmployeeNumber(employee.getEmployeeNumber());
			move.setTime(new Date());
			move.setManager(manager);
			System.out.println(employee.getDepartmentNumber());
			//判断员工的部门是否改变，若改变向change中插入一条员工变动记录
			if(employee.getDepartmentNumber()!=null&&!employee.getDepartmentNumber().equals(employee2.getDepartmentNumber())){
				move.setBefore(employee2.getDepartmentNumber());
				move.setAfter(employee.getDepartmentNumber());
				moveMapper.insert(move);
			}
			baseMapper.updateById(employee);
		}else{
			//状态变为离职或退休
			//删除在职员工记录
			baseMapper.deleteById(employee.getId());
			//更新员工档案的状态
			History history = historyMapper.selectByNumber(employee.getEmployeeNumber());
			history.setStatus(status);
			history.setOutTime(new Date());
			historyMapper.updateById(history);
		}
	}


	public Employee selectByNumber(Integer employeeNumber) {
		return baseMapper.selectByNumber(employeeNumber);
	}

	@Transactional
	public void deleteEmployee(Integer id) {
		//先查询再删除，否则NullPointerException
		Employee employee = baseMapper.selectById(id);
		//删除在职员工记录
		baseMapper.deleteById(id);
		//将员工档案表中的状态改为离职
		History history = historyMapper.selectByNumber(employee.getEmployeeNumber());
		history.setStatus("离职");
		historyMapper.updateById(history);
	}


	public Page<Employee> search(String input, int pageNo) {
		Page<Employee> page = new Page<Employee>(pageNo, 4, "id");
		//是否为升序 ASC（ 默认： true ）
		page.setAsc(false);
		List<Employee> eList = baseMapper.selectPage(page, new EntityWrapper<Employee>()
				.like("name", input));
		for(Employee e : eList){
			//为employee对象中setDepartment setPosition
			setObject(e);
		}
		page.setRecords(eList);
		return page;
	}

	public List<Employee> selectList(){
		return  baseMapper.selectList(new EntityWrapper<Employee>());
	}

	public boolean updateById(Employee employee) {
		int a=baseMapper.updateById(employee);
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
