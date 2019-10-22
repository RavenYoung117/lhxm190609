package com.qf.dao;

import com.qf.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    int deleteByPrimaryKey(Long bolgId);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long bolgId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    //全查+模糊查
    List<Blog> findAll(Map map);

    Blog findByIdAndState(Map map);

    //洪
    int deletecollect(long blogid);

    int deletereply(int blogid);

    int addcollect(long blogid);

    int addreply(int blogid);

    Blog detail(Integer blogid);

    List<Blog> findblog(Map map);

    public int AddBlogLike(int id);

    public Blog findOne(int id);

}