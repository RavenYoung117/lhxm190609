package com.qf.controller;

import com.qf.entity.Answertopic;
import com.qf.service.AnswerService;
import com.qf.util.ResultVoUtil;
import com.qf.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class AnswertopicController {
    @Resource
    AnswerService answertopicService;

    //对评论点赞
    @RequestMapping("/addAnswerLike")
    @ResponseBody
    public ResultVO addLike(int answerId) {
        int i = answertopicService.addLike(answerId);
        Answertopic like = answertopicService.findLike(answerId);
        if (i == 0) {
            return new ResultVoUtil<>().error(null, "点赞失败");
        }
        return new ResultVoUtil<Long>().success(like.getLike(), "点赞成功");
    }
}
