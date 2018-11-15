package com.fit.i_kit.Acitivities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fit.i_kit.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Objects;

public class QuizResultsActivity extends AppCompatActivity implements OnChartValueSelectedListener {
PieChart pieChart;
TextView textView;
String course;
Button button;
    Toolbar toolbar;
    float c,n,a,w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        String correct = getIntent().getStringExtra("correct");
        String nota = getIntent().getStringExtra("nota");

        c = Float.parseFloat(correct);
        n = Float.parseFloat(nota);
        a = 10-n;
        w = a-c;
       // Toast.makeText(getApplicationContext(),"values"+c+n+a+w,Toast.LENGTH_LONG).show();


        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(QuizResultsActivity.this, CourseContentAcitivity.class);
                intent.putExtra("viewpager_position", 7);
                startActivity(intent);
                finish();
              //  onBackPressed();

            }
        });
        textView=findViewById(R.id.restype);
        pieChart = (PieChart) findViewById(R.id.piechart);
        SharedPreferences sharedpreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        course = sharedpreferences.getString("title", "I-KIT");
        textView.setText(course+"Quiz");
        pieChart.setUsePercentValues(true);
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(c, 0));
        yvalues.add(new Entry(n, 1));
        yvalues.add(new Entry(w, 2));
//        yvalues.add(new Entry(25f, 3));
//        yvalues.add(new Entry(23f, 4));
//        yvalues.add(new Entry(17f, 5));
        PieDataSet dataSet = new PieDataSet(yvalues, "");
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Right Answers");
        xVals.add("Not Attempt");
        xVals.add("Wrong Answers");
//        xVals.add("April");
//        xVals.add("May");
//        xVals.add("June");
        PieData data = new PieData(xVals, dataSet);
        // In Percentage term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(data);
        pieChart.setDescription(course+" Quiz Result");

        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleRadius(25f);

        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.setOnChartValueSelectedListener(this);

        pieChart.animateXY(1400, 1400);

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
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
//                Intent intent  = new Intent(QuizResultsActivity.this, CourseContentAcitivity.class);
//                intent.putExtra("viewpager_position", 7);
//                startActivity(intent);
//                finish();
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

//    @Override
//    public void onBackPressed() {
//        Intent intent  = new Intent(QuizResultsActivity.this, CourseContentAcitivity.class);
//        intent.putExtra("viewpager_position", 7);
//        startActivity(intent);
//        finish();
//    }

}
