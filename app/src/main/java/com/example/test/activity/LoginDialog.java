package com.example.test.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.*;

import com.example.test.R;

/**
 * @author 65667
 */
public class LoginDialog extends Dialog {
	public enum ButtonID {BUTTON_NONE, BUTTON_OK, BUTTON_CANCEL, BUTTON_REGISTER};
	
	public String mUserId=null; //用户名
	public String mPsword=null; //用户密码
	public Boolean mIsHoldUserId=false; //是否记住用户名
	public ButtonID mBtnClicked=ButtonID.BUTTON_NONE;//指示哪个按钮被点击
	
	public Button mbtnLogin=null;
	public Button mbtnRegister=null;

	public LoginDialog(Context context) {
		super(context, R.style.Dialog);
		setContentView(R.layout.login);
		this.setTitle("用户登录");
		setCancelable(true);
		
		final EditText dtUserId = (EditText)findViewById(R.id.etLoginUserId);
		final EditText dtPsword = (EditText)findViewById(R.id.etLoginUserPswrod);
		final CheckBox cbIsHoldUserId = (CheckBox)findViewById(R.id.cbIsHoldId);
		mbtnLogin = (Button)findViewById(R.id.btnlogin);
		Button btnCancel = (Button)findViewById(R.id.btnCancel);
		mbtnRegister = (Button)findViewById(R.id.btnRegister);
		
		Button.OnClickListener buttonListener=new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				switch (v.getId()){
				case R.id.btnlogin:
					mUserId = dtUserId.getText().toString();
					mPsword = dtPsword.getText().toString();
					mIsHoldUserId = cbIsHoldUserId.isChecked();
					mBtnClicked = ButtonID.BUTTON_OK;
					break;
				case R.id.btnRegister:
					mBtnClicked = ButtonID.BUTTON_REGISTER;
					break;
				case R.id.btnCancel:
					mBtnClicked = ButtonID.BUTTON_CANCEL;
					break;
				}
				dismiss();
			}
		};
		mbtnLogin.setOnClickListener(buttonListener);
		btnCancel.setOnClickListener(buttonListener);
		mbtnRegister.setOnClickListener(buttonListener);
	}
}
