package com.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ysnow
 *读取流到内存中
 */
public class StreamTool {

	public static byte[] read(InputStream input) throws IOException {
		ByteArrayOutputStream outstream=new ByteArrayOutputStream();
		byte[] buf=new byte[1024];
		int len=0;
		while((len=input.read(buf))!=-1){
			outstream.write(buf);
		}
		input.close();
		return outstream.toByteArray();
	}

}
