package com.fit.i_kit.Acitivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fit.i_kit.Fragments.ProgramsFragment;
import com.fit.i_kit.Fragments.VideosFragment;
import com.fit.i_kit.Javaclasses.ProgramData;
import com.fit.i_kit.Javaclasses.QuizVideoData;
import com.fit.i_kit.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProgramAnswerActivity extends AppCompatActivity {
    TextView course_name;
    String course;
    String question;
    String answer;
    String queNUm,output;
    TextView t1,t2,number1,t3;
    Toolbar toolbar;
    FloatingActionButton hom;
    FloatingActionButton next,prevs;
    List<ProgramData> data = new ArrayList<>();
    int qno=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_answer);
        t1=findViewById(R.id.ques);
        t2=findViewById(R.id.ans);
        t3=findViewById(R.id.output);
        number1=findViewById(R.id.number);
        queNUm = getIntent().getStringExtra("QueNo");
        question = getIntent().getStringExtra("Pgm_ques");
        answer = getIntent().getStringExtra("Pgm_ans");
        output = getIntent().getStringExtra("Pgm_out");
        data = (ArrayList<ProgramData>) getIntent().getSerializableExtra("data");
        qno = Integer.parseInt(queNUm);
       // Toast.makeText(getApplicationContext(),"size"+data.size(),Toast.LENGTH_SHORT).show();
        course_name=findViewById(R.id.coursename);
        SharedPreferences sharedpreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        course = sharedpreferences.getString("title", "I-KIT");
        course_name.setText(course+" Programs");

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
                qno = qno+1;
                if(qno<=data.size()){
                    ProgramData prog = data.get(qno-1);
                    t1.setText(prog.getQues());
                    t2.setText(Html.fromHtml(prog.getAns()));
                    number1.setText(prog.getQueno());
                    t3.setText(Html.fromHtml(prog.getOp()));
                }
                else
                {
                    qno=1;
                    ProgramData prog = data.get(qno-1);
                    t1.setText(prog.getQues());
                    t2.setText(Html.fromHtml(prog.getAns()));
                    number1.setText(prog.getQueno());
                    t3.setText(Html.fromHtml(prog.getOp()));
                }

            }
        });
        prevs=findViewById(R.id.prev);
        prevs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qno = qno-1;
                if(qno>0){
                    ProgramData prog = data.get(qno-1);
                    t1.setText(prog.getQues());
                    t2.setText(Html.fromHtml(prog.getAns()));
                    number1.setText(prog.getQueno());
                    t3.setText(Html.fromHtml(prog.getOp()));
                }
                else
                {
                    qno=data.size();
                    ProgramData prog = data.get(qno-1);
                    t1.setText(prog.getQues());
                    t2.setText(Html.fromHtml(prog.getAns()));
                    number1.setText(prog.getQueno());
                    t3.setText(Html.fromHtml(prog.getOp()));
                }
            }
        });


        t1.setText(question);
        t2.setText(Html.fromHtml(answer));
        number1.setText(queNUm);
        t3.setText(Html.fromHtml(output));

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
