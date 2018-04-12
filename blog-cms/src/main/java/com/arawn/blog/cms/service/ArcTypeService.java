package com.arawn.blog.cms.service;

import com.arawn.blog.cms.entity.ArcType;

import java.util.List;
import java.util.Map;

/**
 * 文章类别Service接口
 */
public interface ArcTypeService {

	/**
	 * 根据条件获取文章类别列表
	 * @param map
	 * @return
	 */
	List<ArcType> list(Map<String, Object> map);

    /**
     * 根据条件获取总记录数
     * @param map
     * @return
     */
	Long count(Map<String, Object> map);

    /**
     * 添加文章类别
     * @param arcType
     * @return
     */
	int insert(ArcType arcType);

    /**
     * 修改文章类别
     * @param arcType
     * @return
     */
	int update(ArcType arcType);

    /**
     * 根据ID删除文章类别
     * @param id
     * @return
     */
	int delete(Integer id);
}
