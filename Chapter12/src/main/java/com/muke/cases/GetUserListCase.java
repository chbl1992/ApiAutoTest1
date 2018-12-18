package com.muke.cases;

import com.muke.config.TestConfig;
import com.muke.model.GetUserInfoTest;
import com.muke.model.GetUserListTest;
import com.muke.model.User;
import com.muke.utils.DatabaseUtil;
import org.apache.http.HttpRequest;
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
import java.util.List;

public class GetUserListCase {

    @Test(groups = "LoginTrue",description = "测试获取用户列表的case")
    public void getUserListTest() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListTest getUserListTest = session.selectOne("getUserListTest",1);
        System.out.println(getUserListTest);
        System.out.println(TestConfig.getUserListUrl);

        JSONArray resultJson = getJsonResult(getUserListTest);

        List<User> userList = session.selectList(getUserListTest.getExpected(),getUserListTest);
        for(User u:userList){
            System.out.println("获取的user："+u.toString());
        }
        JSONArray userListJson = new JSONArray(userList);
        Assert.assertEquals(userListJson.length(),resultJson.length());
        for(int i=0;i<resultJson.length();i++){
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }
    }

    private JSONArray getJsonResult(GetUserListTest getUserListTest) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName",getUserListTest.getUserName());
        param.put("sex",getUserListTest.getSex());
        param.put("age",getUserListTest.getAge());
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        post.setHeader("Content-Type","application/json");

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        String result = EntityUtils.toString(response.getEntity(),"utf-8");

        JSONArray jsonArray = new JSONArray(result);

        System.out.println("调用接口list result："+result);

        return jsonArray;
    }
}
