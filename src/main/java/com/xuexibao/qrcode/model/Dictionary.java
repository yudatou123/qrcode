package com.xuexibao.qrcode.model;

import lombok.Data;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("dictionary")
@Comment("配置文件")
@Data
public class Dictionary {
	@Id
	@ColDefine(type = ColType.INT, width = 20, notNull = true)
	@Comment("主键")
	private long id;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	private String keyStr;
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 20)
	private String valueStr;
}
