package com.xuexibao.qrcode.exception.impl;

import com.xuexibao.qrcode.exception.IParamException;

/**
 * 系统发生业务类的异常，直接显示给用户
 * <p>
 * 不需要记录日志或者提示相关人员
 * 
 * @author 彭文杰
 * @Date 2011-9-1
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException implements
		IParamException {
	public BusinessException() {
		super();
	}

	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BusinessException(final String message) {
		super(message);
	}

	public BusinessException(final Throwable cause) {
		super(cause);
	}
}
