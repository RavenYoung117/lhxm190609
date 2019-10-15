package com.qf.service.impl;

import com.qf.dao.AnswertopicMapper;
import com.qf.entity.Answertopic;
import com.qf.service.AnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * JokerHua
 * 2019-10-11 20:44
 * ******************
 * 【文档注释】:
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    @Resource
    private AnswertopicMapper answertopicMapper;
    @Override
    public List<Answertopic> findByTid(long tid) {
        return answertopicMapper.findByTid(tid);
    }

    @Override
    @Transactional
    public int insertAnswer(Answertopic answertopic) {
        return answertopicMapper.insertSelective(answertopic);
    }
}
