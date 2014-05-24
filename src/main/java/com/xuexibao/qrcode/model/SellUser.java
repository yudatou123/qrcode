package com.xuexibao.qrcode.model;

import lombok.Data;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("sell_user")
@Comment("销售人员")
@Data
public class SellUser {
	@Id
	@ColDefine(type = ColType.INT, width = 20, notNull = true)
	@Comment("主键")
	private long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	@Comment("姓名")
	private String name;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	@Comment("手机号")
	private String phoneNum;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	@Comment("地区")
	private String area;
}
