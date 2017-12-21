package com.example.cm.testrv.utils.mythread;

import java.util.Date;

/**
 * Created by cm on 2017/12/21.
 */

public class MyThread extends Thread {

    private Date creationDate;
    private Date startDate;
    private Date finishDate;

    public MyThread(Runnable r, String name) {

        super(r, name);
        setCreateName();
    }

    private void setCreateName() {
        creationDate = new Date();
    }

    @Override
    public void run() {
        setStartDate();
        super.run();

    }

    private void setStartDate() {
        startDate = new Date();
    }

    private void setFinishDate() {
        finishDate = new Date();
    }

    public long getExecutionTime() {
        return finishDate.getTime() - startDate.getTime();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName()).append(":").append(" Creation Date: ").append(creationDate).append(" Start Date: ");
        return builder.toString();
    }
}
