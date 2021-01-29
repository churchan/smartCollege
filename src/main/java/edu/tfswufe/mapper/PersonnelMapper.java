package edu.tfswufe.mapper;

import edu.tfswufe.entity.Personnel;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface PersonnelMapper extends BaseMapper<Personnel>{

	/**
	 * 登录验证
	 * @param personnelNumber
	 * @param password
	 * @return
	 */
	Personnel checkLogin(@Param("personnelNumber")Integer personnelNumber,
			@Param("password")String password);

	/**
	 * 根据personnelNumber查询信息
	 * @param personnelNumber
	 * @return
	 */
	Personnel selectByNumber(Integer personnelNumber);

}
