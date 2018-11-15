package com.fit.i_kit.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fit.i_kit.Acitivities.CourseContentAcitivity;
import com.fit.i_kit.Acitivities.HomeActivity;
import com.fit.i_kit.Acitivities.QuizResultsActivity;
import com.fit.i_kit.Acitivities.QuizYouTubeActivity;
import com.fit.i_kit.Javaclasses.Homemodel;
import com.fit.i_kit.Javaclasses.QuizVideoData;
import com.fit.i_kit.Network.API;
import com.fit.i_kit.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class QuizFragment extends Fragment  {
    Button start;
    ArrayList<QuizVideoData> data=new ArrayList<>();
    private AdView mAdView;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);

        MobileAds.initialize(getContext(), "ca-app-pub-4682541119478126~8576979007");
        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener());

        start = v.findViewById(R.id.quiz_login);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQuizdata();
//                QuizVideoData data=new QuizVideoData();
//                String aopt=data.getOptiona();

            }
        });

        return v;
    }

    private void getQuizdata(){
        final RequestQueue queue = Volley.newRequestQueue(getContext());
        String serverURL = API.quizurl;
        final StringRequest getRequest = new StringRequest(Request.Method.GET, serverURL,
                new com.android.volley.Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            // Getting JSON Array node
                            JSONArray arr = jsonObj.getJSONArray("quiz");
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                //Toast.makeText(getActivity(),"hi",Toast.LENGTH_SHORT).show();
                                String squizid = obj.getString("videoid");
                                String sopta = obj.getString("opt1");
                                String soptb = obj.getString("opt2");
                                String soptc = obj.getString("opt3");
                                String sans=obj.getString("ans");
                                String vid = obj.getString("id");
                             //   Toast.makeText(getContext(),sopta,Toast.LENGTH_LONG).show();
                                QuizVideoData quiz = new QuizVideoData();
                                quiz.setVidid(squizid);
                                quiz.setOptiona(sopta);
                                quiz.setOptionb(soptb);
                                quiz.setOptionc(soptc);
                                quiz.setAnswer(sans);
                                quiz.setVid(Integer.parseInt(vid));
                                quiz.setSno(i+1);

                                data.add(quiz);
                            }

                            Intent i = new Intent(getActivity(), QuizYouTubeActivity.class);
                            i.putExtra("data",data);
                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No Network Connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Toast.makeText(getContext(), "Success fully Inserted", Toast.LENGTH_LONG).show();
                return null;
            }
        };

        queue.add(getRequest);
    }


}
