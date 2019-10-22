package com.qf.controller;

import com.qf.entity.Bookmark;
import com.qf.service.BlogService;
import com.qf.service.BookmarkService;
import com.qf.service.CollectService;
import com.qf.util.ResultVOUtils;
import com.qf.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * author：你的洪哥哥
 * date：2019-10-19 16:40
 * description:妈妈我想吃烤山药
 */
@RestController
public class CollectController {

    @Resource
    private CollectService collectService;

    @Resource
    private BlogService blogService;

    @RequestMapping("/addcollect")
    public ResultVO addcollect(long blogid, long uid, int bookid){
        int i = collectService.insertSelective(blogid, uid, bookid);
        if (i==1) {
            int addcollect = blogService.addcollect(blogid);
            if (addcollect>0){
                return new ResultVOUtils<>().success(null);
            }
        }
        return new ResultVOUtils<>().error();
    }

    @RequestMapping("/deletecollect")
    public ResultVO deletecollect(long blogid,long cid){
        int i = collectService.deleteByPrimaryKey(cid);
        if (i==1) {
            int addcollect = blogService.deletecollect(blogid);
            if (addcollect>0){
                return new ResultVOUtils<>().success(null);
            }
        }
        return new ResultVOUtils<>().error();
    }
}
