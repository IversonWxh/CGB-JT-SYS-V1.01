package com.jt.common.vo;

import java.io.Serializable;

/**
 * VO
 * 借助此对象封装控制层要返回的数据
 * @author cgb
 *
 */
public class JsonResult implements Serializable{
	private static final long serialVersionUID = 1L;
	/**响应状态的状态码*/
	private int state=1;	//ok
	/**响应消息*/
	private String message="OK";
	/**响应数据(例如查询的结果)*/
	private Object data;
	public JsonResult() {}
	public JsonResult(String message) {
		this.message=message;
	}
	/***
	 * 初始化正确的数据
	 * @param data
	 */
	public JsonResult(Object data) {
		this.data=data;
	}
	/**出现异常时可通过此方法初始化异常信息*/
	public JsonResult(Throwable e) {
		this.state=0;
		this.message=e.getMessage();
	}
	
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
}
