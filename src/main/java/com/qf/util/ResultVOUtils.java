package com.qf.util;

import com.qf.vo.ResultVo;

/**
 * 2019/10/14
 * Administrator
 * 处理返回的数据格式
 */

public class ResultVOUtils<T> {
    public ResultVo success(T t){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(t);
        return resultVo;
    }
    public ResultVo  error(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(1);
        resultVo.setMsg("失败");
        resultVo.setData(null);
        return resultVo;
    }

}
