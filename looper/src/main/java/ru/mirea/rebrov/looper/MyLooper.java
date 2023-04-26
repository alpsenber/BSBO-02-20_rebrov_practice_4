package ru.mirea.rebrov.looper;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.os.Handler;

import java.util.concurrent.TimeUnit;


public class MyLooper extends Thread{
    public Handler mHandler;
    private Handler mainHandler;
    public MyLooper(Handler mainThreadHandler)
    {
        mainHandler = mainThreadHandler;
    }
    public void run()
    {
        Log.d("MyLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                Integer age = Integer.parseInt(msg.getData().getString("age"));
                String job = msg.getData().getString("job");
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", String.format("работа: %s; возраст: %s", job, age));
                message.setData(bundle);
                try {
                    TimeUnit.SECONDS.sleep(age);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}
