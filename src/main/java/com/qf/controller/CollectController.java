package com.qf.controller;

import com.qf.dto.BookMarkDto;
import com.qf.entity.Bookmark;
import com.qf.entity.Collection;
import com.qf.form.CollectionForm;
import com.qf.service.BlogService;
import com.qf.service.BookmarkService;
import com.qf.service.CollectService;
import com.qf.util.ResultVOUtils;
import com.qf.util.ResultVoUtil;
import com.qf.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    BookmarkService bookMarkService;
    //根据用户id查询该用户有哪些文件夹
    @RequestMapping("/chooseBookMark")
    public ResultVO<List<BookMarkDto>> addCollection(int uid){
        List<BookMarkDto> bookMark =bookMarkService.findBookMark(uid);
        return new ResultVoUtil<List<BookMarkDto>>().success(bookMark,"");
    }
    /*    返回的数据：
    [{"bId":1,"bName":"收藏夹1","bDate":1570464000000},
    {"bId":2,"bName":"收藏夹2","bDate":1571068800000},
    {"bId":3,"bName":"收藏夹3","bDate":1571155200000}]*/
//用户新建收藏夹
    @RequestMapping("addBookMark")
    public ResultVO  addBookMark(String name,String derc){
        Bookmark bookMark=new Bookmark();
        bookMark.setbName(name);
        bookMark.setbDate(new Date());
        bookMark.setdDecr(derc);
        int i = bookMarkService.addBookMark(bookMark);
        if(i==0){
            return new ResultVoUtil<List<BookMarkDto>>().error(null,"添加失败");
        }
        return new ResultVoUtil<List<BookMarkDto>>().success(null,"添加成功");
    }
    //成功返回success字符串，失败返回fail字符串

    //用户添加收藏
    @RequestMapping("addCollection")
    public ResultVO addCollection(CollectionForm collectionForm){
        Collection collection=new Collection();
        BeanUtils.copyProperties(collectionForm,collection);
        collection.setcTime(new Date());
        int i = collectService.addCollection(collection);
        if(i==0){
            return new ResultVoUtil<List<BookMarkDto>>().error(null,"收藏失败");
        }
        return new ResultVoUtil<List<BookMarkDto>>().success(null,"收藏成功");
    }
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
    @RequestMapping("/showcollect")
    public ResultVO showcollect(@RequestParam(defaultValue = "-1") int uid){
        List<Collection> collectionList = collectService.showcollect(uid);
        if (collectionList.size()!=0) {
            return new ResultVOUtils<List<Collection>>().success(collectionList);
        }else {
            return new ResultVOUtils<List<Collection>>().error();
        }
    }
}
