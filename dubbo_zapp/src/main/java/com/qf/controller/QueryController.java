package com.qf.controller;

import com.qf.entity.Query;
import com.qf.service.QueryService;
import com.qf.util.ResultVOUtils;
import com.qf.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * author：你的洪哥哥
 * date：2019-10-19 19:19
 * description:妈妈我想吃烤山药
 */
@RestController
public class QueryController {

    @Resource
    private QueryService queryService;

    @RequestMapping("/showquery")
    public ResultVO showquery(@RequestParam(defaultValue = "-1") int uid){
        if (uid==-1) {
            return null;
        }
        List<Query> queryList = queryService.selectbyuid(uid);
        if (queryList.size()==0){
            return new ResultVOUtils<List<Query>>().error();
        }
        return new ResultVOUtils<List<Query>>().success(queryList);
    }
}
