package com.muke.testNg.Groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass3 {
    public void teacher1(){
        System.out.println("GroupOnClass3运行之后的teacher1111111");
    }
    public void teacher2(){
        System.out.println("GroupOnClass3运行之后的teacher2222222");
    }
}
