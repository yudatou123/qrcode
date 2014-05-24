/**
 * MainModule.java
 * cn.vko.web.tiku
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
 */

package com.xuexibao.qrcode;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;
import org.nutz.mvc.view.JspView;

/**
 * 主入口
 * 
 * @author 赵立伟
 * @Date 2014-1-11
 */
@IocBy(type = ComboIocProvider.class, args = {
		"*org.nutz.ioc.loader.json.JsonLoader", "config/db.js",
		"*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.xuexibao" })
// 编码方式，这个只是针对Module的方法，jsp无效，所以需要加EncodeFilter
@Encoding(input = "UTF-8", output = "UTF-8")
// module的扫描，可以直接配置包名，也可以不配置，不配置则扫描本类对应的包路径
@Modules(scanPackage = true)
// 支持本地化，本地化信息放入request的key为msg
@Localization("msg")
// 配置全局成功返回的方式
@Ok("json")
// 配置全局失败返回的方式
// 设置初始化类，可以F3进去看看
@SetupBy(VkoSetup.class)
@IocBean
public class MainModule {
	/**
	 * 获取访问域Url根，来进行登录或者进入主界面
	 */
	@At("/")
	public View index() {
		return new JspView("main");
	}
	//
	// /**
	// * 获取访问域Url根，来进行登录或者进入主界面
	// */
	// @At("/")
	// public View main() {
	// return new JspView("main");
	// }
}
