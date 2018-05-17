package com.javen.model.park;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "T_PARK")
@Entity
public class Park implements Serializable{

	private static final long serialVersionUID = -6246413323975907559L;

	@Override
	public String toString() {
		return "Park [id=" + id + ", title=" + title + ", code=" + code
				+ ", principal=" + principal + ", phone=" + phone + ", mobile="
				+ mobile + ", address=" + address + ", area=" + area
				+ ", parkingCount=" + parkingCount + ", totalCount="
				+ totalCount + ", type=" + type + ", vendor=" + vendor
				+ ", is_merchants_pay=" + is_merchants_pay + ", clientId="
				+ clientId + ", status=" + status + ", time_limit="
				+ time_limit + ", out_park_id=" + out_park_id + ", service_id="
				+ service_id + ", wx_park_id=" + wx_park_id + "]";
	}
	private long id;
	private String title;
	private String code;
	private String principal;
	private String phone;
	private String mobile;
	private String address;
	private Integer area;
	private Integer parkingCount;
	private Integer totalCount;
	private String type;
	private String vendor;
	private String is_merchants_pay;				//是否支付商家代付(1:不支付,2:支付,默认不支付1)
	private Long clientId;
	private String status;
	private Integer time_limit;
	private String out_park_id;					//第三方停车场定义停车场ID
	private String service_id; 
	private String wx_park_id;
	private String partner_secret_key;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getParkingCount() {
		return parkingCount;
	}
	public void setParkingCount(Integer parkingCount) {
		this.parkingCount = parkingCount;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getIs_merchants_pay() {
		return is_merchants_pay;
	}
	public void setIs_merchants_pay(String is_merchants_pay) {
		this.is_merchants_pay = is_merchants_pay;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(Integer time_limit) {
		this.time_limit = time_limit;
	}
	public String getOut_park_id() {
		return out_park_id;
	}
	public void setOut_park_id(String out_park_id) {
		this.out_park_id = out_park_id;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getWx_park_id() {
		return wx_park_id;
	}
	public void setWx_park_id(String wx_park_id) {
		this.wx_park_id = wx_park_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPartner_secret_key() {
		return partner_secret_key;
	}
	public void setPartner_secret_key(String partner_secret_key) {
		this.partner_secret_key = partner_secret_key;
	}
	
	
}
