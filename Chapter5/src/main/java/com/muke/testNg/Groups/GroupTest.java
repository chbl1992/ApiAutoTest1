package com.muke.testNg.Groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupTest {

    @Test(groups = "server")
    public void test01(){
        System.out.println("服务端测试11111111111");
    }
    @Test(groups = "server")
    public void test02(){
        System.out.println("服务端测试22222222222");
    }
    @Test(groups = "client")
    public void test03(){
        System.out.println("客户端测试11111111111");
    }
    @Test(groups = "client")
    public void test04(){
        System.out.println("客户端测试22222222222");
    }

    @BeforeGroups("server")
    public void beforeGroupTest1(){
        System.out.println("在服务端之前执行");
    }

    @AfterGroups("server")
    public void afterGroupTest2(){
        System.out.println("在服务端之后执行");
    }

    @BeforeGroups("client")
    public void beforeGroupTest3(){
        System.out.println("在客户端之前执行");
    }

    @AfterGroups("client")
    public void afterGroupTest4(){
        System.out.println("在客户端之后执行");
    }


}
