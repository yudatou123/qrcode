/*
 * 本配置文件声明了整个应用的数据库连接部分。
 */
var ioc = {
	ds : {
		type : "com.alibaba.druid.pool.DruidDataSource",
		events : {
			depose : 'close'
		},
		fields : {
			DriverClassName : 'net.bull.javamelody.JdbcDriver',
			url : 'jdbc:mysql://mysqldb.91xuexibao.com:3306/liveaa_education?useUnicode=true&characterEncoding=UTF-8',
			username : 'liveaa_education',
			password : 'auiDuU#H4ff',
			InitialSize : 5,
			minIdle : 5,
			maxActive : 20,
			MinEvictableIdleTimeMillis : 1800,
			TimeBetweenEvictionRunsMillis : 1801,
			TestOnBorrow : false,
			testOnReturn : false,
			poolPreparedStatements : false,
			filters : 'stat'
		}
	},
	nut : {
		type : "org.nutz.dao.impl.NutDao",
		args : [ {
			refer : "ds"
		} ]
	},
	dao : {
		type : "com.xuexibao.qrcode.dao.DbDao",
		args : [ {
			refer : "nut"
		} ]
	}
};