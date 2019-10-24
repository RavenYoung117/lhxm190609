package com.qf.controller;

import com.qf.entity.Bookmark;
import com.qf.service.BookmarkService;
import com.qf.util.ResultVOUtils;
import com.qf.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * author：你的洪哥哥
 * date：2019-10-16 22:14
 * description:妈妈我想吃烤山药
 */
@RestController
public class BookmarkController {

    @Resource
    BookmarkService bookmarkService;

    @RequestMapping("/addbookmark")
    public ResultVO addbookmark(String bname,String ddecr,int uid){
        int addbookmark = bookmarkService.addbookmark(bname,ddecr,uid);
        if (addbookmark>0){
            return new ResultVOUtils<>().success(null);
        }
        return new ResultVOUtils<>().error();
    }

    @RequestMapping("/findbookmark")
    public ResultVO findbookmark(@RequestParam(defaultValue = "-1") int uid){
        if (uid==-1) {
            return new ResultVOUtils<>().error();
        }
        List<Bookmark> bookmarkList = bookmarkService.findbookmark(uid);
        return new ResultVOUtils<List<Bookmark>>().success(bookmarkList);
    }
}
