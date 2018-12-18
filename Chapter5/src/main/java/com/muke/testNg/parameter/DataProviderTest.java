package com.muke.testNg.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "methodData")
    public void test01(String name,int age){
        System.out.println("test01111方法的name="+name+";age"+age);
    }
    @Test(dataProvider = "methodData")
    public void test02(String name,int age){
        System.out.println("test02222方法的name="+name+";age"+age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] result = null;
        if(method.getName().equals("test01")){
            result = new Object[][]{
                    {"zhangsan",20},
                    {"lisi",30}
            };
        }else if(method.getName().equals("test02")){
            result = new Object[][]{
                    {"wangwu",50},
                    {"maliu",60}
            };
        }
        return result;
    }
}
