/**
 * VkoSetup.java
 * cn.vko.sso
 * Copyright (c) 2012, 北京微课创景教育科技有限公司版权所有.
 */

package com.xuexibao.qrcode;

import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

/**
 * 启动时设置
 * 
 * @author 赵立伟
 * @Date 2012-5-15
 */
public class VkoSetup implements Setup {

	public void init(final NutConfig config) {
		// KvConfig commonConfig = config.getIoc()
		// .get(KvConfig.class, "webConfig");
		// InitUtil.initWebGlobalConfig(commonConfig,
		// config.getServletContext());
		// Configuration ftlConf = config.getIoc().get(Configuration.class,
		// "ftlConfig");
		// FtlUtil.initConfiguration(ftlConf, config.getServletContext(), "/",
		// "config/common/ftl.properties");
		// InitUtil.initFtlGlobalConfig(commonConfig, ftlConf);
	}

	public void destroy(final NutConfig config) {
	}
}
