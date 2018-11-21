package com.jt.common.exception;
/**
 * 自定义异常（继承RunTimeException或Exception）
 * 
 * @author cgb
 *
 */

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	
}
