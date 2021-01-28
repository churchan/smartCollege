package edu.tfswufe.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.tfswufe.entity.Department;
import edu.tfswufe.entity.History;
import edu.tfswufe.entity.Position;
import edu.tfswufe.mapper.DepartmentMapper;
import edu.tfswufe.mapper.HistoryMapper;
import edu.tfswufe.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

@Service
public class HistoryService implements IService<History>{

	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private PositionMapper positionMapper;
	@Autowired
	private HistoryMapper baseMapper;

	/**
	 * history对象setDepartment setPosition
	 * @param history
	 * @return
	 */
	public History setObject(History history){
		Integer departmentNumber = history.getDepartmentNumber();
		if (departmentNumber != null) {
			Department department = departmentMapper.selectByNumber(departmentNumber);
			history.setDepartment(department);
		}else{
			history.setDepartment(null);
		}
		Integer positionNumber = history.getPositionNumber();
		if (positionNumber != null) {
			Position position = positionMapper.selectByNumber(positionNumber);
			history.setPosition(position);
		}else{
			history.setPosition(null);
		}
		return history;
	}


	public Page<History> selectRetireByPage(int pageNo) {
		Page<History> page = new Page<History>(pageNo, 5, "id");
		//是否为升序 ASC（ 默认： true ）
		page.setAsc(false);
		List<History> hList = baseMapper.selectRetireByPage(page);
		for(History h : hList){
			setObject(h);
		}
		page.setRecords(hList);
		return page;
	}


	public History selectHistory(Integer id) {
		History history = baseMapper.selectById(id);
		setObject(history);
		return history;
	}


	public Page<History> selectLisByPage(int pageNo) {
		Page<History> page = new Page<History>(pageNo, 5);
		//是否为升序 ASC（ 默认： true ）
		page.setAsc(false);
		List<History> hList = baseMapper.selectPage(page, null);
		for(History h : hList){
			setObject(h);
		}
		page.setRecords(hList);
		return page;
	}

	public History selectByNumber(Integer employeeNumber) {
		return baseMapper.selectByNumber(employeeNumber);
	}


	public List<History> selectList() {
		List<History> hList = baseMapper.selectList(new EntityWrapper<History>());
		for(History h : hList){
			setObject(h);
		}
		return hList;
	}

	public String selectMaxEmpnum() {
		return baseMapper.selectMaxEmpnum();
	}


	public boolean insert(History entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertAllColumn(History entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<History> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<History> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<History> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<History> entityList, int batchSize) {
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


	public boolean delete(Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateById(History entity) {
		int a=baseMapper.updateById(entity);
		if(a!=0) {
			return true;
		}
		return false;
	}


	public boolean updateAllColumnById(History entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean update(History entity, Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<History> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<History> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdate(History entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public History selectById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<History> selectBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<History> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}


	public History selectOne(Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Map<String, Object> selectMap(Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Object selectObj(Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public int selectCount(Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<History> selectList(Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<History> selectPage(Page<History> page) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Map<String, Object>> selectMaps(Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Object> selectObjs(Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<History> selectPage(Page<History> page, Wrapper<History> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

}
