package com.example.test.utils;

import java.util.ArrayList;
import android.app.Application;
import android.content.Context;

import com.example.test.bean.Dishes;
import com.example.test.bean.MyUser;
import com.example.test.bean.Order;
import com.example.test.bean.ShoppingCart;

/**
 * @author 65667
 */
public class MyApplication extends Application { //该类用于保存全局变量
	public MyUser g_user; //用户
	public ShoppingCart g_cart; //与登录用户相关联的购物车
	public ArrayList<Order> g_orders; //与登录用户相关联的订单
	public Dishes g_dishes;  //菜品列表
    public String g_ip="";       //TCP通讯时店面服务器IP地址
    public String g_http_ip=""; //用HTTP通信时的店面IP地址
    public int g_communiMode = 1; //通信模式，1为TCP通信，2为HTTP通信
    public int g_objPort=35885;    //店面服务器监听端口号
    public Context g_context;
}
