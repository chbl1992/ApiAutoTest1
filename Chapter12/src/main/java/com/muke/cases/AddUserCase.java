package com.muke.cases;

import com.muke.config.TestConfig;
import com.muke.model.AddUserTest;
import com.muke.model.User;
import com.muke.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AddUserCase {

    @Test(groups = "LoginTrue",description = "添加用户的接口测试")
    public void addUserTest() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        AddUserTest addUserTest = session.selectOne("addUserTest",1);
        System.out.println(addUserTest.toString());
        System.out.println(TestConfig.addUserUrl);

        String result = getResult(addUserTest);
        Thread.sleep(5000);

        User user = session.selectOne("addUser",addUserTest);
        System.out.println(user.toString());

        Assert.assertEquals(addUserTest.getExpected(),result);
    }

    private String getResult(AddUserTest addUserTest) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("userName",addUserTest.getUserName());
        param.put("password",addUserTest.getPassword());
        param.put("sex",addUserTest.getSex());
        param.put("age",addUserTest.getAge());
        param.put("permission",addUserTest.getPermission());
        param.put("isDelete",addUserTest.getIsDelete());

        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        post.setHeader("Content-Type","application/json");

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        String result = EntityUtils.toString(response.getEntity(),"utf-8");

        return result;
    }
}
