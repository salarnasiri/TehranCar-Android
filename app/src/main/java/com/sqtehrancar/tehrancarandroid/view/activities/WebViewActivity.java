package com.sqtehrancar.tehrancarandroid.view.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;


import com.sqtehrancar.tehrancarandroid.R;


public  class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://172.17.8.51:5000/"));
        startActivity(browserIntent);
    }


}