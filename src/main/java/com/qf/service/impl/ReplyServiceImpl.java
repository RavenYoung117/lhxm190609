package com.qf.service.impl;

import com.qf.dao.ReplyblogMapper;
import com.qf.entity.Replyblog;
import com.qf.service.ReplybolgService;
import lombok.experimental.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * author：你的洪哥哥
 * date：2019-10-16 21:08
 * description:妈妈我想吃烤山药
 */
@Service
public class ReplyServiceImpl implements ReplybolgService {

    @Resource
    ReplyblogMapper replyblogMapper;

    @Override
    public List<Replyblog> reply(Integer blogid) {
        return replyblogMapper.reply(blogid);
    }

    @Override
    @Transactional
    public int insertReply(Integer blogid, Integer uid, String content) {
        HashMap map = new HashMap();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String rtime = simpleDateFormat.format(date);
        map.put("blogid",blogid);
        map.put("uid",uid);
        map.put("content",content);
        map.put("rtime",rtime);
        int i = replyblogMapper.insertReply(map);
        return i;
    }

    @Override
    @Transactional
    public int deleteReply(Long rid) {
        int i = replyblogMapper.deleteByPrimaryKey(rid);
        return i;
    }

    @Override
    @Transactional
    public int addlike(int rid) {
        int addlike = replyblogMapper.addlike(rid);
        return addlike;
    }
    @Override
    @Transactional
    public int addReplyBlogLike(int id) {
        int i = replyblogMapper.addReplyBlogLike(id);
        return i;
    }
}
