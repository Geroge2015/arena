package com.example.cm.testrv.requesthttp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.cm.testrv.R;

/**
 * Created by George on 2017/11/4.
 */

public class MyEditTextActivity extends AppCompatActivity {

    private EditText mEditText;
    private WebView mWebView;

    public static void startEditActivity(Context context) {
        Intent intent = new Intent(context, MyEditTextActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_activity);
        init();
    }

    private void init() {
        mEditText = ((EditText) findViewById(R.id.input_website_edit));
        mWebView = ((WebView) findViewById(R.id.webview_display));
        Button button = ((Button) findViewById(R.id.search_btn));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebSite();
            }
        });
    }

    private void openWebSite() {
        String string = mEditText.getEditableText().toString();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(string);
    }
}
