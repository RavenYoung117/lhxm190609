package com.qf.service;

import com.qf.entity.Query;

import java.util.List;

/**
 * author：你的洪哥哥
 * date：2019-10-19 19:09
 * description:妈妈我想吃烤山药
 */
public interface QueryService {
    int insertSelective(String title, long uid);

    List<Query> selectbyuid(long uid);

}
