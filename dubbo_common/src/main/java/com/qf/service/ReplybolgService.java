package com.qf.service;

import com.qf.entity.Replyblog;

import java.util.List;


/**
 * author：你的洪哥哥
 * date：2019-10-16 21:08
 * description:妈妈我想吃烤山药
 */
public interface ReplybolgService {

    List<Replyblog> reply(Integer blogid);

    int insertReply(Integer blogid, Integer uid, String content);

    int deleteReply(Long rid);

    int addlike(int rid);

    public int addReplyBlogLike(int id);

    Replyblog findLike(int id);
}
