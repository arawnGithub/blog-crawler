package com.arawn.blog.cms.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 文章类别实体
 */
@Getter
@Setter
public class ArcType {

    /**
     * 主键ID
     */
	private Integer id;

    /**
     * 类别名称
     */
	private String typeName;

    /**
     * 排序编号
     */
	private Integer sortNo;
}
