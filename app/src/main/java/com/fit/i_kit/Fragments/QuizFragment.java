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
import com.fit.i_kit.Acitivities.QuizYouTubeActivity;
import com.fit.i_kit.Javaclasses.Homemodel;
import com.fit.i_kit.R;



public class QuizFragment extends Fragment {
    Button start;

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
        start = v.findViewById(R.id.quiz_login);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), QuizYouTubeActivity.class);
                startActivity(i);
            }
        });

        return v;
    }
}
