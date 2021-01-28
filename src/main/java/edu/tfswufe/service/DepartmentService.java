package edu.tfswufe.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.tfswufe.entity.Department;
import edu.tfswufe.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

@Service
public class DepartmentService implements IService<Department>{

	@Autowired
	private DepartmentMapper baseMapper;

	public Department selectByNumber(Integer departmentNumber) {
		return baseMapper.selectByNumber(departmentNumber);
	}


	public Page<Department> selectListByPage(int pageNo) {
		Page<Department> page = new Page<Department>(pageNo, 5, "id");
		//是否为升序 ASC（ 默认： true ）
		page.setAsc(false);
		page.setRecords(baseMapper.selectPage(page, null));
		return page;
	}

	public List<Department> selectList(){
		return  baseMapper.selectList(new EntityWrapper<Department>());
	}

	public Integer selectMaxNum(){
		return  baseMapper.selectMaxNum();
	}

	public boolean insert(Department entity) {
		int num=baseMapper.insert(entity);
		if(num !=0) {
			return true;
		}
		return false;
	}


	public boolean insertAllColumn(Department entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<Department> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<Department> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<Department> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<Department> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteById(Serializable id) {
		int num=baseMapper.deleteById(id);
		if(num !=0) {
			return true;
		}
		return false;
	}


	public boolean deleteByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean delete(Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateById(Department entity) {
		int num=baseMapper.updateById(entity);
		if(num!=0) {
			return true;
		}
		return false;
	}


	public boolean updateAllColumnById(Department entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean update(Department entity, Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<Department> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<Department> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdate(Department entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public Department selectById(Serializable id) {
		return baseMapper.selectById(id);
	}


	public List<Department> selectBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Department> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}


	public Department selectOne(Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Map<String, Object> selectMap(Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Object selectObj(Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public int selectCount(Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<Department> selectList(Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Department> selectPage(Page<Department> page) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Map<String, Object>> selectMaps(Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Object> selectObjs(Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Department> selectPage(Page<Department> page, Wrapper<Department> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

}
