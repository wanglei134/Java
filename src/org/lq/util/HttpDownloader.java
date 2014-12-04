package org.lq.util;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.lq.ui.FrameOne;

public class HttpDownloader implements Callable<String> {
    URLConnection connection;
    FileChannel outputChann;
    public static volatile int count = 0;

    public HttpDownloader(String url, FileChannel fileChannel) throws Exception {
        synchronized (HttpDownloader.class) {
            count++;
        }
        connection = (new URL(url)).openConnection();
        this.outputChann = fileChannel;
    }

    @Override
    public String call() throws Exception {
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        ReadableByteChannel rChannel = Channels.newChannel(inputStream);
        outputChann.transferFrom(rChannel, 0, Integer.MAX_VALUE);
        // System.out.println(Thread.currentThread().getName() + " completed!");
        inputStream.close();
        outputChann.close();
        synchronized (HttpDownloader.class) {
            count--;
        }       
        return null;
    }
}