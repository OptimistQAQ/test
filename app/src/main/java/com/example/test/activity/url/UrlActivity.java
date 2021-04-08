package com.example.test.activity.url;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.test.R;

/**
 * @author 65667
 */
public class UrlActivity extends AppCompatActivity {

    private EditText editTextUrl;
    private Button btnUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        editTextUrl = findViewById(R.id.et_input_url);
        btnUrl = findViewById(R.id.btn_url);

        btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String strUrl = editTextUrl.getText().toString();
//                Uri uri = Uri.parse(strUrl);
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);

                String strTelNumber = editTextUrl.getText().toString();
                Uri data = Uri.parse("tel:" + strTelNumber);
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(data);
                startActivity(intent);
            }
        });

        requestPermissions();

    }

    //权限申请
    private void requestPermissions() {
        String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for(int i = 0;i < permissions.length;i ++) {
                    int permission = ActivityCompat.checkSelfPermission( this, permissions[i]);
                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions( this,permissions, 0x0010 );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}