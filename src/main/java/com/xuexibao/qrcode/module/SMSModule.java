package com.xuexibao.qrcode.module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuexibao.enumeration.EnumResult;
import com.xuexibao.qrcode.util.InitConst;
import com.xuexibao.qrcode.util.SHA1;
import com.xuexibao.sms.service.SmsService;

@IocBean
public class SMSModule {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject
	SmsService smsService;

	@At("/send")
	@Ok("json")
	public String sendSMS(@Param("mobilenos") String mobilenos,
			@Param("valid") String valid, @Param("content") String content,
			HttpServletRequest request, HttpSession session) {
		logger.info("发送短信mobilenos={},valid={},content={}", new Object[] {
				mobilenos, valid, content });
		String validCode = SHA1
				.getDigestOfString((mobilenos + InitConst.VALID_KEY).getBytes());
		if (StringUtils.isEmpty(valid) || !valid.equals(validCode)) {
			return EnumResult.VALID_NOT_PASS.getCode();
		} else {
			try {
				smsService.sendSms(mobilenos, content, null);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("短信发送出现异常", e);
				return EnumResult.SNED_ERROR.getCode();
			}
		}
		logger.info("短信发送未出现异常");
		return EnumResult.SUCCESS.getCode();
	}
}
