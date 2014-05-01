package com.lerryrowy.directoryview;

import android.app.Activity;
import android.os.Bundle;

import com.lerryrowy.directoryview.ui.DirectoryView;

public abstract class DirectoryActivity extends Activity{
	private DirectoryView directoryView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dir);
		directoryView = (DirectoryView) findViewById(R.id.directoryview);
	}
	
	@Override
	public void onBackPressed(){
		if(!directoryView.backToStack()){
			super.onBackPressed();
		}
	}
}
