package com.qf.controller;

import com.qf.entity.Answertopic;
import com.qf.entity.Record;
import com.qf.entity.Topic;
import com.qf.exception.WxException;
import com.qf.service.AnswerService;
import com.qf.service.TopicService;
import com.qf.util.ResultVOUtils;
import com.qf.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
    public ResultVO findTopic(String topicWords){
        if (topicWords==null){
            return new ResultVOUtils().error();
        }
        List<Topic> topicList=topicService.findByTitle(topicWords);
        return new ResultVOUtils<List<Topic>>().success(topicList);
    }
    //话题分类全查:返回全部类型
    @RequestMapping("/findType")
    public ResultVO findType(){
        List<String> list=topicService.findType();
        List<Topic> topicList=topicService.findByAnswer();
        Map map=new HashMap();
        map.put("types",list);
        map.put("topics",topicList);
        return new ResultVOUtils<Map>().success(map);
    }
    //话题分类全查:根据类型返回全部话题
    @RequestMapping("/findAllByType")
    public ResultVO findAllByType(String type){
        List<Topic> list=topicService.findAllByType(type);
        return new ResultVOUtils<List<Topic>>().success(list);
    }
    //话题全查:根据话题id返回话题具体内容
    @RequestMapping("/findTopicBytid")
    public ResultVO findtopicBytid(long tid){
        Topic topic=topicService.findById(tid);
        if (topic==null){
            return new ResultVOUtils<Map>().error();
        }
        Record record = new Record();
        record.setuId(topic.getuId());
        record.settId(tid);
        record.setrTime(new Date());
        int i=topicService.insertRecord(record);
        List<Answertopic> answertopics = answerService.findByTid(tid);
        Map map=new HashMap();
        map.put("topic",topic);
        map.put("answertopics",answertopics);
        return new ResultVOUtils<Map>().success(map);
    }
    //话题评论
    @RequestMapping("/insertAnswer")
    public ResultVO insertAnswer(Answertopic answertopic, MultipartFile anImage, HttpServletRequest request){
        if (answertopic.getContent()==null){
            return new ResultVOUtils().error();
        }
        //获取uploadfile目录的真实路径
        //String realPath = request.getRealPath("/answerImage");
        String realPath = "/answerImage";
        //得到上传文件的文件名
        String filename=anImage.getOriginalFilename();
        try {
            anImage.transferTo(new File(realPath+"/"+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //以上是上传文件到服务器的代码
        answertopic.setAnswerimage(realPath+"/"+filename);
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
    public ResultVO getAnswertopics(long uid){
        List<Answertopic> list=answerService.findAnswertopic(uid);
        return new ResultVOUtils<List<Answertopic>>().success(list);
    }

    //我的：浏览记录（话题）
    @RequestMapping("/getRecord")
    public ResultVO getRecord(long uid){
        List<Record> list=topicService.findRecord(uid);
        if(list.size()==0){
            throw new WxException("查询失败,请刷新！");
        }
        List tidList=new ArrayList();
        for (Record record : list) {
            tidList.add(record.gettId());
        }
        List<Topic> topiclist=topicService.findAllByTidList(tidList);
        return new ResultVOUtils<List<Topic>>().success(topiclist);
    }
}
