package com.javen.in4param;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class In4WXOrderPay {

	private String provider_order_id;	//服务商订单号
	private String provider_parking_id;//车辆入场唯一编号
	private String provider_id;			//服务商ID
	private String parking_id;
	private String car_number;
	private String timestamp;
	private String nonce_str;
	private String fee_type;			//货币种类
	private String total_fee;			//订单金额
	private String pay_fee;				//用户支付金额
	private String invoice_flag;		//是否需要发票
	private String parking_duration;	//停车时长
	private String pay_notify_url; 		//扣款结果通知url
	private String sign;
	
	public Map<String, String> toParameters(){
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isEmpty(provider_order_id)) {
			map.put("provider_order_id", provider_order_id);
		}
		if (!StringUtils.isEmpty(provider_parking_id)) {
			map.put("provider_parking_id", provider_parking_id);
		}
		if (!StringUtils.isEmpty(provider_id)) {
			map.put("provider_id", provider_id);
		}
		if (!StringUtils.isEmpty(parking_id)) {
			map.put("parking_id", parking_id);
		}
		if (!StringUtils.isEmpty(car_number)) {
			map.put("car_number", car_number);
		}
		if (!StringUtils.isEmpty(timestamp)) {
			map.put("timestamp", timestamp);
		}
		if (!StringUtils.isEmpty(nonce_str)) {
			map.put("nonce_str", nonce_str);
		}
		if (!StringUtils.isEmpty(fee_type)) {
			map.put("fee_type", fee_type);
		}
		if (!StringUtils.isEmpty(total_fee)) {
			map.put("total_fee", total_fee);
		}
		if (!StringUtils.isEmpty(pay_fee)) {
			map.put("pay_fee", pay_fee);
		}
		if (!StringUtils.isEmpty(invoice_flag)) {
			map.put("invoice_flag", invoice_flag);
		}
		if (!StringUtils.isEmpty(parking_duration)) {
			map.put("parking_duration", parking_duration);
		}
		if (!StringUtils.isEmpty(pay_notify_url)) {
			map.put("pay_notify_url", pay_notify_url);
		}
		if (!StringUtils.isEmpty(sign)) {
			map.put("sign", sign);
		}
		return map;
	}
	
	
	public String getProvider_order_id() {
		return provider_order_id;
	}
	public void setProvider_order_id(String provider_order_id) {
		this.provider_order_id = provider_order_id;
	}
	public String getProvider_parking_id() {
		return provider_parking_id;
	}
	public void setProvider_parking_id(String provider_parking_id) {
		this.provider_parking_id = provider_parking_id;
	}
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}
	public String getParking_id() {
		return parking_id;
	}
	public void setParking_id(String parking_id) {
		this.parking_id = parking_id;
	}
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getPay_fee() {
		return pay_fee;
	}
	public void setPay_fee(String pay_fee) {
		this.pay_fee = pay_fee;
	}
	public String getInvoice_flag() {
		return invoice_flag;
	}
	public void setInvoice_flag(String invoice_flag) {
		this.invoice_flag = invoice_flag;
	}
	public String getParking_duration() {
		return parking_duration;
	}
	public void setParking_duration(String parking_duration) {
		this.parking_duration = parking_duration;
	}
	public String getPay_notify_url() {
		return pay_notify_url;
	}
	public void setPay_notify_url(String pay_notify_url) {
		this.pay_notify_url = pay_notify_url;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "In4WXOrderPay [provider_order_id=" + provider_order_id + ", provider_parking_id=" + provider_parking_id
				+ ", provider_id=" + provider_id + ", parking_id=" + parking_id + ", car_number=" + car_number
				+ ", timestamp=" + timestamp + ", nonce_str=" + nonce_str + ", fee_type=" + fee_type + ", total_fee="
				+ total_fee + ", pay_fee=" + pay_fee + ", invoice_flag=" + invoice_flag + ", parking_duration="
				+ parking_duration + ", pay_notify_url=" + pay_notify_url + ", sign=" + sign + "]";
	}
	
	
}
