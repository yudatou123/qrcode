package com.xuexibao.qrcode.module;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuexibao.qrcode.util.Util;

@IocBean
public class WeiXinModule {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@At("/downloadApk")
	public void downloadApk(HttpServletRequest request,
			HttpServletResponse response) {
		String header = request.getHeader("user-agent");
		if (Util.isEmpty(header)) {
			logger.info("header is null");
			try {
				response.sendRedirect("http://www.91xuexibao.com");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (header.indexOf("MicroMessenger") > -1) {
			try {
				request.getRequestDispatcher("/xuexibao_wx.htm").forward(
						request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.sendRedirect("http://www.91xuexibao.com/xuexibao.apk");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
