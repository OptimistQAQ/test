package com.example.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.utils.Dish;
import com.example.test.utils.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 65667
 */
public class CaipinActivity extends AppCompatActivity {

    static List<Map<String, Object>> mfoodinfo;
    public ListView mlistview;
    static SimpleAdapter mlistItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caipin);

        mlistview = (ListView) findViewById(R.id.ListViewCainpin);
        //mfoodinfo = getFoodData();

        MyApplication myApplication = (MyApplication) getApplication();
        //从数据库中得到数据源
        mfoodinfo = myApplication.g_dbAdepter.getDishData();
        Log.e("123", mfoodinfo.toString());
        //构造SimpleAdapter适配器，将它和自定义的布局文件、List数据源关联
        //数据源
        //ListItem的XML实现
        mlistItemAdapter = new SimpleAdapter(this,mfoodinfo,
                R.layout.listitem,
                //动态数组与ImageItem对应的子项
                new String[] {"dishid", "image","title", "price", "order"},
                //ImageItem的XML文件里面的1个ImageView,3个TextView ID
                new int[] {R.id.dishid, R.id.img, R.id.title, R.id.price});

        mlistItemAdapter.notifyDataSetChanged();
        mlistview.setAdapter(mlistItemAdapter);

        //设置ListView选项点击监听器
        this.mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, //选项所属的ListView
                                    View arg1, //被选中的控件，即ListView中被选中的子项
                                    int arg2, //被选中子项在ListView中的位置
                                    long arg3) //被选中子项的行号
            {
                ListView templist = (ListView)arg0;
                View mView = templist.getChildAt(arg2);//选中子项(即item)在listview中的位置
                final TextView tvTitle = (TextView)mView.findViewById(R.id.title);
                //创建购买数量对话框
                final OrderOneDialog orderDlg = new OrderOneDialog(CaipinActivity.this);
                orderDlg.setTitle(tvTitle.getText().toString());
                orderDlg.show();
                //对话框销毁时的响应事件
                orderDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (orderDlg.mBtnClicked == OrderOneDialog.ButtonID.BUTTON_OK) {
                            //将菜品加入到购物车中
                            MyApplication appInstance = (MyApplication)getApplication();
                            String dishName = tvTitle.getText().toString();
                            Dish newDish = appInstance.g_dishes.GetDishbyName(dishName);
                            appInstance.g_cart.AddOneOrderItem(newDish, orderDlg.mNum);
                            Toast.makeText(CaipinActivity.this, tvTitle.getText().toString()+":"
                                    +orderDlg.mNum, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

    private ArrayList<Map<String, Object>> getFoodData() {
        ArrayList<Map<String, Object>> fooddata=new ArrayList<Map<String,Object>>();
        //将菜品信息填充进foodinfo列表
        MyApplication appInstance = (MyApplication)getApplication();
        int s = appInstance.g_dishes.GetDishQuantity(); //得到菜品数量
        for (int i=0; i<s; i++) {
            Dish theDish = appInstance.g_dishes.GetDishbyIndex(i); //得到当前菜品
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("dishid", theDish.mId);
            map.put("image", theDish.mImage);
            map.put("title", theDish.mName);
            map.put("price", theDish.mPrice);
            fooddata.add(map);
        }
        return fooddata;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.caipin, menu);
        return true;
    }


}