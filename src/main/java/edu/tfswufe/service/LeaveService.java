package edu.tfswufe.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.tfswufe.entity.Department;
import edu.tfswufe.entity.Personnel;
import edu.tfswufe.entity.Leave;
import edu.tfswufe.mapper.DepartmentMapper;
import edu.tfswufe.mapper.PersonnelMapper;
import edu.tfswufe.mapper.LeaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

@Service
public class LeaveService implements IService<Leave>{

	@Autowired
	private PersonnelMapper personnelMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private LeaveMapper baseMapper;

	/**
	 * 为leave对象setPersonnel setDepartment
	 * @param leave
	 * @return
	 */
	public Leave setObject(Leave leave){
		Integer personnelNumber = leave.getPersonnelNumber();
		Personnel personnel = personnelMapper.selectByNumber(personnelNumber);
		leave.setPersonnel(personnel);

		Integer departmentNumber = leave.getDepartmentNumber();
		Department department = departmentMapper.selectByNumber(departmentNumber);
		leave.setDepartment(department);
		return leave;
	}


	public List<Leave> selectList() {
		List<Leave> list = baseMapper.selectList(new EntityWrapper<Leave>()
				.orderBy("start_time",false));
		for(Leave leave : list){
			//为leave对象setPersonnel setDepartment
			setObject(leave);
		}
		return list;
	}


	public Leave selectLeave(Integer id) {
		Leave leave = baseMapper.selectById(id);
		//为leave对象setPersonnel setDepartment
		setObject(leave);
		return leave;
	}


	public void updateStatus(Integer id) {
		Leave leave = baseMapper.selectById(id);
		leave.setStatus("已批准");
		baseMapper.updateById(leave);
	}


	public Page<Leave> selectByPersonnel(Integer personnelNumber, int pageNo) {
		Page<Leave> page = new Page<Leave>(pageNo, 2,"status");
		//是否为升序 ASC（ 默认： true ）
		page.setAsc(false);
		List<Leave> list = baseMapper.selectPage(page, new EntityWrapper<Leave>()
				.eq("personnel_number", personnelNumber));
		for(Leave leave : list){
			//为leave对象setPersonnel setDepartment
			setObject(leave);
		}
		page.setRecords(list);
		return page;
	}


	public List<Leave> selectListByStatus(Integer deparmentNumber, String status) {
		List<Leave> list = baseMapper.selectList(new EntityWrapper<Leave>()
				.eq("department_number", deparmentNumber)
				.eq("status", status)
				.orderBy("id",false));
		for(Leave leave : list){
			//为leave对象setPersonnel setDepartment
			setObject(leave);
		}
		return list;
	}

	public boolean insert(Leave entity) {
		int num=baseMapper.insert(entity);
		if(num!=0) {
			return true;
		}
		return false;
	}

	public boolean insertAllColumn(Leave entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insertBatch(List<Leave> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insertBatch(List<Leave> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insertOrUpdateBatch(List<Leave> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insertOrUpdateBatch(List<Leave> entityList, int batchSize) {
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

	public boolean delete(Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateById(Leave entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateAllColumnById(Leave entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Leave entity, Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateBatchById(List<Leave> entityList) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateBatchById(List<Leave> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insertOrUpdate(Leave entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public Leave selectById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Leave> selectBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Leave> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public Leave selectOne(Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> selectMap(Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectObj(Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	public int selectCount(Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Leave> selectList(Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Leave> selectPage(Page<Leave> page) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> selectMaps(Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object> selectObjs(Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Leave> selectPage(Page<Leave> page, Wrapper<Leave> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

}
