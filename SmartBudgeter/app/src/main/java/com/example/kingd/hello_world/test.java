package com.example.kingd.hello_world;

/**
 * Created by KingD on 24/2/2016.
 */
public class test {
    public static void main(String args[]){
        String string = new String("123%45%78920%000");
        int cur = 0;
        int par = string.indexOf('%',6);
        System.out.println(par);
        /*cur = par + 1;
        par = string.indexOf('%',cur);
        System.out.println(par);
        cur = par + 1;
        par = string.indexOf('%',cur);
        System.out.println(par);
        cur = par + 1;
        par = string.indexOf('%',cur);
        System.out.println(par);*/
    }
}
