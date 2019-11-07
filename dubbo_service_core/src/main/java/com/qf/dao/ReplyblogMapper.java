package com.qf.dao;

import com.qf.entity.Replyblog;

import java.util.List;
import java.util.Map;

public interface ReplyblogMapper {
    int deleteByPrimaryKey(Long rId);

    int insert(Replyblog record);

    int insertSelective(Replyblog record);

    Replyblog selectByPrimaryKey(Long rId);

    int updateByPrimaryKeySelective(Replyblog record);

    int updateByPrimaryKey(Replyblog record);

    //根据文章id查评论
    List<Replyblog> findList(Long blogId);

    int addlike(int rid);

    int insertReply(Map map);

    List<Replyblog> reply(Integer blogid);

    public int addReplyBlogLike(int id);

    Replyblog findLike(int id);
}