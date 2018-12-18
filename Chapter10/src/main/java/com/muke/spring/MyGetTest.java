package com.muke.spring;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetTest {

    //返回带cookie的get请求
    @RequestMapping(value="/getcookies",method = RequestMethod.GET)
    @ApiOperation(value = "返回带cookie的get请求",httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "这是返回带cookies的get方法";
    }

    //带cookie的get请求
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "带cookie的get请求",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "必须携带cookies";
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "这是携带cookie的get请求";
            }
        }
        return "必须携带cookies";
    }


    //带参数的get请求
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "带参数的get请求方法一",httpMethod = "GET")
    public Map<String,Integer> getList1(@RequestParam Integer start,@RequestParam Integer end){

        Map<String,Integer> myList1 = new HashMap<String, Integer>();
        myList1.put("鞋子",400);
        myList1.put("裤子",500);
        myList1.put("帽子",1400);
        return myList1;
    }

    //带参数的get请求
    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "带参数的get请求方法二",httpMethod = "GET")
    public Map<String,Integer> getList2(@PathVariable Integer start, @PathVariable Integer end){

        Map<String,Integer> myList2 = new HashMap<String, Integer>();
        myList2.put("鞋子",400);
        myList2.put("裤子",500);
        myList2.put("帽子",1400);
        return myList2;
    }
}
