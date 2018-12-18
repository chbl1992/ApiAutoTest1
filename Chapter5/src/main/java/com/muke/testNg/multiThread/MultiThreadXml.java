package com.muke.testNg.multiThread;

import org.testng.annotations.Test;

public class MultiThreadXml {

    @Test
    public void test01(){
        System.out.printf("test0111111111111Thread Id:%s%n",Thread.currentThread().getId());
    }

    @Test
    public void test02(){
        System.out.printf("test0222222222222Thread Id:%s%n",Thread.currentThread().getId());
    }

    @Test
    public void test03(){
        System.out.printf("test0333333333333Thread Id:%s%n",Thread.currentThread().getId());
    }
}
