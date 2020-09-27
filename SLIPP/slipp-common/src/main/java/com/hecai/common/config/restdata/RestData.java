package com.hecai.common.config.restdata;


import lombok.ToString;

import java.io.Serializable;

@ToString
public class RestData<T> implements Serializable {
	private Integer code;
	private String message;
	private T data;
	
	public RestData() {}
	
	public RestData(T data) {
		this.code= Code.INFO_SUCCEED.getCode();
		this.message= Code.INFO_SUCCEED.getValue();
		this.data=data;
	}
	public RestData(Code code, String message, T data) {
		this.code=code.getCode();
		this.message=message;
		this.data=data;
	}
	public RestData(Code code, T data) {
		this.code=code.getCode();
		this.message=code.getValue();
		this.data=data;
	}
	public RestData(Code code){
		this.code= Code.INFO_SUCCEED.getCode();
		this.message= Code.INFO_SUCCEED.getValue();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

}
