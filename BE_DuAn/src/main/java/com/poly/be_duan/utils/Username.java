//package com.poly.be_duan.utils;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//
//public class Username {
//    public Username() {
//    }
//
//    public static String getUserName(){
//        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        String[] objects = principal.split(",",0);
//        String username = objects[0].replace("org.springframework.security.core.userdetails.User","")
//                .replace("[","").replace("Username=","").trim();
//
//        System.out.println(username);
//
//        System.out.println("tai khoan login: "+username);
//
//        return username;
//    }
//}
