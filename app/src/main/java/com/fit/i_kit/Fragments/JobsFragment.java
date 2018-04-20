package com.fit.i_kit.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fit.i_kit.Acitivities.JobShowingActivity;
import com.fit.i_kit.Javaclasses.JobsData;
import com.fit.i_kit.Network.API;
import com.fit.i_kit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class JobsFragment extends Fragment {
    RecyclerView recyclerView;
    JobsAdapter horizontalAdapter;
    List<JobsData> data=new ArrayList<>();
    public JobsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_jobs, container, false);
        recyclerView = view.findViewById(R.id.recycle_jobs);
        data.clear();
      //  Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();

        getJobsdata();

        return view;

    }

    private void getJobsdata() {
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        String serverURL = API.videourl;
        final StringRequest getRequest = new StringRequest(Request.Method.GET, serverURL,
                new com.android.volley.Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            // Getting JSON Array node
                            JSONArray arr = jsonObj.getJSONArray("jobs");
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject(i);
                                //Toast.makeText(getActivity(),"hi",Toast.LENGTH_SHORT).show();

                                String sjobid = obj.getString("id");
                                String sjobtech = obj.getString("technology");
                                String sjobtitle = obj.getString("title");
                                String sjobcompany = obj.getString("company");
                                String sjobqual = obj.getString("qual");
                                String sjobexp = obj.getString("exp");
                                String sjobwalkin = obj.getString("walkin");
                                String sjoblocation = obj.getString("jobloc");
                                String sjobdesc = obj.getString("jobdesc");
                                String sjobroles = obj.getString("roles");
                                String sjoblastdate = obj.getString("lastdate");
                                String sjobreferlink = obj.getString("reflink");
                              //Toast.makeText(getActivity(),sjobcompany,Toast.LENGTH_SHORT).show();
                                JobsData job = new JobsData();
                                job.setId(Integer.parseInt(sjobid));
                                job.setCrole(sjobtitle);
                                job.setCname(sjobcompany);
                                job.setCqual(sjobqual);
                                job.setExpi(sjobexp);
                                job.setWalk(sjobwalkin);
                                job.setLocation(sjoblocation);
                                job.setDescr(sjobdesc);
                                job.setRolesresp(sjobroles);
                                job.setLastdate(sjoblastdate);
                                job.setReferlink(sjobreferlink);

                                data.add(job);


                            }
                            horizontalAdapter = new JobsAdapter(data, getContext());
                            LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(horizontalLayoutManager);
                            recyclerView.setAdapter(horizontalAdapter);

                        } catch (JSONException e) {
                            Log.e("MainActivity", "Json parsing error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "No Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Toast.makeText(getActivity(), "Success fully Inserted", Toast.LENGTH_LONG).show();
                return null;
            }
        };
        queue.add(getRequest);
    }


//    public List<JobsData> fill_with_data() {
//
//        List<JobsData> data = new ArrayList<>();
//
//        data.add(new JobsData( "Fratello Innotech", "Android Developer", "B.Tech", "0-3yrs", "12/04/2018", "09:00 AM","Hyderabad", "Software Company providing number of vacanciew", "Strong knowlegde in corejava and android", "12/04/2018", "www.fratelloinnotech.com"));
//        data.add(new JobsData( "Fratello Innotech", "Web Developer", "B.Tech", "0-3yrs", "12/04/2018", "09:00 AM","Hyderabad", "Software Company providing number of vacanciew", "Strong knowlegde in corejava and android", "12/04/2018", "www.fratelloinnotech.com"));
//        data.add(new JobsData( "Fratello Innotech", "Php Developer", "B.Tech", "0-3yrs", "12/04/2018", "09:00 AM","Hyderabad", "Software Company providing number of vacanciew", "Strong knowlegde in corejava and android", "12/04/2018", "www.fratelloinnotech.com"));
//        data.add(new JobsData( "Fratello Innotech", "DotNet Developer", "B.Tech", "0-3yrs", "12/04/2018", "09:00 AM","Hyderabad", "Software Company providing number of vacanciew", "Strong knowlegde in corejava and android", "12/04/2018", "www.fratelloinnotech.com"));
//
//        return data;
//    }

    public class JobsAdapter extends RecyclerView.Adapter<JobsFragment.JobsAdapter.MyViewHolder> {


        List<JobsData> horizontalList ;
        Context context;


        JobsAdapter(List<JobsData> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobsmodel, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.name.setText(horizontalList.get(position).getCname());
            holder.role.setText(horizontalList.get(position).getCrole());
            holder.qual.setText(horizontalList.get(position).getCqual());
            holder.exp.setText(horizontalList.get(position).getExpi());
            holder.walkin.setText(horizontalList.get(position).getWalk());
            holder.time.setText(horizontalList.get(position).getTime());
            holder.view_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String names = horizontalList.get(position).getCname();
                    String roles = horizontalList.get(position).getCrole();
                    String quals = horizontalList.get(position).getCqual();
                    String exps = horizontalList.get(position).getExpi();
                    String walkins = horizontalList.get(position).getWalk();
                    String times = horizontalList.get(position).getTime();
                    String locations = horizontalList.get(position).getLocation();
                    String descrp = horizontalList.get(position).getDescr();
                    String rolesresps = horizontalList.get(position).getRolesresp();
                    String lastdates = horizontalList.get(position).getLastdate();
                    String referlinks = horizontalList.get(position).getReferlink();
                    Intent i = new Intent(getActivity(), JobShowingActivity.class);
                    i.putExtra("CName", names);
                    i.putExtra("CRoles", roles);
                    i.putExtra("CQual", quals);
                    i.putExtra("CExps", exps);
                    i.putExtra("CWalk", walkins);
                    i.putExtra("CTime", times);
                    i.putExtra("CLocation", locations);
                    i.putExtra("CDescrp", descrp);
                    i.putExtra("CRoleResp", rolesresps);
                    i.putExtra("CLastDate", lastdates);
                    i.putExtra("CRefer", referlinks);
                    startActivity(i);
                }
            });
        }
        @Override
        public int getItemCount() {
            return horizontalList.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name, role, qual, exp, walkin, time,location,descr, rolesresp, lastdate;
            CardView view_card;

            MyViewHolder(View view) {
                super(view);
                role = view.findViewById(R.id.role);
                name = view.findViewById(R.id.company);
                qual = view.findViewById(R.id.qual);
                exp = view.findViewById(R.id.experience);
                walkin = view.findViewById(R.id.date);
                time = view.findViewById(R.id.time);
                view_card = view.findViewById(R.id.card);
            }
        }
    }
}
