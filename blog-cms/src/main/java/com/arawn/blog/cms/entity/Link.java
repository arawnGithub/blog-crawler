package com.arawn.blog.cms.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 友情链接实体
 */
@Getter
@Setter
public class Link {

    /**
     * 主键ID
     */
	private Integer id;

    /**
     * 链接名称
     */
	private String name;

    /**
     * 链接URL
     */
	private String url;

    /**
     * 排序编号
     */
	private Integer sortNo;
}
