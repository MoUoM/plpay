package com.javen.util;

public class EsConstant {
	public static final int CMD_SYNC_LIMIT = 10;

	public class DomainConstant {
		public static final String MAIN = "MAIN";
		public static final String PARTNER = "PARTNER";
		public static final String CLIENT = "CLIENT";
		public static final String ADVERT = "ADVERT";
		public static final String COUPON = "COUPON";
		public static final String ADMIN = "ADMIN";
		public static final String SITE = "SITE";
	}
	
	public class CookieName {
		public static final String MAIN_USERNAME = "MAIN_USERNAME";
		public static final String PARTNER_USERNAME = "PARTNER_USERNAME";
		public static final String CLIENT_USERNAME = "CLIENT_USERNAME";
		public static final String ADVERT_USERNAME = "ADVERT_USERNAME";
		public static final String COUPON_USERNAME = "COUPON_USERNAME";
		public static final String PARTNER_TITLE = "PARTNER_TITLE";
		public static final String CLIENT_TITLE = "CLIENT_TITLE";
		public static final String ADVERT_TITLE = "ADVERT_TITLE";
		public static final String COUPON_TITLE = "COUPON_TITLE";
	}

	// 0:时长 1:金额 2:百分比
	public class CouponType {
		public static final String TIME = "0";
		public static final String FEE = "1";
		public static final String PERCENTAGE = "2";
	}
	
	//优惠券接口请求种类
	public class CouponReqType{
		public static final String QUERY = "1";				//查询
		public static final String WRITEOFF = "2";			//核销
		public static final String USED = "3";				//使用
	}

	// 0:已使用 1:未使用 2:已核销 3:已过期 4:作废
	public class CouponUsedFlag {
		public static final String HAS_USED = "0";
		public static final String NOT_USED = "1";
		public static final String WRITEOFF = "2";
		public static final String OVERDUE = "3";
		public static final String DROPPED = "4";
	}
	
	// 0:已使用 1:未使用 2:已核销 3:已过期 4:作废
	public class CouponStatusFlag {
		public static final int HAS_USED = 0;
		public static final int NOT_USED = 1;
		public static final int WRITEOFF = 2;
		public static final int OVERDUE = 3;
		public static final int DROPPED = 4;
	}

	// 0:不需同步; 1:核销同步; 2:使用同步
	public class CouponSyncFlag {
		public static final int NOT_NEED_SYNC = 0;
		public static final int NEED_SYNC = 1;
		public static final int USED_SYNC = 2;
	}

	// 0:失效 1:生效
	public class CouponGroupStatus {
		public static final String NO = "0";
		public static final String YES = "1";
		public static final String OVERDUE = "2";
	}

	public class ConnectionStatus {
		public static final int CONNECTED = 1;
		public static final int DISCONNECTED = 0;
	}

	public class TerminalMessageType {
		public static final String LOGIN = "login";
		public static final String SYNC = "sync";
		public static final String CHECKIN = "checkin";
		public static final String CHECKOUT = "checkout";
		public static final String PAYMENT = "payment";
		public static final String PAYMENT_ACK = "paymentAck";
		public static final String LOGOUT = "logout";
		public static final String ASK_PAYMENT_CMD = "ask_payment";
		public static final String PAYMENT_ACK_CMD = "payment_ack";
		public static final String PAYMENT_DEDUCTIBLE = "payment_deductible";
		public static final String PAYMENT_TICKET = "payment_ticket";
		public static final String WARING = "waring";
		public static final String ORDERPAY = "order_pay";
		public static final String UNIONPAY = "union_pay";
	}

	public class CompareSide {
		public static final String TENPAY = "tenpay";
		public static final String UNIONPAY = "unionpay";
		public static final String WEIXINPAY = "weixinpay";
		public static final String ETCPAY = "etcpay";
		public static final String BESTPAY = "bestpay";
		public static final String CLOUD = "cloud";
	}

	public class PaymentMethod {
		public static final String CASH = "cash";
		public static final String TENPAY = "tenpay";
		public static final String UNIONPAY = "unionpay";
		//银联支付
		public static final String UNIONPAYPAGE = "unionpayPage";	
		//微信支付
		public static final String WEIXINPAY = "weixinpay";		
		public static final String BESTPAY = "bestpay";		//翼支付	
		public static final String ETCPAY = "etcpay";		//ETC		
		public static final String ALIPAY = "alipay";		//支付宝
		public static final String YEEPAY = "yeepay";
		public static final String CCBISPAY = "ccbispay";		//龙支付
		public static final String QUICKPASS = "quickpass";		//银联钱包
		public static final String CCBWGPAY = "ccbwgpay";		//建行无感支付
	}
	
