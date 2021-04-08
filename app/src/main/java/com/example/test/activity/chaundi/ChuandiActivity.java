package com.example.test.activity.chaundi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test.R;

/**
 * @author 65667
 */
public class ChuandiActivity extends AppCompatActivity {

    private EditText et_one, et_two;
    private Button btn_add;
    private TextView show_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuandi);

        init();
    }

    private void init() {
        //初始化控件
        et_one = findViewById(R.id.edit_one);
        et_two = findViewById(R.id.edit_two);
        btn_add = findViewById(R.id.btn_add_two);
        show_result = findViewById(R.id.show_result);

        //设置监听事件
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的值
                int num1 = Integer.parseInt(et_one.getText().toString());
                int num2 = Integer.parseInt(et_two.getText().toString());
                int sum = num1 + num2;
                show_result.setText(String.valueOf(sum));
                Intent intent = new Intent(ChuandiActivity.this, CaculateActivity.class);
                intent.putExtra("num1", num1);
                intent.putExtra("num2", num2);
                startActivity(intent);
            }
        });

    }
}