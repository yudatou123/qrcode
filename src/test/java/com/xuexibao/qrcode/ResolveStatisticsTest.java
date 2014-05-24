package com.xuexibao.qrcode;

import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.testng.annotations.Test;

import com.xuexibao.qrcode.dao.DbDao;

public class ResolveStatisticsTest {
	private Ioc ioc2;
	private Ioc ioc1;
	private DbDao dbDao;
	private NutDao nutDao;

	@Test
	public void testResolveStatistics() throws Exception {
		// ioc1 = new NutIoc(new JsonLoader("config/db.js"));
		// nutDao = ioc1.get(NutDao.class, "nut");
		// nutDao.create(Dictionary.class, true);
	}

}
