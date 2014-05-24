package com.xuexibao.qrcode.service;

import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.xuexibao.qrcode.dao.IDbDao;
import com.xuexibao.qrcode.model.Statistics;
import com.xuexibao.qrcode.util.Util;

@IocBean
public class ResolveStatisticsService {
	@Inject
	private IDbDao dao;

	public void resolveStatistics(Map<String, String> map) {
		String userId = (String) map.get("userId");
		String longitude = (String) map.get("longitude");
		String latitude = (String) map.get("latitude");
		String version = (String) map.get("version");
		Statistics statistics = dao.fetch(
				Statistics.class,
				Cnd.where("userId", "=", userId).and("latitude", "=", latitude)
						.and("longitude", "=", longitude)
						.and("version", "=", version));
		if (Util.isEmpty(statistics)) {
			Statistics newStatistics = new Statistics();
			newStatistics.setLatitude(latitude);
			newStatistics.setLongitude(longitude);
			newStatistics.setUserId(Long.valueOf(userId));
			newStatistics.setSellCount(1);
			newStatistics.setVersion(version);
			dao.insert(newStatistics);
		} else {
			statistics.setSellCount(statistics.getSellCount() + 1);
			dao.update(statistics, "sellCount");
		}
	}
}
