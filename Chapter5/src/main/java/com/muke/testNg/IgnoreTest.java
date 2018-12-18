package com.muke.testNg;

import org.testng.annotations.Test;

public class IgnoreTest {

    @Test
    public void ignore1(){
        System.out.println("ignore1 test");
    }

    @Test(enabled = false)
    public void ignore2(){
        System.out.println("ignore2 test");
    }

    @Test(enabled = true)
    public void ignore3(){
        System.out.println("ignore3 test");
    }
}
