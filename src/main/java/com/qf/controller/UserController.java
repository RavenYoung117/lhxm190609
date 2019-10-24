package com.qf.controller;

import com.qf.entity.Users;
import com.qf.service.UserService;
import com.aliyuncs.exceptions.ClientException;
import com.qf.util.IsPhone;
import com.qf.util.ResultVoUtil;
import com.qf.util.SmsDysmapi;
import com.qf.vo.ResultVO;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService service;
    @RequestMapping("updateheadimg")
    public int upheadimg(Integer id, MultipartFile headimg, HttpServletRequest request){
        String realPath = request.getRealPath( "/headimg");
        String filename = id+headimg.getOriginalFilename();
        try {
            headimg.transferTo(new File(realPath+"/"+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return service.updateheadimg(id,realPath+"/"+filename);
    }
    @RequestMapping("updateuname")
    public int upheadimg(Integer id, String uname, ModelMap map){
        return service.updateuname(id,uname);
    }
    @RequestMapping("updategender")
    public int upheadimg(Integer id,Integer gender){
        return service.updategender(id,gender);
    }
    @RequestMapping("updatebirthday")
    public int upheadimg(Integer id,Date birthday){
        return service.updatebirthday(id,birthday);
    }
    @RequestMapping("updateself")
    public int upheadimg(Integer id,String self){
        return service.updateself(id,self);
    }
    @RequestMapping("updatepassword")
    public int upheadimg(Integer id,String oldpass,String newpass){
        return service.updatepassword(id,newpass,oldpass);
    }
    @RequestMapping("reward")
    public int reward(Integer formid,Integer toid,Integer money){
        return service.reward(formid,toid,money);
    }
    //陈**
    @RequestMapping("/regist")
    public Map regist(String phone,String code,String password,HttpSession session, HttpServletRequest request) {
        String result =(String)session.getAttribute("result");

        String uname=phone;
        String realPath = request.getRealPath("/headimg");
        String iconUrl = "headimg"+"/img1.jpeg";
        Map map=new HashMap();
        if (phone!=null&&result.equals(code)){
            service.addUsers(phone,password,uname,iconUrl);
            map.put("code",200);
            map.put("msg","验证成功");
            return map;//跳转页面
        }else{
            //if ()
            map.put("code", 400);
            map.put("msg", "验证码错误");
            return map;
        }
    }

    //提供一个用户短信校验的方法
    @RequestMapping("/sendMessage")
    public Map messageVerify (String phone,HttpSession session) throws ClientException{
        Map map = new HashMap();
        if(phone ==null){
            map.put("msg","电话号码为空！");
            map.put("code", 500);
            return map;
        }else if(!IsPhone.isMobileNO(phone)){
            map.put("msg","电话号码格式不正确！");
            map.put("code", 500);
            return map;
        }

        Users users=service.findUserByPhone(phone);
        if (users!=null){
            map.put("msg","电话号码已被注册！");
            map.put("code", 500);
            return map;
        }
        //随机产生六位数字字符串的验证码
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        session.setAttribute("result",result);
        SmsDysmapi.sendShortMessage("SMS_175495166",phone,result);
        //这些都是阿里云API替我们发送信息成功后返回的数据
        map.put("msg","已发送验证码！");
        map.put("code", 200);
        //System.out.println(result);
        return map;
    }
    @RequestMapping("/login")
    public Map login(String email, String phone, String password, HttpSession session){
        Map map = new HashMap();
        if (email!=null&&email.contains("@")){
            Users users1=service.FindByemail(email,password);
            if (users1!=null){
                session.setAttribute("user1",users1);
                map.put("code", 200);
                map.put("massage", "登陆成功");
                map.put("data",users1);
                session.setAttribute("user",users1);
                return  map;//跳转主页
            } else {
                map.put("code", 500);
                map.put("massage", "密码错误");
            }
        }else{
            Users users2 =  service.FindByphone(phone,password);
            if (users2!=null){
                session.setAttribute("user2",users2);
                map.put("code", 200);
                map.put("massage", "登陆成功");
                map.put("data",users2);
                session.setAttribute("user",users2);
                return  map;//跳转至主页
            } else {
                map.put("code", 500);
                map.put("massage", "密码错误");
            }

        }
        return  map;//跳转错误界面
    }
    @RequestMapping("/logout")
    public Map logout(SessionStatus status){
        int k=0;
        status.setComplete();
        k=1;
        Map map = new HashMap();
        if (k==1){
            map.put("code", 200);
            map.put("massage", "成功");
            map.put("url", "/user/login");
        }else{
            map.put("code", 500);
            map.put("massage", "失败");
            map.put("url", "/user/login");
        }
        //1把用户信息从session中移除
        /*HttpSession session = request.getSession();
        session.removeAttribute("user");
        //2把session失效
        session.invalidate();
        //3把cookie删除
        Cookie cookie = new Cookie("userinfo","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);*/
        return map;
    }
    @RequestMapping("/update")
    public Map update(String phone,String password,String code,HttpSession session){
        Map map = new HashMap();
        String result = (String) session.getAttribute("result");
        if(result.equals(code)){
            if (phone!=null){
                service.updatephone(phone,password);
                map.put("msg","您的手机号码修改成功");
                map.put("code",200);
                map.put("data","sdsd");
            }else {
                map.put("code", 400);
                map.put("msg", "您的手机号码修改失败");
            }

        }else{
            map.put("code", 401);
            map.put("msg", "您输入的验证码错误");
        }
        return map;
    }
    @RequestMapping("/addMoney")
    public ResultVO addMoney(int userid, int money){
        int i = service.addMoney(userid, money);
        if(i==0){
            return new ResultVoUtil<>().success(null,"充值失败");
        }
        return new ResultVoUtil<>().success(null,"充值成功");
    }

    @RequestMapping("/findMoney")
    public ResultVO findMoney(int userid){
        Users money = service.findMoney(userid);
        return new ResultVoUtil<>().success(money.getMoney(),"");
    }
}
