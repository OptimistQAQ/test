package com.example.test.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import android.app.Application;
import android.content.Context;
import android.net.wifi.WifiManager;

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

    //获取检查WiFi是否可用
    public boolean isWifiEnabled(Context context) {
        //获得WIFI管理对象
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        //检查WIFI连接状态
        if (wifi.getWifiState() == 3)
        {
            return true;
        }
        return false;
    }

    public String SendMessageToServer(String msg)
    {
        String revMsg = "";
        if (g_ip.equals(""))
            return "Not set Server IP";
        try {
            //创建一个Socket对象，指定服务器端的IP地址和端口号
            Socket clientsocket = new Socket(g_ip,g_objPort);
            //从Socket当中得到OutputStream
            OutputStream outputStream = clientsocket.getOutputStream();
            //发送消息
            byte writeData [] = msg.getBytes(Charset.forName("UTF-8"));
            outputStream.write(writeData, 0, writeData.length);
            outputStream.flush();
            //通过clientsocket接收服务器返回的信息
            InputStream inputStream = clientsocket.getInputStream();
            int count = 0;
            while (count == 0){
                count = inputStream.available();
            }
            byte readData [] = new byte[count];
            //从InputStream当中读取客户端所发送的数据
            inputStream.read(readData, 0, readData.length);
            revMsg = new String(readData,Charset.forName("UTF-8"));
            //关闭输入输出流及发送socket
            outputStream.close();
            inputStream.close();
            clientsocket.shutdownInput();
            clientsocket.shutdownOutput();
            clientsocket.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return revMsg;
    }
}
