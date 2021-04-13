package com.example.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.bean.Dishes;
import com.example.test.bean.MyUser;
import com.example.test.bean.Order;
import com.example.test.bean.ShoppingCart;
import com.example.test.utils.Dish;
import com.example.test.utils.MyApplication;

import java.util.ArrayList;

/**
 * @author 65667
 */
public class OrderFoodActivity extends AppCompatActivity {

    private static final int REGISTERACTIVITY = 1; //设置注册Activity的请求码
    private static MyApplication mAppInstance; //用来访问程序全局变量

    public ImageButton mImgBtnLogin, mImgBtnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        mAppInstance = (MyApplication)getApplication(); //获得全局变量对象
        mAppInstance.g_context=getApplicationContext();
        mAppInstance.g_user = new MyUser(); //创建用户
        mAppInstance.g_orders = new ArrayList<Order>();//创建订单列表
        mAppInstance.g_dishes = new Dishes();
        mAppInstance.g_dishes.mDishes =	FillDishesList(); //填充菜品列表

        ImageButton imgBtnRest = (ImageButton)findViewById(R.id.imgBtnRest);
        ImageButton imgBtnTakeout = (ImageButton)findViewById(R.id.imgBtnTakeout);
        ImageButton imgBtnUserInfo = (ImageButton)findViewById(R.id.imgBtnUserInfo);
        ImageButton imgBtnSetting = (ImageButton)findViewById(R.id.imgBtnMyOrderes);
        mImgBtnLogin = (ImageButton)findViewById(R.id.imgBtnLogin);
        mImgBtnLogout = (ImageButton)findViewById(R.id.imgBtnLogout);

