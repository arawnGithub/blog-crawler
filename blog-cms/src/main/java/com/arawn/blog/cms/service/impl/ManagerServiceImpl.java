package com.arawn.blog.cms.service.impl;

import com.arawn.blog.cms.dao.ManagerDao;
import com.arawn.blog.cms.entity.Manager;
import com.arawn.blog.cms.service.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员Service实现类
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	@Resource
	private ManagerDao managerDao;

	public Manager getByUsername(String username) {
		return managerDao.getByUsername(username);
	}

	public int update(Manager manager) {
		return managerDao.update(manager);
	}
}
