package com.muke.testNg.Groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass1 {

    public void stu1(){
        System.out.println("GroupOnClass1运行之后的stu1111111");
    }
    public void stu2(){
        System.out.println("GroupOnClass1运行之后的stu2222222");
    }
}
