package com.qf.service.impl;

import com.qf.dao.BlogMapper;
import com.qf.dao.ReplyblogMapper;
import com.qf.entity.Blog;
import com.qf.entity.Replyblog;
import com.qf.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wzg 2019/10/9 21:35
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private ReplyblogMapper replyblogMapper;

    @Override
    public List<Blog> findAll(String title) {

        Map map = new HashMap();
        map.put("title", title);
        List<Blog> list = blogMapper.findAll(map);
        return list;
    }

    @Override
    public Blog findByIdAndState(Long bolgId, Long bolgstate) {
        Map map = new HashMap();
        map.put("bolgId", bolgId);
        map.put("bolgstate", bolgstate);
        Blog blog = blogMapper.findByIdAndState(map);
        return blog;
    }

    @Override
    public List<Replyblog> findList(Long blogId) {
        List<Replyblog> replyblogList = replyblogMapper.findList(blogId);
        return replyblogList;
    }


    @Override
    public int deleteByPrimaryKey(Long bolgId) {
        return 0;
    }

    @Override
    public int insert(Blog record) {
        return 0;
    }

    @Override
    @Transactional
    public int insertSelective(Blog record) {

        int count = blogMapper.insertSelective(record);

        return count;
    }

    @Override
    public Blog selectByPrimaryKey(Long bolgId) {

        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Blog record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Blog record) {
        return 0;
    }


}
