package com.example.test.activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.app.TabActivity;
import android.content.Intent;

import com.example.test.R;

/**
 * @author 65667
 */
@SuppressWarnings("deprecation")
public class TabhostActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_main);
		
		TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("Caipin").
        		setIndicator("菜品").setContent(new Intent().setClass(this, CaipinActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("Order").
        		setIndicator("已点").setContent(new Intent().setClass(this, OrderedActivity.class)));		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tabhost, menu);
		return true;
	}

}
