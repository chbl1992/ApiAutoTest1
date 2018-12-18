package com.muke.testNg;

import org.testng.annotations.Test;

public class ExpectedException {

    @Test(expectedExceptions = RuntimeException.class)
    public void expectedExceptionFailed(){
        System.out.println("这是失败的测试用例");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void expectedExceptionSuccess(){
        System.out.println("这是成功的测试用例");
        throw new RuntimeException();
    }

}
