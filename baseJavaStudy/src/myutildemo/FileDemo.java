package myutildemo;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileDemo {
	public static void main(String[] args) {
//		windows下使用\\，linux下使用/,java中两种写法都可以
//		File file=new File("D:/doc");
		File file=new File("D:\\doc");
		System.out.println(file.exists());
		file.mkdir();
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getParent());
		System.out.println(file.isDirectory());
		System.out.println(file.isFile());
		System.out.println(file.exists());
		
		File txtFile=new File("D:\\doc\\doc.txt");
		try {
			System.out.println(txtFile.createNewFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		String[] files=file.list();
		String[] fileStrs=file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".PNG");
			}
		});
		for (String f : fileStrs) {
			System.out.println(f);
		}
		System.out.println("************************************");
		/*
		File[] files=file.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".PNG");
			}
		});
		*/
		File[] files=file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".PNG");
			}
		});
		for (File f : files) {
			System.out.println(f.getName()+"--"+f.length());
		}
	}

}
