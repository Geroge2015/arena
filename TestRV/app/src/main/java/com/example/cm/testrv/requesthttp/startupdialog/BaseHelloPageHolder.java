package com.example.cm.testrv.requesthttp.startupdialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by George on 2017/12/12.
 */

public abstract class BaseHelloPageHolder {

    protected ViewGroup parent;
    protected Context context;

    public BaseHelloPageHolder(ViewGroup parent) {
        this.parent = parent;
        this.context = parent.getContext();
    }

    abstract View getHelloView();

    abstract void onStart();

    abstract void onStop();


}
