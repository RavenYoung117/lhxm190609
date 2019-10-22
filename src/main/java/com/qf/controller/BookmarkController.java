package com.qf.controller;

import com.qf.entity.Bookmark;
import com.qf.service.BookmarkService;
import com.qf.util.ResultVOUtils;
import com.qf.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResultVO addbookmark(String bname,String ddecr){
        int addbookmark = bookmarkService.addbookmark(bname,ddecr);
        if (addbookmark>0){
            return new ResultVOUtils<>().success(null);
        }
        return new ResultVOUtils<>().error();
    }

    @RequestMapping("/findbookmark")
    public ResultVO findbookmark(){
        List<Bookmark> bookmarkList = bookmarkService.findbookmark();
        return new ResultVOUtils<List<Bookmark>>().success(bookmarkList);
    }
}
