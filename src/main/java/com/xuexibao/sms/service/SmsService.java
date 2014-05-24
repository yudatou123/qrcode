package com.xuexibao.sms.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuexibao.qrcode.util.InitConst;
import com.xuexibao.qrcode.util.StringUtil;


@IocBean
public class SmsService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public List<String> getMobileNos() {
		return null;
	}

	public List<String> getMobileNos(String userName) {
		if (userName == null) {
			return getMobileNos();
		}
		return null;
	}

	public String sendSms(String mobilenos, String content, String stime) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://sdk2.zucp.net:8060/z_mdsmssend.aspx");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
		if (StringUtils.isEmpty(stime)) {
			stime = "";
		}
		NameValuePair[] data = { new NameValuePair("sn", InitConst.SMS_SN), new NameValuePair("pwd", StringUtil.makeSmsPass(InitConst.SMS_SN, InitConst.SMS_PASSWORD)),
				new NameValuePair("mobile", mobilenos), new NameValuePair("content", content), new NameValuePair("ext", ""), new NameValuePair("rrid", ""), new NameValuePair("stime", stime),

		};
		post.setRequestBody(data);

		client.executeMethod(post);
	//	String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		Integer result = post.getStatusLine().getStatusCode();
		logger.info("短信代理返回结果{}",new Object[]{String.valueOf(result)});
		return null;
	}
}
