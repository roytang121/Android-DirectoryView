package com.lerryrowy.directoryview.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lerryrowy.directoryview.adapter.FileAdapter;
import com.lerryrowy.directoryview.model.FileRow;

public class DirectoryView extends ListView implements OnItemClickListener{
	private ArrayList<FileRow> items = new ArrayList<FileRow>();
	private Stack<File> breadcrumb = new Stack<File>();
	private FileAdapter mAdapter;
	private OnFileSelectedListener listener;
	private OnDirChangeListener dirListener;
	public DirectoryView(Context context) {
		super(context);
		init();
	}
	public DirectoryView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		init();
	}
	public DirectoryView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		mAdapter = new FileAdapter(getContext(), items);
		this.setAdapter(mAdapter);
		this.setOnItemClickListener(this);
		File root = Environment.getExternalStorageDirectory();
		updateAdapter(root);
		breadcrumb.push(root);
	}
	
	private void updateAdapter(File location){
		if(location != null && location.isDirectory()){
			File[] files = location.listFiles();
			if(files == null){
				Log.i("items", "null");
				return;
			}
			items.clear();
			for (File file : files){
				FileRow row = new FileRow(file, file.isDirectory());
				items.add(row);
			}
			mAdapter.notifyDataSetChanged();
			
			if(dirListener != null)
				dirListener.onChange(location.getName());
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		FileRow row = (FileRow) adapterView.getItemAtPosition(position);
		if(row.isdir){
			//perform go-in-to
			updateAdapter(row.file);
			breadcrumb.add(row.file);
		} else {
			//perform preview
			if(listener != null)
				listener.OnFileSelected(row.file);
		}
	}
	
	/**
	 * return the current directory, maybe null
	 * @return
	 */
	public File getCurrentDirectory(){
		try{
			File cur = breadcrumb.lastElement();
			return cur;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public void setOnFileSelectedListener(OnFileSelectedListener listener){
		this.listener = listener;
	}
	
	public boolean backToStack(){
		try{
			breadcrumb.pop();
			updateAdapter(breadcrumb.lastElement());
			return true;
		}catch (Exception e){
			return false;
		}
	}
	
	public interface OnDirChangeListener{
		public void onChange(String dir);
	}
	
	public void setOnDirChangeListener(OnDirChangeListener listener){
		this.dirListener = listener;
	}

}
