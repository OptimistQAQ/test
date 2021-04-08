package com.example.test.activity.chaundi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.test.R;

/**
 * @author 65667
 */
public class CaculateActivity extends AppCompatActivity {

    private TextView tvShowNumber;
    private Integer num1, num2;
    private Button btnQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caculate);

        tvShowNumber = findViewById(R.id.tv_show_numbers);
        btnQueren = findViewById(R.id.btn_queren);

        num1 = getIntent().getIntExtra("num1", 1);
        num2 = getIntent().getIntExtra("num2", 1);

        String show = num1.toString() + "和" + num2.toString();
        tvShowNumber.setText(show);

        btnQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}