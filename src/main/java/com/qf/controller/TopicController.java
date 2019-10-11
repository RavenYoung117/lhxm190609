package com.qf.controller;

import com.qf.entity.Topic;
import com.qf.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    //模糊查询话题
    @RequestMapping("/findTopic")
    public List<Topic> findTopic(String topicWords){
        List<Topic> topicList=topicService.findByTitle(topicWords);
        return topicList;
    }
    //话题分类全查:返回全部类型
    @RequestMapping("/findType")
    public Map findType(){
        List<String> list=topicService.findType();
        List<Topic> topicList=topicService.findByAnswer();
        Map map=new HashMap();
        map.put("types",list);
        map.put("topics",topicList);
        return map;
    }
    //话题分类全查:根据类型返回全部话题
    @RequestMapping("/findAllByType")
    public List findAllByType(String type){
        Topic topic = new Topic();
        List<Topic> list=topicService.findAllByType(type);
        return list;
    }
    //话题全查:根据话题id返回话题具体内容
    @RequestMapping("/findById")
    public Topic findById(long tid){
        Topic topic=topicService.findById(tid);
        return topic;
    }
}
