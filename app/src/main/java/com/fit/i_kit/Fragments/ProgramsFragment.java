package com.fit.i_kit.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fit.i_kit.Acitivities.ProgramAnswerActivity;
import com.fit.i_kit.Javaclasses.FresherData;
import com.fit.i_kit.Javaclasses.ProgramData;
import com.fit.i_kit.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProgramsFragment extends Fragment {
    RecyclerView recyclerView;
    ProgramAdapter horizontalAdapter;
  //  public List<ProgramData> data;
  List<ProgramData> data = new ArrayList<>();

    String course="",fname="";

    public ProgramsFragment() {
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
        View view=inflater.inflate(R.layout.fragment_programs, container, false);
        recyclerView=view.findViewById(R.id.recycle_program);
        //data = fill_with_data();
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        course = sharedpreferences.getString("title", "I-KIT");
        fname = course.toLowerCase()+".json";

        String jsonjava = null;
        try {
            InputStream is = getActivity().getAssets().open(fname);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonjava = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonjava);
            JSONArray events = jsonObject.getJSONArray("programs");
            data.clear();
            for (int j=0; j < events.length(); j++){
                JSONObject cit = events.getJSONObject(j);
                String quesno = cit.getString("quesno");
                String ques = cit.getString("ques");
                String ans = cit.getString("ans");
                ProgramData programData = new ProgramData(quesno,ques,ans);
                data.add(programData);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        horizontalAdapter = new ProgramAdapter(data, getContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(horizontalAdapter);

        return view;
    }


    private class ProgramAdapter extends RecyclerView.Adapter<ProgramsFragment.ProgramAdapter.MyViewHolder> {
        List<ProgramData> horizontalList = Collections.emptyList();
        Context context;


        ProgramAdapter(List<ProgramData> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView question,answer;
            LinearLayout panel;


            MyViewHolder(View view) {
                super(view);
                question=view.findViewById(R.id.faqtext1);
                answer=view.findViewById(R.id.defans);
                panel = view.findViewById(R.id.faqpanel1);
            }
        }
        @Override
        public ProgramAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.programmodel, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ProgramAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.question.setText(horizontalList.get(position).ques);
            holder.answer.setText(horizontalList.get(position).ans);
           // holder.outputs.setText(horizontalList.get(position).output);
            holder.question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String queno=horizontalList.get(position).queno;
                    String prog_ques=horizontalList.get(position).ques;
                    String prog_ans=horizontalList.get(position).ans;
                    //String prog_output=horizontalList.get(position).output;
                    Intent i=new Intent(getActivity(), ProgramAnswerActivity.class);
                    i.putExtra("QueNo",queno);//adding additional data using putExtras()
                    i.putExtra("Pgm_ques",prog_ques);//adding additional data using putExtras()
                    i.putExtra("Pgm_ans",prog_ans);//adding additional data using putExtras()
                   // i.putExtra("Pgm_out",prog_output);
                    startActivity(i);
                }
            });



        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }
}
