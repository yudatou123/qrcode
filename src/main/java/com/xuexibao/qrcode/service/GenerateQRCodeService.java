package com.xuexibao.qrcode.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.xuexibao.qrcode.dao.IDbDao;
import com.xuexibao.qrcode.model.Dictionary;
import com.xuexibao.qrcode.model.SellUser;
import com.xuexibao.qrcode.util.MapUtil;
import com.xuexibao.qrcode.util.Util;

@IocBean
public class GenerateQRCodeService {
	@Inject
	private IDbDao dao;

	public Map<String, String> dealInfo(Map<String, String> map) {

		SellUser sellUser = new SellUser();
		sellUser.setName(map.get("name"));
		sellUser.setPhoneNum(map.get("phoneNum"));
		sellUser.setArea(map.get("area"));
		SellUser newSellUser = dao.insert(sellUser);
		List<Dictionary> urlConfig = dao.query(Dictionary.class,
				Cnd.where("keyStr", "=", "url"), null);
		List<Dictionary> versionConfig = dao.query(Dictionary.class,
				Cnd.where("keyStr", "=", "version"), null);
		List<Dictionary> longitudeConfig = dao.query(Dictionary.class,
				Cnd.where("keyStr", "=", "longitude"), null);
		List<Dictionary> latitudeConfig = dao.query(Dictionary.class,
				Cnd.where("keyStr", "=", "latitude"), null);
		StringBuilder sb = new StringBuilder();
		Dictionary configuration = urlConfig.get(0);
		if (!Util.isEmpty(configuration)) {
			sb.append(configuration.getValueStr());
		}
		sb.append("version=" + versionConfig.get(0).getValueStr());
		sb.append("&longitude=" + longitudeConfig.get(0).getValueStr());
		sb.append("&latitude=" + latitudeConfig.get(0).getValueStr());
		sb.append("&userId=" + newSellUser.getId());
		String content = sb.toString();
		Map<String, String> returnMap = MapUtil.map();
		returnMap.put("url", content);
		returnMap.put("userId", String.valueOf(newSellUser.getId()));
		return returnMap;
	}

	public Map<String, String> getImagePath() {
		List<Dictionary> srcImgConfig = dao.query(Dictionary.class,
				Cnd.where("keyStr", "=", "srcImg"), null);
		List<Dictionary> desImgConfig = dao.query(Dictionary.class,
				Cnd.where("keyStr", "=", "desImg"), null);
		String des = desImgConfig.get(0).getValueStr();
		String src = srcImgConfig.get(0).getValueStr();
		Map<String, String> returnMap = MapUtil.map();
		returnMap.put("des", des);
		returnMap.put("src", src);
		return returnMap;
	}
}
