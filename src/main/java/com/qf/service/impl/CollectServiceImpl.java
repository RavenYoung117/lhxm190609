package com.qf.service.impl;

import com.qf.dao.CollectionMapper;
import com.qf.entity.Collection;
import com.qf.service.CollectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * author：你的洪哥哥
 * date：2019-10-19 16:41
 * description:妈妈我想吃烤山药
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectionMapper collectionMapper;

    @Override
    @Transactional
    public int insertSelective(long blogid,long uid,int bookid) {
        Collection collections = new Collection();
        Date date = new Date();
        String bookId=""+bookid;
        collections.setId(bookId);
        collections.settId(blogid);
        collections.setuId(uid);
        collections.setcTime(date);
        int i = collectionMapper.insertSelective(collections);
        return i;
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long cId) {
        int i = collectionMapper.deleteByPrimaryKey(cId);
        return i;
    }

    @Override
    @Transactional
    public int insert(Collection record) {
        int i = collectionMapper.insert(record);
        return i;
    }

    @Override
    @Transactional
    public int addCollection(Collection collection) {
        int i = collectionMapper.addCollection(collection);
        return i;
    }

}
