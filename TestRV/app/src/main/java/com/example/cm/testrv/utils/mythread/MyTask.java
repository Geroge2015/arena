package com.example.cm.testrv.utils.mythread;

import java.util.concurrent.TimeUnit;

/**
 * Created by cm on 2017/12/22.
 */

public class MyTask implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
