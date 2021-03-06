package com.qf.service;

import com.qf.entity.Users;

import java.util.Date;

public interface UserService {
    int updateheadimg(Integer uid, String iconUrl);
    int updateuname(Integer uid, String uname);
    int updategender(Integer uid, Integer gender);
    int updatebirthday(Integer uid, Date birthday);
    int updateself(Integer uid, String self);
    int updatepassword(Integer uid, String newpass, String oldpass);

    int reward(Integer formid, Integer toid, Integer money);

    Users FindByemail(String email, String password);//邮箱

    Users FindByphone(String phone, String password);//手机号

    int addUsers(String phone, String password, String uname, String iconUrl);//注册

    int updatephone(String phone, Long uid);//修改手机号

    public int addMoney(int userid, int money);

    Users findUserByPhone(String phone);

    Users findMoney(int userid);
}

