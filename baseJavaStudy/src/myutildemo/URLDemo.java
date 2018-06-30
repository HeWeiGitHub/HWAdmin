package myutildemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLDemo {
	public static void main(String[] args) throws IOException {
		String url="http://img02.tooopen.com/images/20150507/tooopen_sy_122395947985.jpg";
		UrlDownload.download(url, "urldownload.jpg", "D:\\img");
	}
}

class UrlDownload{
	
	public static void download(String urlstr,String filename,String savepath) throws IOException {
		URL url=new URL(urlstr);
//		URLConnection con=url.openConnection();
//		InputStream is=con.getInputStream();
		InputStream is=url.openStream();
		
		File file=new File(savepath);
		if (!file.exists()) {
			file.mkdirs();
			System.out.println("路径不存在，已创建！");
		}
		FileOutputStream fos=new FileOutputStream(file.getAbsolutePath()+"\\"+filename);
		
		byte[] buf=new byte[1024*10];
		int len=0;
		while ((len=is.read(buf))!=-1) {
			fos.write(buf,0,len);
		}
		fos.close();
		is.close();
		System.out.println("文件:"+filename+" 已下载到:"+savepath);
		
	}
}