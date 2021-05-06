package com.example.test.activity.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.R;

/**
 * @author 65667
 */
public class SharedPreferencesActivity extends AppCompatActivity {

    private EditText editName = null;
    private EditText editPassword = null;
    private Button btnSave = null;
    private Button btnRead = null;
    SharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        init();
    }

    private void init() {
        editName = (EditText)findViewById(R.id.editName);
        editPassword = (EditText)findViewById(R.id.editPassword);
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnRead = (Button)findViewById(R.id.buttonRead);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("456", editName.getText().toString());
                mySharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putString("username", editName.getText().toString());
                editor.putString("password", editPassword.getText().toString());
                editor.commit();
                editName.setText("");
                editPassword.setText("");
                Log.e("123", editName.getText().toString());
                Toast.makeText(SharedPreferencesActivity.this, "将用户名和密码写入Sharepreferences成功！", Toast.LENGTH_SHORT).show();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String username = mySharedPreferences.getString("username", "");
                String password = mySharedPreferences.getString("password", "");
                editName.setText(username);
                editPassword.setText(password);
                Toast.makeText(SharedPreferencesActivity.this, "读取成功！", Toast.LENGTH_SHORT).show();
            }
        });

    }

}