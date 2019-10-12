package com.qf.service.impl;

import com.qf.dao.AnswertopicMapper;
import com.qf.dao.TopicMapper;
import com.qf.entity.Topic;
import com.qf.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * JokerHua
 * 2019-10-08 21:57
 * 文档注释
 */
@Service
public class TopicServiceImpl implements TopicService{
    @Resource
    private TopicMapper topicMapper;

    @Resource
    private AnswertopicMapper answertopicMapper;

    @Override
    public List<Topic> findByTitle(String topicWords) {
        return topicMapper.findByTitle(topicWords);
    }

    @Override
    public List<String> findType() {
        return topicMapper.findType();
    }

    @Override
    public List<Topic> findAllByType(String type) {
        return topicMapper.findAllByType(type);
    }

    @Override
    public Topic findById(long tid) {
        return topicMapper.selectByPrimaryKey(tid);
    }

    @Override
    public List<Topic> findByAnswer() {
        return topicMapper.findByAnswer();
    }

    @Override
    public List<Topic> findByAnswertopic(long uid) {
        List<Long> tids= answertopicMapper.findByuId(uid);
        return topicMapper.findByTids(tids);
    }
}
