package com.qf.service.impl;

import com.qf.dao.UsersMapper;
import com.qf.entity.Users;
import com.qf.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImp implements UserService{
    @Resource
    private UsersMapper dao;
    @Override
    @Transactional
    public int updateheadimg(Integer uid, String iconUrl) {
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("iconurl",iconUrl);
        return dao.updateheadimg(map);
    }

    @Override
    @Transactional
    public int updateuname(Integer uid, String uname) {
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("uname",uname);
        return dao.updateuname(map);
    }

    @Override
    @Transactional
    public int updategender(Integer uid, Integer gender) {
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("gender",gender);
        return dao.updategender(map);
    }

    @Override
    @Transactional
    public int updatebirthday(Integer uid, Date birthday) {
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("birthday",birthday);
        return dao.updatebirthday(map);
    }

    @Override
    public int updateself(Integer uid, String self) {
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("self",self);
        return dao.updateself(map);
    }

    @Override
    @Transactional
    public int updatepassword(Integer uid, String newpass, String oldpass) {
        Map map=new HashMap();
        map.put("uid",uid);
        map.put("newpass",newpass);
        map.put("oldpass",oldpass);
        return dao.updatepassword(map);
    }

    @Override
    @Transactional
    public int reward(Integer formid, Integer toid, Integer money) {
        Map map1=new HashMap();
        Map map2=new HashMap();
        map1.put("id",formid);
        map2.put("id",toid);
        map2.put("money",money);
        map1.put("money",-money);
        Users users =dao.selectByPrimaryKey((long)formid);
        if(users.getMoney()<money){
            return -1;
        }else {
            dao.updateMoney(map1);
            dao.updateMoney(map2);
            return 1;
        }
    }

    @Override
    public Users FindByemail(String email,String password) {
        Map map = new HashMap();
        map.put("email",email);
        map.put("password",password);
        return dao.selectByemail(map);
    }

    @Override
    public Users FindByphone(String phone, String password) {
        Map map = new HashMap();
        map.put("phone",phone);
        map.put("password",password);
        return dao.selectByphone(map);
    }

    @Override
    public int addUsers(String phone, String password,String uname,String iconUrl) {
        Map map = new HashMap();
        map.put("uanme",uname);
        map.put("phone",phone);
        map.put("password",password);
        map.put("iconUrl",iconUrl);
        return dao.addUser(map);
    }

    @Override
    public int updatephone(String phone, String password) {
        Map map = new HashMap();
        map.put("phone",phone);
        map.put("password",password);
        return   dao.updateByphone(map);
    }
    @Override
    public int addMoney(int userid, int money) {
        Map map=new HashMap();
        map.put("userid",userid);
        map.put("money",money);
        int i = dao.addMoney(map);
        return i;
    }

    @Override
    public Users findUserByPhone(String phone) {
        return dao.findUserByPhone(phone);
    }

    @Override
    public Users findMoney(int userid) {
        Users money = dao.findMoney(userid);
        return money;
    }

}
