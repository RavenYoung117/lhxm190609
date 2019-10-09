package com.qf.service.impl;

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
    @Override
    public List<Topic> findByTitle(String topicWords) {
        return topicMapper.findByTitle(topicWords);
    }
}
