package com.qf.controller;

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
public class BlogController {

    @Resource
    private BlogService blogService;

    @RequestMapping("/addblog")
    @ResponseBody
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
    @ResponseBody
    public ResultVO<List<Blog>> findall(String title){
        List<Blog> blogList = blogService.findAll(title);
        if(blogList==null){
            return new ResultVOUtils<List<Blog>>().error();
        }
        return new ResultVOUtils<List<Blog>>().success(blogList);
    }

    @RequestMapping("/findbybid")
    @ResponseBody
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

}
