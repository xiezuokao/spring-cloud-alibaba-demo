package com.hecai.gateway.config.restdata;
/**
 * 错误码枚举类
 */
public enum Code {

	INFO_SUCCEED(200,"succeed"),
	INFO_ERROR(1001,"其它错误"),
	INFO_ERROR_SQL(1000,"数据库错误"),
	INFO_ERROR_400(400,"请求参数错误"),
	INFO_ERROR_404(404,"请求地址未找到"),
	INFO_ERROR_500(500,"内部服务器错误"),
	INFO_ERROR_1002(1002,"登录过期，请重新登录！"),
	INFO_ERROR_1003(1003,"token无效！"),

	INFO_GATEWAY_2001(2001,"接口异常"),
	INFO_GATEWAY_2002(2002,"授权异常"),
	INFO_GATEWAY_2003(2003,"降级异常"),
	INFO_GATEWAY_2004(2004,"限流异常"),
	INFO_GATEWAY_2005(2005,"参数限流异常"),
	INFO_GATEWAY_2006(2006,"系统负载异常");

	
	
	
	private int code;
	private String value;
	public int getCode() {
		return code;
	}
	public String getValue() {
		return value;
	}
	
	private Code(int code,String value) {
		this.code=code;
		this.value=value;
	}
}
