package com.arawn.blog.cms.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 文章实体
 */
@Getter
@Setter
public class Article {

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 摘要
	 */
	private String summary;

	/**
	 * 爬取时间
	 */
	private Date crawlerDate;

	/**
	 * 发布时间
	 */
	private Date releaseDate;

	/**
	 * 点击次数
	 */
	private Integer clickHit;

	/**
	 * 对应文章类别
	 */
	private ArcType arcType;

	/**
	 * 标签
	 */
	private String tags;

	/**
	 * 原始URL
	 */
	private String originalUrl;

	/**
	 * 状态
	 */
	private Integer state;
}
