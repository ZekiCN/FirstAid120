package com.hc.utils;

public class TestJNI {

    public native int add(int a, int b);  
    
    static {  
        System.loadLibrary("CallCS");  
    }  
    public static void main(String[] args) {  
        TestJNI t = new TestJNI();  
        System.out.println(t.add(10, 20));  
    }  
}
