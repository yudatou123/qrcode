package com.xuexibao.qrcode.model;

import lombok.Data;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("statistics")
@Comment("数据统计")
@Data
public class Statistics {
	@Id
	@ColDefine(type = ColType.INT, width = 20, notNull = true)
	@Comment("主键")
	private long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 200)
	@Comment("经度")
	private String longitude;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 200)
	@Comment("维度")
	private String latitude;
	@Column
	@ColDefine(type = ColType.INT, width = 20)
	@Comment("销售数目")
	private int sellCount;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 200)
	@Comment("版本号")
	private String version;
	@Column
	@ColDefine(type = ColType.INT, width = 20)
	@Comment("销售人员Id")
	private long userId;
}
