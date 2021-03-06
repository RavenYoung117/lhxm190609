package com.qf.service;

import com.qf.entity.Blog;
import com.qf.entity.Replyblog;
import com.qf.form.BlogMoneyFrom;

import java.util.List;

/**
 * wzg 2019/10/9 21:35
 */
public interface BlogService {

    int deleteByPrimaryKey(Long bolgId);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long bolgId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);


    List<Blog> findAll(String title);

    Blog findByIdAndState(Long bolgId);

    //根据文章id查评论
    List<Replyblog> findList(Long blogId);


    List<Blog> fingblog(String title);

    Blog detail(Integer blogid);

    int addreply(int blogid);

    int addcollect(long blogid);

    int deletereply(int blogid);

    int deletecollect(long blogid);

    public int AddBlogLike(int id);

    public int addBlogMoney(BlogMoneyFrom blogMoneyFrom);

    List<Blog> findByUid(Long uid, Long blogState);

    Blog findLike(int blogid);
}
