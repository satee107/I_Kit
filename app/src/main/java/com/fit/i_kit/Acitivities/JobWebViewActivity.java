package com.fit.i_kit.Acitivities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fit.i_kit.R;

import java.net.URL;
import java.util.Objects;

public class JobWebViewActivity extends AppCompatActivity {
    Toolbar toolbar;
    WebView mywebview;
    String crefer;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_web_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        crefer = getIntent().getStringExtra("CRefer");
        mywebview = findViewById(R.id.webview);
        WebSettings webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebview.loadUrl("http://" + crefer);
        mywebview.setWebViewClient(new WebViewClient());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dots, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                onBackPressed();
                return true;
            case R.id.about:
                Intent intent = new Intent(getApplicationContext(), AboutusActivity.class);
                startActivity(intent);
                return true;
            case R.id.contact:
                intent = new Intent(getApplicationContext(), ContactusActivity.class);
                startActivity(intent);
                return true;
            case R.id.share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Interview Kit (IKIT)");
                    String shareapp = "\nLet me recommend you this application\n\n";
                    shareapp = shareapp + "https://play.google.com/store/apps/details?id=com.fit.i_kit";
                    i.putExtra(Intent.EXTRA_TEXT, shareapp);
                    startActivity(Intent.createChooser(i, "Choose One"));
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
