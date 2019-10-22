package com.qf.dao;

import com.qf.entity.Users;

import java.util.Map;

public interface UsersMapper {
    int deleteByPrimaryKey(Long uId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long uId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    int updateheadimg(Map map);

    int updateuname(Map map);

    int updategender(Map map);

    int updateself(Map map);

    int updatebirthday(Map map);

    int updatepassword(Map map);

    int updateMoney(Map map);

    //邮箱
    Users selectByemail(Map map);
    //电话
    Users selectByphone(Map map);
    //添加用户
    int addUser(Map map);
    //更改手机号码
    int updateByphone(Map map);

    public int addMoney(Map map);

    public int deMoney(Map map);
}