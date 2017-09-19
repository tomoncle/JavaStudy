package com.aric.common.utils;

import java.util.UUID;

/**
 * uuid crate
 * Created by aric on 2016/5/5.
 */
public class UUIDGenerator {

    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


//    public static void main(String[] args) {
//        System.out.println(UUID());
//    }

}
