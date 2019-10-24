package com.qf.service.impl;

import com.qf.dao.QueryMapper;
import com.qf.entity.Query;
import com.qf.service.QueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.registry.QueryManager;
import java.util.List;

/**
 * author：你的洪哥哥
 * date：2019-10-19 19:09
 * description:妈妈我想吃烤山药
 */
@Service
public class QueryServiceImpl  implements QueryService {
    @Resource
    private QueryMapper queryMapper;

    @Override
    @Transactional
    public int insertSelective(String title,long uid) {
        Query query = new Query();
        query.setRecord(title);
        query.setuId(uid);
        int i = queryMapper.insertSelective(query);
        return i;
    }

    @Override
    public List<Query> selectbyuid(long uid) {
        List<Query> queryList = queryMapper.selectbyuid(uid);
        return queryList;
    }
}
