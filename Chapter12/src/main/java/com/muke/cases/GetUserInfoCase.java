package com.muke.cases;

import com.muke.config.TestConfig;
import com.muke.model.GetUserInfoTest;
import com.muke.model.User;
import com.muke.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoCase {

    @Test(groups = "LoginTrue",description = "获取用户信息的接口测试")
    public void getUserInfoTest() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserInfoTest getUserInfoTest = session.selectOne("getUserInfoTest",1);
        System.out.println(getUserInfoTest.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        JSONArray resultJson = getJsonResult(getUserInfoTest);

        User user = session.selectOne(getUserInfoTest.getExpected(),getUserInfoTest);
        System.out.println("自己查库获取用户信息："+ user.toString());

        List userList = new ArrayList();
        userList.add(user);
        JSONArray jsonArray = new JSONArray(userList);
        JSONArray jsonArray1 = new JSONArray(resultJson.getString(0));
        System.out.println("获取用户信息："+ jsonArray.toString());
        System.out.println("调用接口获取用户信息" + resultJson.toString());
        Assert.assertEquals(jsonArray.toString(),jsonArray1.toString());
    }

    private JSONArray getJsonResult(GetUserInfoTest getUserInfoTest) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",getUserInfoTest.getUserId());
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        post.setHeader("Content-Type","application/json");

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        String result = EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println("调用接口result："+ result);

        List resultList = Arrays.asList(result);

        JSONArray array  = new JSONArray(resultList);

        System.out.println(array.toString());

        return array;
    }
}
