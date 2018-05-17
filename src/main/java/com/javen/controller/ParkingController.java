package com.javen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
@RequestMapping("/platform")  
public class ParkingController {
	private static Logger log = LoggerFactory.getLogger(ParkingController.class);

	/**
	 * 
	 * @param in4OrderPay
	 * @return
	 */
	@RequestMapping(value="/orderPay.api", method=RequestMethod.POST, consumes="application/json", produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String orderPay(@RequestBody Object in4OrderPay){
		return null;
	}
	
}
