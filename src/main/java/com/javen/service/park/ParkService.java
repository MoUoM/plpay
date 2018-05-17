package com.javen.service.park;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.javen.dao.park.IParkDao;
import com.javen.model.park.Park;

@Service
@Lazy
public class ParkService {

	@Autowired
	private IParkDao parkDao;
	
	public Park findParkByOutParkId(Object out_park_id){
		return parkDao.findParkById(out_park_id);
	}

	public Park findParkByParkId(Long park_id) {
		// TODO Auto-generated method stub
		return parkDao.findParkByParkId(park_id);
	}
	
}
