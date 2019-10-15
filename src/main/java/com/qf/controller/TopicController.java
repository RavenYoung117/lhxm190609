package com.qf.controller;

import com.qf.entity.Answertopic;
import com.qf.entity.Topic;
import com.qf.service.AnswerService;
import com.qf.service.TopicService;
import com.qf.util.ResultVOUtils;
import com.qf.vo.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JokerHua
 * 2019-10-08 20:53
 * 文档注释
 */
@Controller
@ResponseBody
public class TopicController {
    @Resource
    private TopicService topicService;

    @Resource
    private AnswerService answerService;

    //模糊查询话题
    @RequestMapping("/findTopic")
    public ResultVo findTopic(String topicWords){
        if (topicWords==null){
            return new ResultVOUtils().error();
        }
        List<Topic> topicList=topicService.findByTitle(topicWords);
        return new ResultVOUtils<List<Topic>>().success(topicList);
    }
    //话题分类全查:返回全部类型
    @RequestMapping("/findType")
    public ResultVo findType(){
        List<String> list=topicService.findType();
        List<Topic> topicList=topicService.findByAnswer();
        Map map=new HashMap();
        map.put("types",list);
        map.put("topics",topicList);
        return new ResultVOUtils<Map>().success(map);
    }
    //话题分类全查:根据类型返回全部话题
    @RequestMapping("/findAllByType")
    public ResultVo findAllByType(String type){
        Topic topic = new Topic();
        List<Topic> list=topicService.findAllByType(type);
        return new ResultVOUtils<List<Topic>>().success(list);
    }
    //话题全查:根据话题id返回话题具体内容
    @RequestMapping("/findById")
    public ResultVo findById(long tid){
        Topic topic=topicService.findById(tid);
        List<Answertopic> answertopics = answerService.findByTid(tid);
        Map map=new HashMap();
        map.put("topic",topic);
        map.put("answertopics",answertopics);
        return new ResultVOUtils<Map>().success(map);
    }
    //话题评论
    @RequestMapping("/insertAnswer")
    public ResultVo insertAnswer(Answertopic answertopic){
        if (answertopic==null){
            return new ResultVOUtils().error();
        }
        Date date = new Date();
        answertopic.setTime(date);
        int i=answerService.insertAnswer(answertopic);
        if (i>0){
            return new ResultVOUtils().success(null);
        }
        return new ResultVOUtils().error();
    }
    //我的：我参与讨论的话题
    @RequestMapping("/getAnswertopics")
    public ResultVo getAnswertopics(long uid){
        List<Topic> list=topicService.findByAnswertopic(uid);
        return new ResultVOUtils<List<Topic>>().success(list);
    }
}
