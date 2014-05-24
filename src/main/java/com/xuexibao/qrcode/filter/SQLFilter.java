package com.xuexibao.qrcode.filter;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuexibao.qrcode.util.Util;

/**
 * 通过Filter过滤器来防SQL注入攻击
 * 
 */
public class SQLFilter implements Filter {
	private String inj_str;
	private String url;
	protected FilterConfig filterConfig = null;
	/**
	 * Should a character encoding specified by the client be ignored?
	 */
	protected boolean ignore = true;

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
		this.inj_str = filterConfig.getInitParameter("keywords");
		this.url = filterConfig.getInitParameter("keyurl");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (!Util.isEmpty(url)) {
			String nowurl = ((HttpServletRequest) request).getRequestURL()
					.toString();
			if (nowurl.indexOf(url) > -1) {
				return;
			}
		}
		Iterator values = req.getParameterMap().values().iterator();
		while (values.hasNext()) {
			String[] value = (String[]) values.next();
			for (int i = 0; i < value.length; i++) {
				if (sql_inj(value[i])) {
					res.sendRedirect(req.getContextPath() + "/main.jsp");
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	public boolean sql_inj(String str) {
		String[] inj_stra = inj_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.indexOf(inj_stra[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {

	}
}
