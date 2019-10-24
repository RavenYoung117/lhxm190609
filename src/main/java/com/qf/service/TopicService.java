package com.qf.service;

import com.qf.entity.Record;
import com.qf.entity.Topic;

import java.util.List;

/**
 * JokerHua
 * 2019-10-08 21:54
 * 文档注释
 */

public interface TopicService {

    List<Topic> findByTitle(String topicWords);

    List<String> findType();

    List<Topic> findAllByType(String type);

    Topic findById(long tid);

    List<Topic> findByAnswer();

    int insertRecord(Record record);

    List<Record> findRecord(long uid);

    List<Topic> findAllByTidList(List tidList);
}
