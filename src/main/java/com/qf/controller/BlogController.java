package com.qf.controller;

import com.qf.dto.BookMarkDto;
import com.qf.entity.Query;
import com.qf.form.BlogMoneyFrom;
import com.qf.service.QueryService;
import com.qf.util.ResultVoUtil;
import com.qf.vo.ResultVO;
import com.qf.entity.Blog;
import com.qf.entity.Replyblog;
import com.qf.exception.WxException;
import com.qf.service.BlogService;
import com.qf.util.ResultVOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wzg 2019/10/9 21:25
 */
@Controller
@ResponseBody
public class BlogController {

    @Resource
    private BlogService blogService;

    @RequestMapping("/addblog")

    public ResultVO save(Blog blog, MultipartFile file, HttpServletRequest request){
        //获取photos目录的真实路径
        String realPath = request.getRealPath("/blogImage");
        //得到上传文件的文件名
        String filename = file.getOriginalFilename();
        try {
            file.transferTo(new File(realPath+"/"+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog.setImageUrl(realPath+"/"+filename);
        blog.setCimage(filename);
        int count = blogService.insertSelective(blog);
        if (count>0){
          return new ResultVOUtils<Blog>().success(null);
        }

        return new ResultVOUtils<Blog>().error();
    }

    @RequestMapping("/findblog")
    public ResultVO<List<Blog>> findall(String title){
        List<Blog> blogList = blogService.findAll(title);
        if(blogList==null){
            return new ResultVOUtils<List<Blog>>().error();
        }
        return new ResultVOUtils<List<Blog>>().success(blogList);
    }

    @RequestMapping("/findbybid")
    public ResultVO findBlog(long bolgId,Long blogstate){
        if (bolgId<=0){
            throw new WxException("没有该发布信息");
        }
        if (blogstate==null){
            throw new WxException("没有该发布信息");
        }
        Blog blog = blogService.findByIdAndState(bolgId, blogstate);
        List<Replyblog> replyblogList = blogService.findList(bolgId);
        Map map = new HashMap();
        map.put("blog", blog);
        map.put("replyblogList",replyblogList);
        return new ResultVOUtils<Map>().success(map);
    }

    @Resource
    private QueryService queryService;

    @RequestMapping("/findAllblog")
    public ResultVO findblog(String title,Long uid){

        List<Blog> blogList = blogService.fingblog(title);
        List<Query> queryList = queryService.selectbyuid(uid);
        if (title!=null&&uid!=null) {
            int i=0;
            for (Query query : queryList) {
                if (title.equals(query.getRecord())) {
                    i=1;
                }
            }
            if (i==0) {
                queryService.insertSelective(title,uid);
            }
        }
        if (blogList.size()==0){
            return new ResultVOUtils<List<Blog>>().error();
        }
        return new ResultVOUtils<List<Blog>>().success(blogList);
    }

    @RequestMapping("/blogdetail")
    public ResultVO detail(Integer blogid){
        Blog blog = blogService.detail(blogid);
        return new ResultVOUtils<Blog>().success(blog);
    }

    @RequestMapping("/addBlogLike")
    public ResultVO addBlogLike(int blogid) {
        int i = blogService.AddBlogLike(blogid);
        if(i==0){
            return new ResultVoUtil<List<BookMarkDto>>().error(null,"点赞失败");
        }
        return new ResultVoUtil<List<BookMarkDto>>().success(null,"点赞成功");

    }
    @RequestMapping("/addBlogMoney")
    public ResultVO addBlogMoney(BlogMoneyFrom blogMoneyFrom) {

        /*if(i==0){
            return new ResultVoUtil<List<BookMarkDto>>().error(null,"点赞失败");
        }*/
        int i = blogService.addBlogMoney(blogMoneyFrom);
        if(i==0){
            return new ResultVoUtil<List<BookMarkDto>>().error(null,"打赏失败");
        }
        return new ResultVoUtil<List<BookMarkDto>>().success(null,"打赏成功");

    }

    @RequestMapping("findbyuid")
    @ResponseBody
    public ResultVO<List<Blog>> findUid(Long uId){
        if (uId<=0){
            throw new WxException("用户未登录");
        }
        List<Blog> blogList = blogService.findByUid(uId);

        return new ResultVOUtils<List<Blog>>().success(blogList);
    }
}
