package com.lerryrowy.directoryview.adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lerryrowy.directoryview.R;
import com.lerryrowy.directoryview.model.FileRow;

public class FileAdapter extends BaseAdapter{
	
	private Context context;
	private ArrayList<FileRow> list;
	private DecimalFormat df;

	public FileAdapter(Context context, ArrayList<FileRow> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public FileRow getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.listitem_file, null);
			holder.fileName = (TextView) convertView.findViewById(R.id.fileName);
			holder.fileSize = (TextView) convertView.findViewById(R.id.fileSize);
			holder.fileIcon	= (ImageView) convertView.findViewById(R.id.fileIcon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		FileRow row = getItem(position);
		if (row != null){
			if(row.isdir){
				holder.fileName.setText(row.file.getName());
				holder.fileSize.setVisibility(View.GONE);
				holder.fileIcon.setImageResource(R.drawable.ic_folder11);
			} else {
				holder.fileSize.setVisibility(View.VISIBLE);
				holder.fileName.setText(row.file.getName());
				holder.fileSize.setText(formatByte(row.file.length()));
				
				String ext = getExtension(row.file.getName());
				if(ext != null){
					if (ext.equalsIgnoreCase("pdf")) {
						holder.fileIcon.setImageResource(R.drawable.ic_pdf);
					} else if (ext.equalsIgnoreCase("doc")) {
						holder.fileIcon.setImageResource(R.drawable.ic_doc);
					} else if (ext.equalsIgnoreCase("jpeg")
							|| ext.equalsIgnoreCase("jpg")
							|| ext.equalsIgnoreCase("png")
							|| ext.equalsIgnoreCase("gif")) {
						holder.fileIcon.setImageResource(R.drawable.ic_jpeg);
					} else if (ext.equalsIgnoreCase("mp4")
							|| ext.equalsIgnoreCase("avi")
							|| ext.equalsIgnoreCase("wmv")
							|| ext.equalsIgnoreCase("3gp")
							|| ext.equalsIgnoreCase("flv")) {
						holder.fileIcon.setImageResource(R.drawable.ic_mp4);
					} else if (ext.equalsIgnoreCase("ppt")) {
						holder.fileIcon.setImageResource(R.drawable.ic_ppt);
					} else if (ext.equalsIgnoreCase("xls")) {
						holder.fileIcon.setImageResource(R.drawable.ic_xls);
					}
				}
			}
		}
		return convertView;
	}
	
	class ViewHolder{
		TextView fileName;
		TextView fileSize;
		ImageView fileIcon;
	}
	
	private String getExtension(String filename){
		String[] temp = filename.split("\\.");
		if(temp.length > 1){
			return temp[1];
		}else{
			return null;
		}
	}
	private String formatByte(long bytes){
		String result = "";
		df = new DecimalFormat("#.##");
		if(bytes < 1024*1024){
			result = df.format((double) (bytes/1024.0));
			return  result + " KB";
		} else if(bytes < 1073741824){
			result = df.format((double) (bytes / (1024.0*1024.0)));
			return  result + " MB";
		} else {
			result = df.format((double) (bytes / (1024.0*1024.0*1024.0)));
			return result + " GB";
		}
	}

}
