package com.lerryrowy.directoryview.model;

import java.io.File;

public class FileRow {
	public File file;
	public boolean isdir;
	
	public FileRow(File file, boolean isDir){
		this.file = file;
		this.isdir = isDir;
	}
}
