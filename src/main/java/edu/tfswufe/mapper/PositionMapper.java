package edu.tfswufe.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import edu.tfswufe.entity.Position;

public interface PositionMapper extends BaseMapper<Position>{

	/**
	 * 根据PositionNumber查询信息
	 * @param positionNumber
	 * @return
	 */
	Position selectByNumber(Integer positionNumber);

	Integer selectMaxNum();
}
