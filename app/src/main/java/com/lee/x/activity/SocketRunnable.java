package com.lee.x.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by android on 2017/4/26.
 */
public class SocketRunnable implements Runnable {


    private Socket socket;
    InputStream is;
    OutputStream os;

    public SocketRunnable() {
        try {
            socket = new Socket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            socket.connect(new InetSocketAddress("192.168.1.120", 10000), 1000 * 20);
            is = socket.getInputStream();
            os = socket.getOutputStream();

            os.write(11);
            os.write(11);
            os.write(11);
            os.write(11);

            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void w() {
        byte b = 0x11;
        try {
            os.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
