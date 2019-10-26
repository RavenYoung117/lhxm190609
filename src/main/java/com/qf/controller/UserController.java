package com.qf.controller;

import com.qf.entity.Users;
import com.qf.service.UserService;
import com.aliyuncs.exceptions.ClientException;
import com.qf.util.IsEmail;
import com.qf.util.IsPhone;
import com.qf.util.ResultVoUtil;
import com.qf.util.SmsDysmapi;
import com.qf.vo.ResultVO;
import org.springframework.format.annotation.DateTimeFormat;
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
        if(id==null||headimg==null){
            return 0;
        }
        String realPath = request.getRealPath( "/headimg");
        String filename = id+headimg.getOriginalFilename();
        try {
            headimg.transferTo(new File(realPath+"/"+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return service.updateheadimg(id,"headimg/"+filename);
    }
    @RequestMapping("updateuname")
    public int updateuname(Integer id, String uname){
        if(id==null||uname==null){
            return 0;
        }
        return service.updateuname(id,uname);
    }
    @RequestMapping("updategender")
    public int updategender(Integer id,Integer gender){
        if(id==null||gender==null){
            return 0;
        }
        return service.updategender(id,gender);
    }
    @RequestMapping("updatebirthday")
    public int updatebirthday(Integer id,@DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday){
        if(id==null||birthday==null){
            return 0;
        }
        return service.updatebirthday(id,birthday);
    }
    @RequestMapping("updateself")
    public int updateself(Integer id,String self){
        if(id==null||self==null){
            return 0;
        }
        return service.updateself(id,self);
    }
    @RequestMapping("updatepassword")
    public int updatepassword(Integer id,String oldpass,String newpass){
        if(id==null||oldpass==null){
            return 0;
        }
        return service.updatepassword(id,newpass,oldpass);
    }
    @RequestMapping("reward")
    public int reward(Integer formid,Integer toid,Integer money){
        if(formid==null||toid==null){
            return 0;
        }
        return service.reward(formid,toid,money);
    }
    //陈**
    @RequestMapping("/regist")
    public Map regist(String phone,String code,String password,HttpSession session, HttpServletRequest request) {
        String result =(String)session.getAttribute("result");
        Map map=new HashMap();
        if(result==null){
            map.put("code", 400);
            map.put("msg", "请发送验证码");
            return map;
        }
        String uname=phone;
        //String realPath = request.getRealPath("/headimg");
        String iconUrl = "headimg/img1.jpeg";
        if(result.equals(code)){
            if (phone!=null&&IsPhone.isMobileNO(phone)&&password!=null){
                service.addUsers(phone,password,uname,iconUrl);
                map.put("code",200);
                map.put("msg","注册成功");
                return map;//跳转页面
            }else{
                //if ()
                map.put("code", 400);
                map.put("msg", "注册信息错误");
                return map;
            }
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
        if (email!=null&&IsEmail.isEmail(email)){
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
        }else if(phone!=null&&IsPhone.isMobileNO(phone)){
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
    public Map update(String phone,Long uid,String code,HttpSession session){
        Map map = new HashMap();
        String result = (String) session.getAttribute("result");
        if(result==null){
            map.put("code", 400);
            map.put("msg", "请发送验证码");
            return map;
        }
        if(result.equals(code)){
            if (phone!=null&&uid!=null){
                service.updatephone(phone,uid);
                map.put("msg","您的手机号码修改成功");
                map.put("code",200);
                map.put("data","sdsd");
                return map;
            }else {
                map.put("code", 400);
                map.put("msg", "您的手机号码修改失败");
                return map;
            }

        }else{
            map.put("code", 401);
            map.put("msg", "您输入的验证码错误");
            return map;
        }
    }
    @RequestMapping("/addMoney")
    public ResultVO addMoney(int userid, int money){
        if(userid<=0||money<=0){
            return new ResultVoUtil<>().success(null,"充值失败");
        }
        int i = service.addMoney(userid, money);
        if(i==0){
            return new ResultVoUtil<>().success(null,"充值失败");
        }
        return new ResultVoUtil<>().success(null,"充值成功");
    }

    @RequestMapping("/findMoney")
    public ResultVO findMoney(int userid){
        if(userid<=0){
            return new ResultVoUtil<>().success(null,"查询失败");
        }
        Users money = service.findMoney(userid);
        if(money==null){
            return new ResultVoUtil<>().success(null,"查询失败");
        }
        return new ResultVoUtil<>().success(money.getMoney(),"");
    }
}
