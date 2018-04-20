package com.fit.i_kit.Acitivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.fit.i_kit.R;
import com.joanzapata.pdfview.PDFView;

import java.util.Objects;

public class ExpResumeLookActivity extends AppCompatActivity {
    TextView t1;
    String expres,expTitle;
    private android.support.v7.widget.Toolbar toolbar;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_resume_look);
        expres = getIntent().getStringExtra("ExpResType");
        expTitle=getIntent().getStringExtra("Exptitle");
        t1=findViewById(R.id.restype);
        t1.setText(expTitle);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        pdfView=findViewById(R.id.pdfview);
        pdfView.fromAsset(expres+".pdf")
                .defaultPage(1)
                .showMinimap(false)
                .swipeVertical(true)
                .load();

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
