package com.qf.util;

import com.qf.vo.ResultVO;

public class ResultVoUtil<T> {
    public ResultVO success(T t,String msg){
        ResultVO resultVo = new ResultVO();
        resultVo.setCode(200);
        resultVo.setMsg(msg);
        resultVo.setData(t);
        return resultVo;
    }
    public ResultVO error(T t,String msg){
        ResultVO resultVo = new ResultVO();
        resultVo.setCode(402);
        resultVo.setMsg(msg);
        resultVo.setData(t);
        return resultVo;
    }
}
