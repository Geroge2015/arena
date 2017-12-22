package com.example.cm.testrv.utils.mythread;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * @author George on 2017/12/22.
 *
 */

public class MyThreadFactory implements ThreadFactory {

    private int counter;
    private String prefix;

    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
        counter = 1;
    }

    @Override
    public Thread newThread(@NonNull Runnable r) {
        MyThread myThread = new MyThread(r, prefix + counter);
        counter++;
        return myThread;
    }
}
