package com.hc.utils;

/**
 * Created by Administrator on 2017/4/10.
 */
public class NotEmpty {

    public static boolean isEmpty(String string){
        if(string==null || string.trim().length()==0){
            return true;
        }else{
            return false;
        }
    }

}
