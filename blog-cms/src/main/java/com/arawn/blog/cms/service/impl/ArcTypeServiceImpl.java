package com.arawn.blog.cms.service.impl;

import com.arawn.blog.cms.dao.ArcTypeDao;
import com.arawn.blog.cms.entity.ArcType;
import com.arawn.blog.cms.service.ArcTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 文章类别Service实现类
 */
@Service("arcTypeService")
public class ArcTypeServiceImpl implements ArcTypeService {

	@Resource
	private ArcTypeDao arcTypeDao;
	
	public List<ArcType> list(Map<String, Object> map) {
		return arcTypeDao.list(map);
	}

	public Long count(Map<String, Object> map) {
		return arcTypeDao.count(map);
	}

	public int insert(ArcType arcType) {
		return arcTypeDao.insert(arcType);
	}

	public int update(ArcType arcType) {
		return arcTypeDao.update(arcType);
	}

	public int delete(Integer id) {
		return arcTypeDao.delete(id);
	}

}
