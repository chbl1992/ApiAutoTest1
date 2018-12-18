package com.muke.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GetCookiesForm {

    private CookieStore store;
    private String url;
    private ResourceBundle bundle;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("base_url");
    }

    @Test
    public void cookieTest() throws IOException {
        String Cookie_url = bundle.getString("cookie_url");
        String test_url = this.url+Cookie_url;

        String result;
        HttpGet get = new HttpGet(test_url);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        this.store = client.getCookieStore();
        List<Cookie> cookie = this.store.getCookies();
        for(Cookie cookie1:cookie){
            String name = cookie1.getName();
            String value =cookie1.getValue();
            System.out.println("cookies name="+name+";cookies value="+value);
        }

    }

    @Test(dependsOnMethods = "cookieTest")
    public void testWithCookies() throws IOException {

        String getWithCookies_url = bundle.getString("getwithcookie_url");
        String real_url = this.url + getWithCookies_url;

        HttpGet get = new HttpGet(real_url);
        DefaultHttpClient client = new DefaultHttpClient();

        client.setCookieStore(this.store);

        HttpResponse response = client.execute(get);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("status = " +statusCode);

        if(statusCode==200){
            String result1 = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result1);
        }
    }
}
