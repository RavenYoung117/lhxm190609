package com.qf.controller;

import com.qf.dto.BookMarkDto;
import com.qf.service.AnswerService;
import com.qf.util.ResultVoUtil;
import com.qf.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AnswertopicController {
    @Resource
    AnswerService answertopicService;
    //对评论点赞
    @RequestMapping("/addAnswerLike")
    @ResponseBody
    public ResultVO addLike(int answerId){
        int i = answertopicService.addLike(answerId);
        if(i==0){
            return new ResultVoUtil<List<BookMarkDto>>().error(null,"点赞失败");
        }
        return  new ResultVoUtil<List<BookMarkDto>>().success(null,"点赞成功");
    }

}
