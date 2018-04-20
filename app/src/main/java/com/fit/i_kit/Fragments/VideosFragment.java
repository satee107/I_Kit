package com.fit.i_kit.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fit.i_kit.Acitivities.YouTubePlayerActivity;
import com.fit.i_kit.Javaclasses.FresherData;
import com.fit.i_kit.Javaclasses.VideoData;
import com.fit.i_kit.Network.API;
import com.fit.i_kit.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideosFragment extends Fragment {
    RecyclerView recyclerView;
    VideoAdapter horizontalAdapter;
    List<VideoData> data = new ArrayList<>();

    String course="",fname="";

    public VideosFragment() {
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
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        recyclerView = view.findViewById(R.id.recycle_video);

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
            JSONArray events = jsonObject.getJSONArray("videos");
            data.clear();
            for (int j=0; j < events.length(); j++){
                JSONObject cit = events.getJSONObject(j);
                String ids = cit.getString("id");
                String titles = cit.getString("title");
                String times = cit.getString("time");
                String names = cit.getString("name");
                VideoData videoData = new VideoData(ids,titles,times,names);
                data.add(videoData);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        horizontalAdapter = new VideoAdapter(data, getContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(horizontalAdapter);


        return view;

    }


//    public List<VideoData> fill_with_data() {
//
//        List<VideoData> data = new ArrayList<>();
//
//        data.add(new VideoData("tttG6SdnCd4","Java Introduction","5:00","Sateesh Patnana"));
//        data.add(new VideoData("x-hH_Txxzls","Java Introduction","5:00","Sateesh Patnana"));
//        data.add(new VideoData("TTh_qYMzSZk","Java Introduction","5:00","Sateesh Patnana"));
//        data.add(new VideoData("tttG6SdnCd4","Java Introduction","5:00","Sateesh Patnana"));
//        data.add(new VideoData("tttG6SdnCd4","Java Introduction","5:00","Sateesh Patnana"));
//        return data;
//    }

    public class VideoAdapter extends RecyclerView.Adapter<VideosFragment.VideoAdapter.MyViewHolder> {


        List<VideoData> horizontalList = Collections.emptyList();
        Context context;


        VideoAdapter(List<VideoData> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.videomodel, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.txt_tit.setText(horizontalList.get(position).getVidtit());
            holder.vide_time.setText(horizontalList.get(position).getVidtime());
            holder.auth.setText(horizontalList.get(position).getVidname());
            holder.view_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String vidName=horizontalList.get(position).getVidtit();
                    String vid=horizontalList.get(position).getVidid();
                    Intent i=new Intent(getActivity(), YouTubePlayerActivity.class);
                    i.putExtra("Vid",vid);//adding additional data using putExtras()
                    i.putExtra("VidName",vidName);
                    startActivity(i);


                }
            });
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView txt_tit, vide_time, auth;
            CardView view_card;
            MyViewHolder(View view) {
                super(view);
                txt_tit = view.findViewById(R.id.video_title);
                vide_time=view.findViewById(R.id.video_time);
                auth=view.findViewById(R.id.author);
                view_card=view.findViewById(R.id.card);
            }
        }

    }

}


