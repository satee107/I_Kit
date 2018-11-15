package com.fit.i_kit.Acitivities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fit.i_kit.Javaclasses.QuizVideoData;
import com.fit.i_kit.Network.API;
import com.fit.i_kit.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class QuizYouTubeActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {
    Button opta;
    Button optb;
    Button optc;
    Toolbar toolbar;
    TextView count;
    String course;
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private static final int RECOVERY_REQUEST = 1;
    YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;

    Button nxt;
    int a = 0, b = 0, c = 0, d = 0;
    TextView t1;
    public int counter;
    ArrayList<QuizVideoData> data;
    public static final String API_KEY = "AIzaSyDAWGSnQeLYIlPR_3F3AvKuoihE_rA5yyg";
    public static String VIDEO_ID = "";
    QuizVideoData quiz;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_you_tube_acivity);

        data = (ArrayList<QuizVideoData>) getIntent().getSerializableExtra("data");

        countDownTimer = new MyCountDownTimer(60000, 1000);

        opta = findViewById(R.id.optionA);
        optb = findViewById(R.id.optionB);
        optc = findViewById(R.id.optionC);
        nxt = findViewById(R.id.next);
        count = findViewById(R.id.counters);
        YouTubePlayerSupportFragment frag = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.contents);
        frag.initialize(API_KEY, this);
//        countDownTimer.start();
//        quiz = data.get(i);
//        opta.setText("A. " + quiz.getOptiona());
//        optb.setText("B. " + quiz.getOptionb());
//        optc.setText("C. " + quiz.getOptionc());
//        VIDEO_ID = quiz.getVidid();

        // Toast.makeText(getApplicationContext(),"hi"+data.size(),Toast.LENGTH_LONG).show();
        t1 = findViewById(R.id.restype);
        SharedPreferences sharedpreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        course = sharedpreferences.getString("title", "I-KIT");
        t1.setText(course + "Quiz");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        gotoNextQuestion();


        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNextQuestion();

            }
        });


        opta.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (view.getId()) {
                    case R.id.optionA:
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

        optb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (view.getId()) {
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
        optc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (view.getId()) {
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

    void gotoNextQuestion() {
        countDownTimer.cancel();
        countDownTimer.start();

        if (i == 10) {
            Intent ires = new Intent(getApplicationContext(), QuizResultsActivity.class);
            ires.putExtra("correct",new Integer(a).toString());
            ires.putExtra("nota",new Integer(d).toString());
            startActivity(ires);
        } else {

//Toast.makeText(getApplicationContext(),"counts"+a+b+c+d,Toast.LENGTH_SHORT).show();

            quiz = data.get(i);

            if (opta.isPressed()){
                if(quiz.getAnswer().equals("1"))
                    a++;
            }
            else if (optb.isPressed())
            {
                if(quiz.getAnswer().equals("2"))
                    a++;

            }
            else if (optc.isPressed())
            {
                if(quiz.getAnswer().equals("3"))
                    a++;

            }
            else{
                d++;
            }

            i = i + 1;

            opta.setText(quiz.getSno() + "A. " + quiz.getOptiona());
            optb.setText(quiz.getSno() + "B. " + quiz.getOptionb());
            optc.setText(quiz.getSno() + "C. " + quiz.getOptionc());
            VIDEO_ID = quiz.getVidid();

            if (youTubePlayer != null) {
                youTubePlayer.cueVideo(VIDEO_ID);
                youTubePlayer.play();
            }

            opta.setPressed(false);
            opta.setFocusable(false);

            optb.setPressed(false);
            optb.setFocusable(false);

            optc.setPressed(false);
            optc.setFocusable(false);
        }

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
        // youTubePlayer.loadVideo(VIDEO_ID);
        youTubePlayer = player;

        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);

        // Toast.makeText(getApplicationContext(),"YouTubePlayer.onInitializationSuccess()",Toast.LENGTH_LONG).show();

        if (!b) {
            youTubePlayer.loadVideo(VIDEO_ID);
            youTubePlayer.play();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(QuizYouTubeActivity.this, "You Tube App might be not installed in your mobile", Toast.LENGTH_SHORT).show();

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {

            super(startTime, interval);

        }

        @Override
        public void onFinish() {
            gotoNextQuestion();
        }

        @Override

        public void onTick(long millisUntilFinished) {

            count.setText("" + millisUntilFinished / 1000);

        }

    }
}
