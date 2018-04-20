package com.fit.i_kit.Acitivities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.fit.i_kit.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Objects;


public class QuizYouTubeActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{
Button opta;
Button optb;
Button optc;
Toolbar toolbar;
TextView count;
String course;
    TextView t1;

    public int counter=60;

    public static final String API_KEY = "AIzaSyDAWGSnQeLYIlPR_3F3AvKuoihE_rA5yyg";
    public static String VIDEO_ID = "w0SDwUU7wpo";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_you_tube_acivity);
        t1=findViewById(R.id.restype);
        SharedPreferences sharedpreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        course = sharedpreferences.getString("title", "I-KIT");
        t1.setText(course+"Quiz");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.contents);
        frag.initialize(API_KEY, this);

        count=findViewById(R.id.counters);
        new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                count.setText(String.valueOf(counter));
                counter--;
            }
            public  void onFinish(){
                count.setText("FINISH!!");
            }
        }.start();

        opta= findViewById(R.id.optionA);
        opta.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
               switch (view.getId()){
                   case R.id.optionA :
                       opta.setPressed(true);
                       opta.setFocusable(true);
                       optb.setPressed(false);
                       optb.setFocusable(false);
                       optc.setPressed(false);
                       optc.setFocusable(false);
                       break;

               }
               return true;
           }
       });
        optb=findViewById(R.id.optionB);
        optb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (view.getId()){
                    case R.id.optionB:
                        optb.setPressed(true);
                        optb.setFocusable(true);
                        opta.setPressed(false);
                        opta.setFocusable(false);
                        optc.setPressed(false);
                        optc.setFocusable(false);
                break;
                }
                return true;
            }
        });
        optc=findViewById(R.id.optionC);
        optc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (view.getId()){
                    case R.id.optionC:
                        optc.setPressed(true);
                        optc.setFocusable(true);
                        opta.setPressed(false);
                        opta.setFocusable(false);
                        optb.setPressed(false);
                        optb.setFocusable(false);
                        break;
                }
                return true;
            }
        });

    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(VIDEO_ID);
        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(QuizYouTubeActivity.this, "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();

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
                Intent intent =new Intent(getApplicationContext(), AboutusActivity.class);
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
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Sharing Options"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}

}
