package com.qf.dao;

import com.qf.entity.Bookmark;

import java.util.List;

public interface BookmarkMapper {

    List<Bookmark> findbookmark();

    int deleteByPrimaryKey(Long bId);

    int insert(Bookmark record);

    int insertSelective(Bookmark record);

    Bookmark selectByPrimaryKey(Long bId);

    int updateByPrimaryKeySelective(Bookmark record);

    int updateByPrimaryKey(Bookmark record);

    public List<Bookmark> findBookMark(int id);
    public int addBookMark(Bookmark bookMark);
}