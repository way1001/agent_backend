package com.aiforest.cloud.estate.common.constant;

/**
 * 全局返回码
 * 房产用9开头，例90001
 * @author JL
 * 2019年7月25日
 */
public enum MyReturnCode {

	ERR_90000(90000, "系统错误，请稍候再试"){},//其它错误
	ERR_90001(90001, "该状态订单不允许操作"){},
	ERR_90002(90002, "请选择需要上传图片"){},
	ERR_90003(90003, "没有符合下单条件的规格商品"){},
	ERR_90004(90004, "只有未支付的详单能发起支付"){},
	ERR_90005(90005, "无效订单"){},
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
