package com.qf.util;

import com.qf.vo.ResultVO;

/**
 * 2019/10/14
 * Administrator
 * 处理返回的数据格式
 */

public class ResultVOUtils<T> {
    public ResultVO success(T t){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(t);
        return resultVO;
    }
    public ResultVO error(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("失败");
        resultVO.setData(null);
        return resultVO;
    }

}
