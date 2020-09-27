package com.hecai.gateway.config.restdata;


import java.io.Serializable;

public class RestData<T> implements Serializable {
	private int code;
	private String msg;
	private T data;
	
	public RestData() {}
	
	public RestData(Object data) {
		this.code= Code.INFO_SUCCEED.getCode();
		this.msg= Code.INFO_SUCCEED.getValue();
		this.data=(T)data;
	}
	public RestData(Code code, String msg, Object data) {
		this.code=code.getCode();
		this.msg=msg;
		this.data=(T)data;
	}
	public RestData(Code code, Object data) {
		this.code=code.getCode();
		this.msg=code.getValue();
		this.data=(T)data;
	}
	public RestData(Code code){
		this.code= Code.INFO_SUCCEED.getCode();
		this.msg= Code.INFO_SUCCEED.getValue();
	}
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
	public <t>Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = (T)data;
	}
	
}
