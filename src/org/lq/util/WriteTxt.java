package org.lq.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class WriteTxt {
	public static void writefile(String path,String content) throws IOException
	{
		File file = new File(path);
		if(!file.exists())
			file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        FileChannel channel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(content.getBytes().length);
        buffer.put(content.getBytes());
        buffer.flip();     //此处必须要调用buffer的flip方法
        channel.write(buffer);
        channel.close();
        outputStream.close();
	}
}
