package com.arawn.blog.cms.dao;

import com.arawn.blog.cms.entity.Manager;

/**
 * 管理员Dao接口
 */
public interface ManagerDao {

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
