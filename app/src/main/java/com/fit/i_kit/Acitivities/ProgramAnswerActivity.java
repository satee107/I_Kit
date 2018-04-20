package com.fit.i_kit.Acitivities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.fit.i_kit.Fragments.ProgramsFragment;
import com.fit.i_kit.Fragments.VideosFragment;
import com.fit.i_kit.R;

import java.io.File;
import java.util.Objects;

public class ProgramAnswerActivity extends AppCompatActivity {
    String question;
    String answer;
    String queNUm;
    TextView t1,t2,number1,t3;
    Toolbar toolbar;
    Button hom;
    Button next,prevs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_answer);
        hom=findViewById(R.id.home_btn);
        hom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        next=findViewById(R.id.nxt);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        prevs=findViewById(R.id.prev);
        prevs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        queNUm = getIntent().getStringExtra("QueNo");
        question = getIntent().getStringExtra("Pgm_ques");
        answer = getIntent().getStringExtra("Pgm_ans");
        t1=findViewById(R.id.ques);
        t2=findViewById(R.id.ans);
        t3=findViewById(R.id.output);
        number1=findViewById(R.id.number);
        t1.setText(question);
        t2.setText(answer);
        number1.setText(queNUm);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

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
//                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                sharingIntent.setType("text/plain");
//                String shareBodyText = "Check it out. Your message goes here";
//                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
//                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
//                startActivity(Intent.createChooser(sharingIntent, "Sharing Options"));
                ApplicationInfo app = getApplicationContext().getApplicationInfo();
                String filePath = app.sourceDir;

                Intent shareintent = new Intent(Intent.ACTION_SEND);

                // MIME of .apk is "application/vnd.android.package-archive".
                // but Bluetooth does not accept this. Let's use "*/*" instead.
                shareintent.setType("*/*");


                // Append file and send Intent
                shareintent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
                startActivity(Intent.createChooser(shareintent, "Share app via"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }
