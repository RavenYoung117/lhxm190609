package com.qf.service;

import com.qf.entity.Answertopic;

import java.util.List;

/**
 * JokerHua
 * 2019-10-11 20:43
 * ******************
 * 【文档注释】:
 */

public interface AnswerService {
    List<Answertopic> findByTid(long tid);

    int insertAnswer(Answertopic answertopic);

    List<Answertopic> findAnswertopic(long uid);

    public int addLike(int id);

    Answertopic findLike(int answerId);
}
