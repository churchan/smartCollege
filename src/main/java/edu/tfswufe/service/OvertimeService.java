package edu.tfswufe.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.tfswufe.entity.Department;
import edu.tfswufe.entity.Employee;
import edu.tfswufe.entity.Overtime;
import edu.tfswufe.mapper.DepartmentMapper;
import edu.tfswufe.mapper.EmployeeMapper;
import edu.tfswufe.mapper.OvertimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

@Service
public class OvertimeService implements IService<Overtime>{

	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private OvertimeMapper baseMapper;

	/**
	 * 为overtime对象setDepartment setPosition
	 * @param overtime
	 * @return
	 */
	public Overtime setObject(Overtime overtime){
		Integer departmentNumber = overtime.getDepartmentNumber();
		Department department = departmentMapper.selectByNumber(departmentNumber);
		overtime.setDepartment(department);

		Integer employeeNumber = overtime.getEmployeeNumber();
		Employee employee = employeeMapper.selectByNumber(employeeNumber);
		overtime.setEmployee(employee);
		return overtime;
	}


	public Page<Overtime> selectListByPage(int pageNo) {
		Page<Overtime> page = new Page<Overtime>(pageNo,4,"id");
		//是否为升序 ASC（ 默认： true ）
		page.setAsc(false);
		List<Overtime> oList = baseMapper.selectPage(page, null);
		for(Overtime overtime : oList){
			setObject(overtime);
		}
		page.setRecords(oList);
		return page;
	}


	public Page<Overtime> selectByEmployee(int pageNo, Integer employeeNumber) {
		Page<Overtime> page = new Page<Overtime>(pageNo, 4, "id");
		//是否为升序 ASC（ 默认： true ）
		page.setAsc(false);
		 //查询一个员工的考勤记录，根据id倒序排序
		List<Overtime> oList = baseMapper.selectPage(page, new EntityWrapper<Overtime>()
				.eq("employee_number", employeeNumber)
				.orderBy("id", false));
		for(Overtime overtime : oList){
			//为attendance对象setEmployee
			setObject(overtime);
		}
		page.setRecords(oList);
		return page;
	}


	public boolean insert(Overtime entity) {
		int num=baseMapper.insert(entity);
		if(num!=0) {
			return true;
		}
		return false;
	}


	public boolean insertAllColumn(Overtime entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<Overtime> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<Overtime> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<Overtime> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<Overtime> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteById(Serializable id) {
		int num=baseMapper.deleteById(id);
		if(num!=0) {
			return true;
		}
		return false;
	}


	public boolean deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean delete(Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateById(Overtime entity) {
		int num=baseMapper.updateById(entity);
		if(num!=0) {
			return true;
		}
		return false;
	}


	public boolean updateAllColumnById(Overtime entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean update(Overtime entity, Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<Overtime> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<Overtime> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdate(Overtime entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public Overtime selectById(Serializable id) {
		return baseMapper.selectById(id);
	}


	public List<Overtime> selectBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Overtime> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}


	public Overtime selectOne(Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Map<String, Object> selectMap(Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Object selectObj(Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public int selectCount(Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<Overtime> selectList(Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Overtime> selectPage(Page<Overtime> page) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Map<String, Object>> selectMaps(Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Object> selectObjs(Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Overtime> selectPage(Page<Overtime> page, Wrapper<Overtime> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

}
