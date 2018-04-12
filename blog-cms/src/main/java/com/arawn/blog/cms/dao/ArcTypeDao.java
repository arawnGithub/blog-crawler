package com.arawn.blog.cms.dao;

import com.arawn.blog.cms.entity.ArcType;

import java.util.List;
import java.util.Map;

/**
 * 文章类别Dao接口
 */
public interface ArcTypeDao {

    /**
     * 根据条件获取文章类别集合
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
     * 根据ID获取文章类别
     * @param id
     * @return
     */
    ArcType getById(Integer id);

    /**
     * 根据ID删除文章类别
     * @param id
     * @return
     */
    int delete(Integer id);
}
