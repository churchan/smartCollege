package edu.tfswufe.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.tfswufe.entity.Department;
import edu.tfswufe.entity.Employee;
import edu.tfswufe.entity.Move;
import edu.tfswufe.entity.Overtime;
import edu.tfswufe.mapper.DepartmentMapper;
import edu.tfswufe.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import edu.tfswufe.mapper.MoveMapper;

@Service
public class MoveService implements IService<Move>{


	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private MoveMapper baseMapper;

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


	public List<Move> selectList() {
		//查询所有记录，根据id倒序排序
		List<Move> list = baseMapper.selectList(new EntityWrapper<Move>().
				orderBy("id", false));
		for(Move move : list){
			Employee employee = employeeMapper.selectByNumber(move.getEmployeeNumber());
			move.setEmployee(employee);
			Department department = departmentMapper.selectByNumber(move.getBefore());
			move.setDepartment(department);
			Department department2 = departmentMapper.selectByNumber(move.getAfter());
			move.setDepartment2(department2);
		}
		return list;
	}


	public boolean insert(Move entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertAllColumn(Move entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<Move> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<Move> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<Move> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<Move> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean delete(Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateById(Move entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateAllColumnById(Move entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean update(Move entity, Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<Move> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<Move> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdate(Move entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public Move selectById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Move> selectBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Move> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}


	public Move selectOne(Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Map<String, Object> selectMap(Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Object selectObj(Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public int selectCount(Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<Move> selectList(Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Move> selectPage(Page<Move> page) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Map<String, Object>> selectMaps(Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Object> selectObjs(Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Move> selectPage(Page<Move> page, Wrapper<Move> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}
}
