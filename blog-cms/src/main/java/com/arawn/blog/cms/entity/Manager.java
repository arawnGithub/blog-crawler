package com.arawn.blog.cms.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员实体
 */
@Getter
@Setter
public class Manager {

    /**
     * 主键ID
     */
	private Integer id;

    /**
     * 用户名
     */
	private String username;

    /**
     * 密码
     */
	private String password;
}
