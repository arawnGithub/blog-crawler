package com.arawn.blog.cms.service;

import com.arawn.blog.cms.entity.Manager;

/**
 * 管理员Service接口
 */
public interface ManagerService {

    /**
     * 根据用户名获取管理员实体
     * @param username
     * @return
     */
	Manager getByUsername(String username);

    /**
     * 修改管理员信息
     * @param manager
     * @return
     */
	int update(Manager manager);
}
