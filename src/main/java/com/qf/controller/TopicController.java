package com.qf.controller;

import com.qf.entity.Topic;
import com.qf.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * JokerHua
 * 2019-10-08 20:53
 * 文档注释
 */
@Controller
public class TopicController {
    @Resource
    private TopicService topicService;

    //模糊查询话题
    @RequestMapping("/findTopic")
    public String findTopic(String topicWords){
        List<Topic> topicList=topicService.findByTitle(topicWords);
        return null;
    }
}