        //将各图像按钮注册到myImageButton点击事件监听器
        imgBtnRest.setOnClickListener(new myImageButtonListener());
        imgBtnTakeout.setOnClickListener(new myImageButtonListener());
        imgBtnUserInfo.setOnClickListener(new myImageButtonListener());
        imgBtnRest.setOnClickListener(new myImageButtonListener());
        mImgBtnLogin.setOnClickListener(new myImageButtonListener());
        mImgBtnLogout.setOnClickListener(new myImageButtonListener());
    }

    private ArrayList<Dish> FillDishesList() {
        ArrayList<Dish> theDishesList = new ArrayList<Dish>();
        Dish theDish = new Dish();
        //添加菜品
        theDish.mId = 1001;
        theDish.mName = "宫保鸡丁";
        theDish.mPrice = (float) 20.0;
        theDish.mImage = (R.raw.food01gongbaojiding);
        theDishesList.add(theDish);

        theDish = new Dish();
        theDish.mId = 1002;
        theDish.mName = "椒盐玉米";
        theDish.mPrice = (float) 24.0;
        theDish.mImage = (R.raw.food02jiaoyanyumi);
        theDishesList.add(theDish);

        theDish = new Dish();
        theDish.mId = 1003;
        theDish.mName = "清蒸武昌鱼";
        theDish.mPrice = (float) 48.0;
        theDish.mImage = (R.raw.food03qingzhengwuchangyu);
        theDishesList.add(theDish);

        theDish = new Dish();
        theDish.mId = 1004;
        theDish.mName = "鱼香肉丝";
        theDish.mPrice = (float) 20.0;
        theDish.mImage = (R.raw.food04yuxiangrousi);
        theDishesList.add(theDish);
        return theDishesList;
    }

    public class myImageButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.imgBtnRest:
                    if (!mAppInstance.g_user.mIslogined)
                    {
                        //用户未登录,提示用户登录
                        Toast.makeText(OrderFoodActivity.this, "请先登录!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        //用户已登录
                        //清空上次桌号
                        mAppInstance.g_user.mSeatname = "";
                        //填写桌号或包间号，给出桌号选择对话框
                        final Dialog dialog = new Dialog(OrderFoodActivity.this);
                        dialog.setContentView(R.layout.zhuoname);
                        dialog.setTitle("请输入桌号或包间号");
                        dialog.setCancelable(true);
                        Button btnOK = (Button)dialog.findViewById(R.id.btnOK);
                        final EditText etSeatId = (EditText)dialog.findViewById(R.id.etSeatId);
                        dialog.show();
                        btnOK.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v) {
                                MyApplication appInstance = (MyApplication)getApplication();
                                appInstance.g_user.mSeatname=etSeatId.getText().toString();
                                System.out.println("mAppInstance.g_seatname="+appInstance.g_user.mSeatname);
                                dialog.dismiss();
                                if (!(appInstance.g_user.mSeatname.equals(""))) {
                                    //已填写桌号或房间号，跳转到菜品清单页面
                                    Intent intent = new Intent(OrderFoodActivity.this, TabhostActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                    return;
                case R.id.imgBtnTakeout:
                    if (!mAppInstance.g_user.mIslogined)
                    {
                        //用户未登录,提示用户登录
                        Toast.makeText(OrderFoodActivity.this, "请先登录!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        //清空桌号
                        mAppInstance.g_user.mSeatname = "";
                        //用户已登录，跳转到菜品清单页面
                        Intent intent = new Intent(OrderFoodActivity.this, TabhostActivity.class);
                        startActivity(intent);
                    }
                    return;
                case R.id.imgBtnLogin://用户未登录时该按钮才会出现
                    //用户未登录，显示登录对话框让用户登录
                    final LoginDialog loginDlg = new LoginDialog(OrderFoodActivity.this);
                    loginDlg.show();
                    //对话框销毁时的响应事件
                    loginDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            switch (loginDlg.mBtnClicked) {
                                case BUTTON_OK://用户点击了“确定”按钮
                                    //判断用户名及密码是否符合
                                    MyApplication appInstance = (MyApplication)getApplication();
                                    if (appInstance.g_user.mUserid.equals(loginDlg.mUserId) &&
                                            appInstance.g_user.mPassword.equals(loginDlg.mPsword)) {
                                        //用户登录成功
                                        appInstance.g_user.mIslogined = true;
                                        //隐藏“登录”按钮，显示“注销”按钮
                                        mImgBtnLogin.setVisibility(Button.GONE);
                                        mImgBtnLogout.setVisibility(Button.VISIBLE);
                                        //创建该用户的购物车
                                        appInstance.g_cart = new ShoppingCart(appInstance.g_user.mUserid);
                                        if (loginDlg.mIsHoldUserId)	{
                                            //保存用户名
                                        }
                                        else {
                                            //清除保存的用户名
                                        }
                                        Toast.makeText(OrderFoodActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(OrderFoodActivity.this, "用户名或者密码错误", Toast.LENGTH_LONG).show();
                                    }
                                    break;
                                case BUTTON_REGISTER://用户点击了“注册”按钮
                                    //跳转到登陆页面
                                    Intent intent = new Intent(OrderFoodActivity.this, RegisterActivity.class);
                                    startActivityForResult(intent, REGISTERACTIVITY);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    return;
                case R.id.imgBtnUserInfo:
                    if (!mAppInstance.g_user.mIslogined) {
                        //用户未登录,提示用户登录
                        Toast.makeText(OrderFoodActivity.this, "请先登录!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        //用户已登录，跳转到个人信息页面
                        Intent intent = new Intent(OrderFoodActivity.this, UserInfoActivity.class);
                        startActivity(intent);
                    }
                    return;
                case R.id.imgBtnLogout: //用户登录后该按钮才会出现
                    mAppInstance.g_user.mIslogined = false;
                    //隐藏“注销”按钮，显示“登录”按钮
                    mImgBtnLogout.setVisibility(Button.GONE);
                    mImgBtnLogin.setVisibility(Button.VISIBLE);
                    return;
                default:
                    break;
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REGISTERACTIVITY:
                if (resultCode== Activity.RESULT_OK){
                    //获得RegisterActivity封装在intent中的数据
                    String userid = data.getStringExtra("user");
                    String userpsd = data.getStringExtra("password");
                    String userphone = data.getStringExtra("phone");
                    String useraddress = data.getStringExtra("address");
                    mAppInstance.g_user.mUserid = userid;
                    mAppInstance.g_user.mPassword = userpsd;
                    mAppInstance.g_user.mUserphone = userphone;
                    mAppInstance.g_user.mUseraddress = useraddress;
                }
                break;
            default:break;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_exit:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}