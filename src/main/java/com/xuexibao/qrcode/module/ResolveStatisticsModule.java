package com.xuexibao.qrcode.module;

import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuexibao.qrcode.service.ResolveStatisticsService;
import com.xuexibao.qrcode.util.MapUtil;
import com.xuexibao.qrcode.util.Util;

@IocBean(name = "resolveStatisticsModule")
public class ResolveStatisticsModule {
	@Inject
	private ResolveStatisticsService resolveStatisticsService;
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ResolveStatisticsModule.class);

	@At("/statistics")
	@Ok("redirect:http://www.91xuexibao.com/public/xuexibao.apk")
	public void resolveStatistics(@Param("version") String version,
			@Param("longitude") String longitude,
			@Param("latitude") String latitude, @Param("userId") long userId) {
		if (Util.isEmpty(version) || Util.isEmpty(longitude)
				|| Util.isEmpty(latitude) || Util.isEmpty(userId)) {
			logger.info("generate info error");
			return;
		}
		Map<String, String> map = MapUtil.map();
		map.put("version", version);
		map.put("longitude", longitude);
		map.put("latitude", latitude);
		map.put("userId", String.valueOf(userId));
		resolveStatisticsService.resolveStatistics(map);
	}
}
