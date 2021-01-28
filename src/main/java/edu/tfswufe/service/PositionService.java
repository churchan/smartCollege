package edu.tfswufe.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.tfswufe.entity.Position;
import edu.tfswufe.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

@Service
public class PositionService implements IService<Position>{

	@Autowired
	private PositionMapper baseMapper;


	public Position selectByNumber(Integer positionNumber) {
		return baseMapper.selectByNumber(positionNumber);
	}


	public Page<Position> selectListByPage(int pageNo) {
		Page<Position> page = new Page<Position>(pageNo, 5, "id");
		page.setAsc(false);
		page.setRecords(baseMapper.selectPage(page, null));
		return page;
	}

	public List<Position> selectList(){
		return baseMapper.selectList(new EntityWrapper<Position>());
	}

	public Integer selectMaxNum(){
		return  baseMapper.selectMaxNum();
	}


	public boolean insert(Position entity) {
		int num=baseMapper.insert(entity);
		if(num!=0) {
			return true;
		}
		return false;
	}


	public boolean insertAllColumn(Position entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<Position> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<Position> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<Position> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<Position> entityList, int batchSize) {
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


	public boolean delete(Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateById(Position entity) {
		int num=baseMapper.updateById(entity);
		if(num!=0) {
			return true;
		}
		return false;
	}


	public boolean updateAllColumnById(Position entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean update(Position entity, Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<Position> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<Position> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdate(Position entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public Position selectById(Serializable id) {
		return baseMapper.selectById(id);
	}


	public List<Position> selectBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Position> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}


	public Position selectOne(Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Map<String, Object> selectMap(Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Object selectObj(Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public int selectCount(Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<Position> selectList(Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Position> selectPage(Page<Position> page) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Map<String, Object>> selectMaps(Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Object> selectObjs(Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Position> selectPage(Page<Position> page, Wrapper<Position> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}
}
