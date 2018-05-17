package com.javen.dao.park;

import org.apache.ibatis.annotations.Select;

import com.javen.model.park.Park;


public interface IParkDao {

	@Select("SELECT * FROM t_park WHERE out_park_id = #{park_id}")
	public Park findParkById(Object park_id);

	@Select("SELECT * FROM t_park WHERE id = #{park_id}")
	public Park findParkByParkId(Long park_id);
	
}
