package com.muke.spring;

import com.muke.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post接口")
@RequestMapping("/post")
public class MyPostTest {

    private static Cookie cookie;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "返回带Cookies的POST接口",httpMethod = "post")
    public String myPostReturnCookie(HttpServletResponse response,
                                     @RequestParam(value = "userName",required = true) String userName,
                                     @RequestParam(value = "password",required = true) Integer password){
        if(userName.equals("zhangsan")&&password.equals(123456)){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜返回cookie成功";
        }
        return "返回cookie失败";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User user1){
        User user;
        Cookie[] cookies = request.getCookies();
        for (Cookie c:cookies){
            if(c.getName().equals("login")&&c.getValue().equals("true")
            &&user1.getUserName().equals("zhangsan")&&user1.getPassword().equals("123456")){
                user = new User();
                user.setName("lisi");
                user.setSex("man");
                user.setAge("16");

                return user.toString();
            }
        }
        return "请输入正确的参数";

    }
}
