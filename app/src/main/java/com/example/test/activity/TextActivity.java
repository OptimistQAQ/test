package com.example.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test.R;

/**
 * @author 65667
 */
public class TextActivity extends AppCompatActivity {

    private Button btn_Red, btn_Green, btn_Blue;
    private Button btn_Add, btn_Cut;
    private Button btn_Bold, btn_Slope, btn_Origin;
    private TextView textView;
    private EditText editText;
    private static float size = 20f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        init();
    }

    private void init() {
        btn_Red = findViewById(R.id.btn_red);
        btn_Green = findViewById(R.id.btn_green);
        btn_Blue = findViewById(R.id.btn_blue);
        btn_Add = findViewById(R.id.btn_add);
        btn_Cut = findViewById(R.id.btn_cut);
        btn_Bold = findViewById(R.id.btn_bold);
        textView = findViewById(R.id.text_view_1);
        editText = findViewById(R.id.edit_text);

        btn_Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.RED);
            }
        });
        btn_Green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.GREEN);
            }
        });
        btn_Blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.BLUE);
            }
        });
        btn_Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.RED);
            }
        });
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size++;
                textView.setTextSize(size);
            }
        });
        btn_Cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size--;
                textView.setTextSize(size);
            }
        });
        btn_Bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTypeface(Typeface.DEFAULT_BOLD);
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText().toString());
            }
        });
    }

}