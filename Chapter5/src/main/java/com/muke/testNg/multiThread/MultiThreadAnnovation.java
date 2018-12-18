package com.muke.testNg.multiThread;

import org.testng.annotations.Test;

@Test(invocationCount = 100,threadPoolSize = 10)
public class MultiThreadAnnovation {

    public void test(){
        System.out.println(1);
        System.out.printf("ThreadId = %s%n",Thread.currentThread().getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void test2(){
        System.out.println(2);
        System.out.printf("ThreadId = %s%n",Thread.currentThread().getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
