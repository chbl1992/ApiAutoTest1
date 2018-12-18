package com.muke.httpclient.cookies;

import netscape.javascript.JSObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PostCookiesForm {
    private CookieStore store;
    private String url;
    private ResourceBundle bundle;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("base_url");
    }

    @Test
    public void getCookieTest() throws IOException {
        String cookie_url = bundle.getString("cookie_url");
        String test_url = this.url+cookie_url;

        String result;

        HttpGet get = new HttpGet(test_url);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        this.store = client.getCookieStore();
        List<Cookie> listcookie = store.getCookies();
        for(Cookie cookie1:listcookie){
            System.out.println("cookie name ="+cookie1.getName()+";cookie value="+cookie1.getValue());
        }
    }

    @Test(dependsOnMethods = "getCookieTest")
    public void postCookiesTest() throws IOException {
        String cookie_url = bundle.getString("postwithcookie_url");
        String test_url = this.url+cookie_url;

        DefaultHttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(test_url);

        JSONObject param = new JSONObject();
        param.put("name","huhanshan");
        param.put("age","18");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        post.setHeader("content-type","application/json");

        client.setCookieStore(this.store);

        HttpResponse response = client.execute(post);

        String result;

        result = EntityUtils.toString(response.getEntity(),"utf-8");

        JSONObject jsonresult = new JSONObject(result);

        String succ = jsonresult.getString("huhanshan");
        String sta = jsonresult.getString("status");

        Assert.assertEquals("success",succ);
        Assert.assertEquals("1",sta);



    }
}
