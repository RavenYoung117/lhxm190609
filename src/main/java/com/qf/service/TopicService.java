package com.qf.service;

import com.qf.entity.Topic;

import java.util.List;

/**
 * JokerHua
 * 2019-10-08 21:54
 * 文档注释
 */

public interface TopicService {

    List<Topic> findByTitle(String topicWords);

}
