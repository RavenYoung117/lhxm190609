package com.qf.dao;

import com.qf.entity.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Long uId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long uId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}