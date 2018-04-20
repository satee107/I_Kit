package com.fit.i_kit.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fit.i_kit.Javaclasses.FresherData;
import com.fit.i_kit.R;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FresherFragment extends Fragment {
    RecyclerView recyclerView;
    FresherAdapter horizontalAdapter;
    List<FresherData> data = new ArrayList<>();
    View openLayout;//copy this

    String course="",fname="";

    public FresherFragment() {
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
        View view = inflater.inflate(R.layout.fragment_fresher, container, false);
        recyclerView = view.findViewById(R.id.recycle_fresh);

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
            JSONArray events = jsonObject.getJSONArray("fresheriqs");
            data.clear();
            for (int j=0; j < events.length(); j++){
                JSONObject cit = events.getJSONObject(j);
                String ques = cit.getString("ques");
                String ans = cit.getString("ans");
                FresherData fresher = new FresherData(ques,ans);
                data.add(fresher);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        horizontalAdapter = new FresherAdapter(data, getContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(horizontalAdapter);

        return view;
    }

    private class FresherAdapter extends RecyclerView.Adapter<FresherFragment.FresherAdapter.MyViewHolder> {
        List<FresherData> horizontalList = Collections.emptyList();
        Context context;

        public FresherAdapter(List<FresherData> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

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
        public FresherAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.freshermodel, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final FresherAdapter.MyViewHolder holder, final int position) {
            holder.question.setText(horizontalList.get(position).ques);
            holder.answer.setText(horizontalList.get(position).ans);
            //copy this
            holder.question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getContext(),"message"+horizontalList.get(position).ans,Toast.LENGTH_LONG).show();
                    int v;

                        v = holder.panel.getVisibility();
                        if (v != View.VISIBLE) {
                            holder.panel.setVisibility(View.VISIBLE);
                            Log.v("CZ", "height..." + holder.panel.getHeight());
                        }
                        else
                        {
                            holder.panel.setVisibility(View.INVISIBLE);
                        }


                        if (v != View.VISIBLE) {
                            holder.panel.startAnimation(new FresherFragment.ScaleAnimToShow(1.0f, 1.0f, 1.0f, 0.0f, 500, holder.panel, true));
                        }
                        else
                        {
                            holder.panel.startAnimation(new FresherFragment.ScaleAnimToHide(1.0f, 1.0f, 1.0f, 0.0f, 500, holder.panel, true));
                        }

                }
            });


        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }

    }

    //copy in every page

    public class ScaleAnimToHide extends ScaleAnimation {

        private View mView;

        private LinearLayout.LayoutParams mLayoutParams;

        private int mMarginBottomFromY, mMarginBottomToY;

        private boolean mVanishAfter = false;

        public ScaleAnimToHide(float fromX, float toX, float fromY, float toY, int duration, View view, boolean vanishAfter) {
            super(fromX, toX, fromY, toY);
            setDuration(duration);
            openLayout = null;
            mView = view;
            mVanishAfter = vanishAfter;
            mLayoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            int height = mView.getHeight();
            mMarginBottomFromY = (int) (height * fromY) + mLayoutParams.bottomMargin - height;
            mMarginBottomToY = (int) (0 - ((height * toY) + mLayoutParams.bottomMargin)) - height;

            Log.v("CZ", "height..." + height + " , mMarginBottomFromY...." + mMarginBottomFromY + " , mMarginBottomToY.." + mMarginBottomToY);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                int newMarginBottom = mMarginBottomFromY + (int) ((mMarginBottomToY - mMarginBottomFromY) * interpolatedTime);
                mLayoutParams.setMargins(mLayoutParams.leftMargin, mLayoutParams.topMargin, mLayoutParams.rightMargin, newMarginBottom);
                mView.getParent().requestLayout();
                //Log.v("CZ","newMarginBottom..." + newMarginBottom + " , mLayoutParams.topMargin..." + mLayoutParams.topMargin);
            } else if (mVanishAfter) {
                mView.setVisibility(View.GONE);
            }
        }
    }

    public class ScaleAnimToShow extends ScaleAnimation {

        private View mView;

        private LinearLayout.LayoutParams mLayoutParams;

        private int mMarginBottomFromY, mMarginBottomToY;

        private boolean mVanishAfter = false;

        public ScaleAnimToShow(float toX, float fromX, float toY, float fromY, int duration, View view, boolean vanishAfter) {
            super(fromX, toX, fromY, toY);
            openLayout = view;
            setDuration(duration);
            mView = view;
            mVanishAfter = vanishAfter;
            mLayoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            mView.setVisibility(View.VISIBLE);
            int height = mView.getHeight();
            //mMarginBottomFromY = (int) (height * fromY) + mLayoutParams.bottomMargin + height;
            //mMarginBottomToY = (int) (0 - ((height * toY) + mLayoutParams.bottomMargin)) + height;

            mMarginBottomFromY = 0;
            mMarginBottomToY = height;

            Log.v("CZ", ".................height..." + height + " , mMarginBottomFromY...." + mMarginBottomFromY + " , mMarginBottomToY.." + mMarginBottomToY);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                int newMarginBottom = (int) ((mMarginBottomToY - mMarginBottomFromY) * interpolatedTime) - mMarginBottomToY;
                mLayoutParams.setMargins(mLayoutParams.leftMargin, mLayoutParams.topMargin, mLayoutParams.rightMargin, newMarginBottom);
                mView.getParent().requestLayout();
                //Log.v("CZ","newMarginBottom..." + newMarginBottom + " , mLayoutParams.topMargin..." + mLayoutParams.topMargin);
            }
        }

    }


}
