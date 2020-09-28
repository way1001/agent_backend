package com.aiforest.cloud.mall.common.constant;

/**
 * 全局返回码
 * 商城用7开头，例70001
 * @author JL
 * 2019年7月25日
 */
public enum MyReturnCode {

	ERR_70000(70000, "系统错误，请稍候再试"){},//其它错误
	ERR_70001(70001, "该状态订单不允许操作"){},
	ERR_70002(70002, "请选择付款方式"){},
	ERR_70003(70003, "没有符合下单条件的规格商品"){},
	ERR_70004(70004, "只有未支付的详单能发起支付"){},
	ERR_70005(70005, "无效订单"){},
	ERR_80001(80001, "你已领取过该电子券"){},
	ERR_80002(80002, "无此电子券"){},
	ERR_80003(80003, "电子券库存不足"){},
	ERR_80004(80004, "该商品已删除"){},
	ERR_80005(80005, "砍价ID不能为空"){},
	ERR_80006(80006, "该砍价已经购买，请勿重复购买"){},
	ERR_80010(80010, "无效的拼团活动，此拼团可能已过期或已关闭"){},
	ERR_80011(80011, "该拼团已经拼满，请重开新团"){},
	ERR_80012(80012, "你已是该团成员，请重开新团"){},
	ERR_80020(80020, "该活动已有用户参与，不能删除"){},
	;

	MyReturnCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private int code;
	private String msg;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "MyReturnCode{" + "code='" + code + '\'' + "msg='" + msg + '\'' + '}';
	}

}
