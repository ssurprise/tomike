package com.skx.tomike.enumeration;

/**
 * 押金状态的枚举
 * 
 * @author shiguotao
 * 
 */
public enum CashPledgeBizState {
	// 无押金(自己处理)
	ZERO,
	// 全部退还(自己处理)
	ALLREFUND,
	// 订单完成,开始退还押金
	BOOKORDERCOMPLETE,
	// 正常进行中
	AUTO,
	// 客服处理中
	PROCESS,
	// 房东申请押金索赔
	LANDLORDWAIT,
	// 客服已完成调解
	SERVICECONFIRM,
	// 房客已同意扣除押金
	TENANTCONFIRM,
	// 房东同意返还押金
	LANDLORDCONFIRM,
	// 房东取消扣除押金
	LANDLORDCANCEL,
	// 已拒绝房东扣除押金
	TENANTREJECT,
	// 房东申请押金索赔，房客确认超时
	TENANTTIMEOUT,
	// 房东确认超时
	LANDLORDTIMEOUT,
	// 押金申请客服介入
	TENANTSERVICE,
	// 押金申请客服介入
	LANDLORDSERVICE,
	// 速订押金确认前返回
	FIRSTPAYRETURN,
	// 入住前押金返回
	PAYCANCELRETURN,
	// 暂时不用
	TENANTCONFIRMPART, TENANTCONFIRMALL, TENANTREJECTALL, TENANTREJECTPART,
	OFFLINEPAY,
}
