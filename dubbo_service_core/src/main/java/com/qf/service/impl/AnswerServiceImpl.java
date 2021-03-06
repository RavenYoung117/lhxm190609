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
@Service("answerServiceImpl")
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

    @Override
    public List<Answertopic> findAnswertopic(long uid) {
        return answertopicMapper.findAnswertopic(uid);
    }
    @Override
    public int addLike(int id) {
        int i = answertopicMapper.addLike(id);
        return i;
    }
    @Override
    public Answertopic findLike(int id) {
        Answertopic like = answertopicMapper.findLike(id);
        return like;
    }
}
