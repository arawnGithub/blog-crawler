package com.arawn.blog.cms.service.impl;

import com.arawn.blog.cms.dao.LinkDao;
import com.arawn.blog.cms.entity.Link;
import com.arawn.blog.cms.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 友情链接Service实现类
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

	@Resource
	private LinkDao linkDao;
	
	public List<Link> list(Map<String, Object> map) {
		return linkDao.list(map);
	}

	public Long count(Map<String, Object> map) {
		return linkDao.count(map);
	}

	public int insert(Link link) {
		return linkDao.insert(link);
	}

	public int update(Link link) {
		return linkDao.update(link);
	}

	public int delete(Integer id) {
		return linkDao.delete(id);
	}

}
