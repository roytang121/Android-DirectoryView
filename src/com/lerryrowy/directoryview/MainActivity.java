package com.lerryrowy.directoryview;

import com.lerryrowy.directoryview.ui.DirectoryView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends DirectoryActivity {

	private DirectoryView directoryView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dir);
		directoryView = (DirectoryView) findViewById(R.id.directoryview);
		directoryView.setOnDirChangeListener(new DirectoryView.OnDirChangeListener() {
			
			@Override
			public void onChange(String dir) {
				//setTitle(directoryView.getCurrentDirectory().getName());
				setTitle(dir);
			}
		});
	}
	
	@Override
	public void onBackPressed(){
		if(!directoryView.backToStack()){
			super.onBackPressed();
		}
	}
}
