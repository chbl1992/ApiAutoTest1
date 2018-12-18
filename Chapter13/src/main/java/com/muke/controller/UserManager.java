package com.muke.controller;

import com.muke.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j
@Api(value = "v1",description = "用户管理系统")
@RestController
@RequestMapping("v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口",httpMethod = "POST")
    public boolean login(HttpServletResponse response, @RequestBody User user){
        int i = template.selectOne("loginTest",user);
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        log.info("login");
        if(i == 1){
            log.info("login success");
            return true;
        }
        return false;
    }

    @ApiOperation(value = "添加用户的接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookie(request);
        int result = 0;
        if(x == true){
            result = template.insert("addUser",user);
            log.info("添加用户的数量为："+ result);
            return true;
        }
        return false;
    }

    @ApiOperation(value = "获取用户信息的接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookie(request);
        if(x == true){
            List<User> users = template.selectList("getUserInfo",user);
            log.info("获取用户信息成功");
            return users;
        }
        return null;
    }

    @ApiOperation(value = "更新用户信息的接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookie(request);
        int i = 0;
        if(x == true){
            i = template.update("updateUserInfo",user);
            log.info("更新用户信息成功");
            return i;
        }
        return i;
    }

    private Boolean verifyCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            log.info("Cookies为空");
            return false;
        }
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                log.info("Cookies验证通过");
                return true;
            }
        }
        return false;
    }
}