	//扫码方式
	public class ScanMethod{
		public static final String WEIXINSCAN = "weixinscan";
	}

	public class AuditLogType {
		public static final String PARKING_API = "Parking API";
		public static final String TENPAY_API = "Tenpay API";
	}

	// 0:平账；1:不平账
	public class BalanceFlag {
		public static final String NO = "0";
		public static final String YES = "1";
	}

	// 0:未校正；1:已校正
	public class AdjustFlag {
		public static final String NO = "0";
		public static final String YES = "1";
	}

	// 1:启用；2:停用
	public class ParkCardStatus {
		public static final String AVAILABLE = "1";
		public static final String DELETED = "2";
	}

	// 1:终端缴费通知；2:云端费用查询反馈; 3: 抵扣信息收到反馈; 4: 其他终端收到优惠券使用信息反馈
	public class PaymentType {
		public static final String TERMINAL_PAYMENT_ACK = "1";
		public static final String ASK_PAYMENT_ACK = "2";
		public static final String DISCOUNT_ACK = "3";
		public static final String TERMINAL_COUPON_USED_ACK = "4";
	}

	// 0:新创建 1:上位机已确认 2:开始支付 3:支付成功开始通知 4:通知查询订单失败 5:未查询到订单 6:金额不匹配 7:完成
	public class OrderBizStatus {
		public static final String NEW = "0";
		public static final String CONFIRM = "1";
		public static final String START_PAY = "2";
		public static final String NOTICE = "3";
		public static final String NOTICE_QUERY_FAILED = "4";
		public static final String NOTICE_ERROR_NOT_EXIST_TENPAY = "5";
		public static final String NOTICE_ERROR_TOTAL_NOT_MATCH = "6";
		public static final String FINISH = "7";
	}

	// 0:未支付 1:已支付
	public class OrderStatus {
		public static final String NOT_PAIED = "0";
		public static final String HAS_PAIED = "1";
	}

	// 0:查询费用 1:支付成功通知
	public class CommandType {
		public static final String ASK_PAYMENT = "0";
		public static final String PAYMENT_ACK = "1";
	}

	// 0:新建 1:已通知 2:未回复 3:已回复
	public class CommandStatus {
		public static final String NEW = "0";
		public static final String NOTIFIED = "1";
		public static final String NO_ACK = "2";
		public static final String ACK = "3";
	}

	// 0:未连接 1:活动
	public class TerminalStatus {
		public static final String IN_ACTIVE = "0";
		public static final String ACTIVE = "1";
	}

	// 0:formula，1:quantum
	public class BillingType {
		public static final int FORMULA = 1;
		public static final int QUANTUM = 2;
	}

	// 0:中央缴费，1:闸口缴费
	public class BillingPosition {
		public static final int CENTER = 0;
		public static final int GATE = 1;
	}

	// 0:未缴费， 1:已缴费
	public class BillingStatus {
		public static final int NOT_PAID = 0;
		public static final int HAS_PAID = 1;
	}

	// 支付宝配置设置 0:未启用， 1:启用
	public class AlipaySettingStatus {
		public static final String DISABLE = "0";
		public static final String ENABLE = "1";
	}

	// 0-未连接；1-已连接；2-已断开；3-未知
	public class TerminalPosStatus {
		public static final String NOT_CONNECTED = "0";
		public static final String HAS_CONNECTED = "1";
		public static final String DIS_CONNECTED = "2";
		public static final String UNKNOWN = "3";
	}
	
	// 优惠券用户角色(1: 超级管理员, 2: 普通管理员, 3: 财务, 4: 物业; 5: 既可以生成又可以审核)
	public class CouponUserRoleType{
		public static final String SUPERMANAGER = "1";
		public static final String MANAGER = "2";
		public static final String FINANCE = "3";
		public static final String PROPERTY = "4";
		public static final String DOUBLEROLE = "5";
	}
	
	//商户支付订单状态(0: 过期未支付; 1: 支付成功; 2: 创建待支付; 3: 支付失败
	public class MerchantOrderStatus{
		public static final String OVERDUE = "0";
		public static final String FINISH = "1";
		public static final String CREATED = "2";
		public static final String FAIL = "3";
	}
	
	//无感支付方式
	public class OrderPayMethod{
		public static final String WXPAY = "wxpay";
		public static final String ALIPAY = "alipay";
		public static final String UNIONPAY = "unionpay";
	}
	
}
