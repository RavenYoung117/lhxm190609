package com.qf.service.impl;

import com.qf.dao.BlogMapper;
import com.qf.dao.ReplyblogMapper;
import com.qf.dao.UsersMapper;
import com.qf.entity.Blog;
import com.qf.entity.Replyblog;
import com.qf.form.BlogMoneyFrom;
import com.qf.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wzg 2019/10/9 21:35
 */
@Service("blogServiceImpl")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private ReplyblogMapper replyblogMapper;

    @Resource
    UsersMapper usersMapper;

    @Override
    public List<Blog> findAll(String title) {

        Map map = new HashMap();
        map.put("title", title);
        List<Blog> list = blogMapper.findAll(map);
        return list;
    }

    @Override
    public Blog findByIdAndState(Long bolgId) {
        Map map = new HashMap();
        map.put("bolgId", bolgId);
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


    @Override
    public List<Blog> fingblog(String title) {
        Map map = new HashMap();
        map.put("title",title);
        List<Blog> blogList = blogMapper.findblog(map);
        return blogList;
    }

    @Override
    public Blog detail(Integer blogid) {
        return blogMapper.detail(blogid);
    }

    @Override
    @Transactional
    public int addreply(int blogid) {
        int addreply = blogMapper.addreply(blogid);
        return addreply;
    }

    @Override
    @Transactional
    public int addcollect(long blogid) {
        int addcollect = blogMapper.addcollect(blogid);
        return addcollect;
    }

    @Override
    @Transactional
    public int deletereply(int blogid) {
        int deletereply = blogMapper.deletereply(blogid);
        return deletereply;
    }

    @Override
    @Transactional
    public int deletecollect(long blogid) {
        int deletecollect = blogMapper.deletecollect(blogid);
        return deletecollect;
    }
    @Override
    @Transactional
    public int AddBlogLike(int id) {
        int i = blogMapper.AddBlogLike(id);
        return i;
    }

    @Override
    @Transactional
    public int addBlogMoney(BlogMoneyFrom blogMoneyFrom) {
        //减打赏者的金额
        Map map1=new HashMap();
        map1.put("userid",blogMoneyFrom.getUserid());
        map1.put("money",blogMoneyFrom.getMoney());
        int i = usersMapper.deMoney(map1);
        if(i==0){
            return 0;
        }
        //根据文章id查出文章作者id
        Blog blog = blogMapper.findOne(blogMoneyFrom.getBlogid());
        //加作者的余额
        Map map2=new HashMap();
        map2.put("userid",blog.getuId());
        map2.put("money",blogMoneyFrom.getMoney());
        int i1 = usersMapper.addMoney(map2);
        if(i1==0){
            return 0;
        }
        return 1;
    }

    @Override
    public List<Blog> findByUid(Long uid,Long blogState) {
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("blogState",blogState);
        List<Blog> byUid = blogMapper.findByUid(map);
        return byUid;
    }

    @Override
    public Blog findLike(int id) {
        Blog like = blogMapper.findLike(id);
        return like;
    }
}
