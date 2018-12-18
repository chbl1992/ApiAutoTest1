package com.muke.cases;

import com.muke.config.TestConfig;
import com.muke.model.UpdateUserInfoTest;
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

public class UpdateUserInfoCase {

    @Test(groups = "LoginTrue",description = "测试更新用户信息的接口测试")
    public void updateUserInfoTest() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoTest updateUserInfoTest =session.selectOne("updateUserInfoTest",1);
        System.out.println(updateUserInfoTest.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

        int result = getResult(updateUserInfoTest);

        User user = session.selectOne(updateUserInfoTest.getExpected(),updateUserInfoTest);
        System.out.println(user.toString());

        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    @Test(groups = "LoginTrue",description = "测试删除用户信息的接口测试")
    public void deleteUser() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        UpdateUserInfoTest updateUserInfoTest = session.selectOne("updateUserInfoTest",2);
        System.out.println(updateUserInfoTest);
        System.out.println(TestConfig.updateUserInfoUrl);

        int result = getResult(updateUserInfoTest);

        User user = session.selectOne(updateUserInfoTest.getExpected(),updateUserInfoTest);
        System.out.println(user.toString());

        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    private int getResult(UpdateUserInfoTest updateUserInfoTest) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",updateUserInfoTest.getUserId());
        param.put("userName",updateUserInfoTest.getUserName());
        param.put("sex",updateUserInfoTest.getSex());
        param.put("age",updateUserInfoTest.getAge());
        param.put("permission",updateUserInfoTest.getPermission());
        param.put("isDelete",updateUserInfoTest.getIsDelete());
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        post.setHeader("Content-Type","application/json");

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);

        HttpResponse response = TestConfig.defaultHttpClient.execute(post);

        String result = EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println(result);
        return Integer.parseInt(result);
    }
}
