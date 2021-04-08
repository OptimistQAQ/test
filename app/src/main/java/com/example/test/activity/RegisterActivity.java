package com.example.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.R;

/**
 * @author 65667
 */
public class RegisterActivity extends AppCompatActivity {

    public EditText metId, metPsword, metAffirmPsword, metPhone, metAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        metId = (EditText)findViewById(R.id.etRegisterUserId);
        metPsword = (EditText)findViewById(R.id.etRegisterUserPsword);
        metAffirmPsword = (EditText)findViewById(R.id.etRegisterUserAffirmPsword);
        metPhone = (EditText)findViewById(R.id.etRegisterUserMobilePhone);
        metAddress = (EditText)findViewById(R.id.etRegisterUserAddress);
        Button btnOK = (Button)findViewById(R.id.btnRegister);
        Button btnCancel = (Button)findViewById(R.id.btnCancel);

        Button.OnClickListener mybtnListener = new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.btnCancel:
                        finish();
                        break;
                    case R.id.btnRegister:
                        Toast.makeText(RegisterActivity.this, "点击了注册按钮!", Toast.LENGTH_LONG).show();
                    default:
                        break;
                }

            }
        };

        btnOK.setOnClickListener(mybtnListener);
        btnCancel.setOnClickListener(mybtnListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

}