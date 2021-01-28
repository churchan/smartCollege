package edu.tfswufe.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.tfswufe.entity.Attendance;
import edu.tfswufe.entity.Employee;
import edu.tfswufe.mapper.AttendanceMapper;
import edu.tfswufe.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import edu.tfswufe.util.MConstant;
import edu.tfswufe.util.MTimeUtil;

@Service
public class AttendanceService implements IService<Attendance>{

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private AttendanceMapper baseMapper;

	//获取相关时间
	Date amTime = MTimeUtil.stringTimeParse(MConstant.AMTime);
	Date amStartTime = MTimeUtil.stringTimeParse(MConstant.AMStartTime);
	Date amEndTime = MTimeUtil.stringTimeParse(MConstant.AMEndTime);
	Date pmTime = MTimeUtil.stringTimeParse(MConstant.PMTime);
	Date pmStartTime = MTimeUtil.stringTimeParse(MConstant.PMStartTime);
	Date pmEndTime = MTimeUtil.stringTimeParse(MConstant.PMEndTime);
	Date ovTime = MTimeUtil.stringTimeParse(MConstant.OVTime);
	Date ovStartTime = MTimeUtil.stringTimeParse(MConstant.OVStartTime);
	Date ovEndTime = MTimeUtil.stringTimeParse(MConstant.OVEndTime);


	public void addStart(Integer employeeNumber){
		//获取当前时间
		Date nowTime = MTimeUtil.nowTime();
		//获取当前日期
		Date nowDate = MTimeUtil.nowDate();
		Attendance attendance = new Attendance();
		attendance.setEmployeeNumber(employeeNumber);
		attendance.setDay(nowDate);
		attendance.setStartTime(nowTime);
		if (nowTime.after(amTime) && nowTime.before(amEndTime)) {
			Attendance att = baseMapper.selectByNumber(employeeNumber, nowDate, "上午");
			if (att == null) {
				attendance.setTimeType("上午");
				if (nowTime.before(amStartTime)) {
					attendance.setStartType("正常");
				}else{
					attendance.setStartType("迟到");
				}
				baseMapper.insert(attendance);
			}
		}else if(nowTime.after(pmTime) && nowTime.before(pmEndTime)){
			Attendance att = baseMapper.selectByNumber(employeeNumber, nowDate, "下午");
			if (att == null) {
				attendance.setTimeType("下午");
				if (nowTime.before(pmStartTime)) {
					attendance.setStartType("正常");
				}else{
					attendance.setStartType("迟到");
				}
				baseMapper.insert(attendance);
			}
		}else if(nowTime.after(ovTime) && nowTime.before(ovEndTime)){
			Attendance att = baseMapper.selectByNumber(employeeNumber, nowDate, "加班");
			if (att == null) {
				attendance.setTimeType("加班");
				if (nowTime.before(ovStartTime)) {
					attendance.setStartType("正常");
				}else{
					attendance.setStartType("迟到");
				}
				baseMapper.insert(attendance);
			}
		}
	}


	public void addEnd(Integer employeeNumber) {
		Date nowTime = MTimeUtil.nowTime();
		Date nowDate = MTimeUtil.nowDate();
		if (nowTime.after(amStartTime) && nowTime.before(pmStartTime)) {
			Attendance attendance = baseMapper.selectByNumber(employeeNumber, nowDate, "上午");
			if (attendance.getEndTime() == null) {
				attendance.setEndTime(nowTime);
				if (nowTime.after(amEndTime)) {
					attendance.setEndType("正常");
				}else{
					attendance.setEndType("早退");
				}
				baseMapper.updateById(attendance);
			}
		}else if(nowTime.after(pmStartTime) && nowTime.before(ovStartTime)){
			Attendance attendance = baseMapper.selectByNumber(employeeNumber, nowDate, "下午");
			if (attendance.getEndTime() == null) {
				attendance.setEndTime(nowTime);
				if (nowTime.after(pmEndTime)) {
					attendance.setEndType("正常");
				}else{
					attendance.setEndType("早退");
				}
				baseMapper.updateById(attendance);
			}
		}else if(nowTime.after(ovStartTime)){
			Attendance attendance = baseMapper.selectByNumber(employeeNumber, nowDate, "加班");
			if (attendance.getEndTime() == null) {
				attendance.setEndTime(nowTime);
				if (nowTime.after(ovEndTime)) {
					attendance.setEndType("正常");
				}else{
					attendance.setEndType("早退");
				}
				baseMapper.updateById(attendance);
			}
		}
	}


	public List<Attendance> selectList() {
		 //查询所有的考勤记录，根据id倒序排序
		List<Attendance> list = baseMapper.selectList(new EntityWrapper<Attendance>().
				orderBy("id", false));
		for(Attendance attendance : list){
			//为attendance对象setEmployee
			Employee employee = employeeMapper.selectByNumber(attendance.getEmployeeNumber());
			attendance.setEmployee(employee);
		}
		return list;
	}


	public List<Attendance> selectByEmployee(Integer employeeNumber) {
		 //查询一个员工的考勤记录，根据id倒序排序
		List<Attendance> list = baseMapper.selectList(new EntityWrapper<Attendance>()
				.eq("employee_number", employeeNumber)
				.orderBy("id", false));
		for(Attendance attendance : list){
			//为attendance对象setEmployee
			Employee employee = employeeMapper.selectByNumber(attendance.getEmployeeNumber());
			attendance.setEmployee(employee);
		}
		return list;
	}


	public boolean insert(Attendance entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertAllColumn(Attendance entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<Attendance> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertBatch(List<Attendance> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<Attendance> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdateBatch(List<Attendance> entityList, int batchSize) {
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


	public boolean delete(Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateById(Attendance entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateAllColumnById(Attendance entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean update(Attendance entity, Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<Attendance> entityList) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateBatchById(List<Attendance> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean insertOrUpdate(Attendance entity) {
		// TODO Auto-generated method stub
		return false;
	}


	public Attendance selectById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Attendance> selectBatchIds(List<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Attendance> selectByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}


	public Attendance selectOne(Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Map<String, Object> selectMap(Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Object selectObj(Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public int selectCount(Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<Attendance> selectList(Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Attendance> selectPage(Page<Attendance> page) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Map<String, Object>> selectMaps(Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Object> selectObjs(Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}


	public Page<Attendance> selectPage(Page<Attendance> page, Wrapper<Attendance> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}
}
