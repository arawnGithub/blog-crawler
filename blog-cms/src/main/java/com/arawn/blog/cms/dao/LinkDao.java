package com.arawn.blog.cms.dao;

import com.arawn.blog.cms.entity.Link;

import java.util.List;
import java.util.Map;

/**
 * 友情链接Dao接口
 */
public interface LinkDao {

    /**
     * 根据条件获取友情链接集合
     * @param map
     * @return
     */
    List<Link> list(Map<String, Object> map);

    /**
     * 根据条件获取总记录数
     * @param map
     * @return
     */
    Long count(Map<String, Object> map);

    /**
     * 添加友情链接
     * @param link
     * @return
     */
    int insert(Link link);

    /**
     * 修改友情链接
     * @param link
     * @return
     */
    int update(Link link);

    /**
     * 根据ID删除友情链接
     * @param id
     * @return
     */
    int delete(Integer id);
}
