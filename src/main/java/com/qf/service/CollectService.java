package com.qf.service;


import com.qf.entity.Collection;

import java.util.List;

/**
 * author：你的洪哥哥
 * date：2019-10-19 16:41
 * description:妈妈我想吃烤山药
 */
public interface CollectService {
    int insertSelective(long blogid, long uid, int bookid);

    int deleteByPrimaryKey(Long cId);

    int insert(Collection record);

    int addCollection(Collection collection);

    List<Collection> showcollect(int uid);
}
