package com.qf.controller;

import com.qf.dto.BookMarkDto;
import com.qf.entity.Query;
import com.qf.entity.Replyblog;
import com.qf.service.BlogService;
import com.qf.service.ReplybolgService;
import com.qf.util.ResultVOUtils;
import com.qf.util.ResultVoUtil;
import com.qf.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * author：你的洪哥哥
 * date：2019-10-16 21:07
 * description:妈妈我想吃烤山药
 */
@RestController
public class ReplybolgController {

    @Resource
    private ReplybolgService replybolgService;

    @Resource
    private BlogService blogService;

    @RequestMapping("/reply")
    public ResultVO reply(Integer blogid){
        List<Replyblog> reply = replybolgService.reply(blogid);
        return new ResultVOUtils<List<Replyblog>>().success(reply);
    }

    @RequestMapping("/addreply")
    public ResultVO  insertReply(Integer blogid, Integer uid, String content){
        int i = replybolgService.insertReply(blogid, uid, content);
        if (i==1) {
            int addreply = blogService.addreply(blogid);
            if (addreply>0){
                return new ResultVOUtils<>().success(null);
            }
        }
        return new ResultVOUtils<>().error();
    }

    @RequestMapping("/deletereply")
    public ResultVO deleteReply(Long rid,int blogid){
        int i = replybolgService.deleteReply(rid);
        if (i==1) {
            int deletereply = blogService.deletereply(blogid);
            if (deletereply>0){
                return new ResultVOUtils<>().success(null);
            }
        }
        return new ResultVOUtils<>().error();
    }

    @RequestMapping("/likereply")
    public ResultVO likereply(int rid){
        int addlike = replybolgService.addlike(rid);
        if (addlike>0){
            return new ResultVOUtils<>().success(null);
        }
        return new ResultVOUtils<>().error();
    }
    @RequestMapping("/addReplyBlogLike")
    public ResultVO addReplyBlogLike(int id){
        int i = replybolgService.addReplyBlogLike(id);
        if(i==0){
            return new ResultVoUtil<List<BookMarkDto>>().error(null,"点赞失败");
        }
        return new ResultVoUtil<List<BookMarkDto>>().success(null,"点赞成功");
    }
}
