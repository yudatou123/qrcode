package com.xuexibao.enumeration;

public enum EnumResult {
	
	SUCCESS("0","发送成功"),
	VALID_NOT_PASS("1","验证失败"),
	SNED_ERROR("2","发送过程中出现错误"),
	RECEIPT_ERROR("3","接收短信过程中出现错误");
	
	private EnumResult(String code,String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;
	private String desc;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}


}
