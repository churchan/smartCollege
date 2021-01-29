package edu.tfswufe.mapper;

import java.util.Date;

import edu.tfswufe.entity.Attendance;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface AttendanceMapper extends BaseMapper<Attendance>{

	/**
	 * 根据personnelNumber和day查询记录
	 * @param personnelNumber
	 * @return
	 */
	Attendance selectByNumber(@Param("personnelNumber")Integer personnelNumber,
			@Param("day")Date day, @Param("timeType")String timeType);
}
