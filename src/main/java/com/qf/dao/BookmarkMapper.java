package com.qf.dao;

import com.qf.entity.Bookmark;

public interface BookmarkMapper {
    int deleteByPrimaryKey(Integer bId);

    int insert(Bookmark record);

    int insertSelective(Bookmark record);

    Bookmark selectByPrimaryKey(Integer bId);

    int updateByPrimaryKeySelective(Bookmark record);

    int updateByPrimaryKey(Bookmark record);
}