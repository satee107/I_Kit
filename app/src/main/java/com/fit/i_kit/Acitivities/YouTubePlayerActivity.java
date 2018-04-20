package com.fit.i_kit.Acitivities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.fit.i_kit.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.Objects;

public class YouTubePlayerActivity  extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{
    Toolbar toolbar;
    String videoname;
    TextView t1;
    String course;
    public static final String API_KEY = "AIzaSyDAWGSnQeLYIlPR_3F3AvKuoihE_rA5yyg";

    //https://www.youtube.com/watch?v=<VIDEO_ID>
    public static String VIDEO_ID = "";
    private Toolbar supportActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_player);
        t1=findViewById(R.id.restype);
        SharedPreferences sharedpreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        course = sharedpreferences.getString("title", "I-KIT");
        t1.setText(course+"Videos");

        videoname=getIntent().getStringExtra("VidName");
        VIDEO_ID = getIntent().getStringExtra("Vid");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.content);
        frag.initialize(API_KEY, this);
      
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
        }
}

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
                youTubePlayer.loadVideo(VIDEO_ID);

    }}

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
               Toast.makeText(YouTubePlayerActivity.this, "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();

    }
}