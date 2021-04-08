package com.example.test;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

/**
 * @author 65667
 */
public class LoginDialog extends Dialog {

    public String username = "", password = "";

    public LoginDialog(@NonNull Context context, int dialog) {
        super(context, R.style.Dialog);
        setContentView(R.layout.dialog_login);
        this.setTitle("用户登录");
        setCancelable(true);

        final EditText et_userName = (EditText) findViewById(R.id.edit_username);
        final EditText et_password = (EditText) findViewById(R.id.edit_password);

        Button bt_OK = findViewById(R.id.btn_ok);
        Button bt_Cancel = findViewById(R.id.btn_cancel);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_ok:
                        username = et_userName.getText().toString().trim();
                        password = et_password.getText().toString().trim();
                        break;
                    case R.id.btn_cancel:
                        break;
                }
            }
        };

        bt_OK.setOnClickListener(buttonListener);
        bt_Cancel.setOnClickListener(buttonListener);

    }

}
