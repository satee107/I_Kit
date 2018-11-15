package com.fit.i_kit.Acitivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fit.i_kit.Network.API;
import com.fit.i_kit.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;

public class JobShowingActivity extends AppCompatActivity {
TextView t1; TextView t2; TextView t3; TextView t4; TextView t5; TextView t6; TextView t7; TextView t8; TextView t9;
TextView t10; TextView t11;
String cname;
String crole;
String cqual;
String cexps;
String cwalk;
String ctime;
String clocation;
String cdescrp;
String crolesresp;
String clastdate;
String crefer;
Toolbar toolbar;
    private AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_showing);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this, "ca-app-pub-4682541119478126~8576979007");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener());

        cname = getIntent().getStringExtra("CName");
        crole = getIntent().getStringExtra("CRoles");
        cqual = getIntent().getStringExtra("CQual");
        cexps = getIntent().getStringExtra("CExps");
        cwalk = getIntent().getStringExtra("CWalk");
        ctime = getIntent().getStringExtra("CTime");
        clocation = getIntent().getStringExtra("CLocation");
        cdescrp = getIntent().getStringExtra("CDescrp");
        crolesresp = getIntent().getStringExtra("CRoleResp");
        clastdate = getIntent().getStringExtra("CLastDate");
        crefer = getIntent().getStringExtra("CRefer");

        t1=findViewById(R.id.roles);
        t1.setText(crole);
        t2=findViewById(R.id.companies);
        t2.setText(cname);
        t3=findViewById(R.id.Quals);
        t3.setText(cqual);
        t4=findViewById(R.id.experis);
        t4.setText(cexps);
        t5=findViewById(R.id.dates);
        t5.setText(cwalk);
        t6=findViewById(R.id.times);
        t6.setText(ctime);
        t7=findViewById(R.id.joblocations);
        t7.setText(clocation);
        t8=findViewById(R.id.descripts);
        t8.setText(cdescrp);
        t9=findViewById(R.id.rolesresps);
        t9.setText(crolesresp);
        t10=findViewById(R.id.applydates);
        t10.setText(clastdate);
        t11=findViewById(R.id.referlinks);
        t11.setText(crefer);

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
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Check it out. Your message goes here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Sharing Options"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}

    public void link(View view) {
    String links=t11.getText().toString();
    Intent i=new Intent(getApplicationContext(),JobWebViewActivity.class);
    i.putExtra("CRefer",links);
    startActivity(i);
        Toast.makeText(getApplicationContext(),"Loading Web Page....",Toast.LENGTH_LONG).show();
    }
}
