package com.fit.i_kit.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.fit.i_kit.Acitivities.ExpResumeLookActivity;
import com.fit.i_kit.Acitivities.ResumeLookActivity;
import com.fit.i_kit.Javaclasses.Data;
import com.fit.i_kit.Javaclasses.Data2;
import com.fit.i_kit.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;


public class ResumeFragment extends Fragment {

    RecyclerView horizontal_recycler_view;
    RecyclerView horizontal_recycler_view2;
    List<Data> data = new ArrayList<>();
    List<Data2> data2 = new ArrayList<>();
    HorizontalAdapter horizontalAdapter;
    HorizontalAdapterexp horizontalAdapter2;

    String course="",fname="";
    String mailID = "";
    String mailSubject = "Attachment Sample";
    private static final int MY_PERMISSION_REQUESTSTORAGE=1;
    public static String networkErrorMessage = "Network not available";
    public static boolean checkInternetConnection = true;
    public static boolean showErrorMessage = true;


    public ResumeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resume, container, false);
        horizontal_recycler_view = view.findViewById(R.id.idRecyclerViewHorizontalList);

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        course = sharedpreferences.getString("title", "I-KIT");
        fname = course.toLowerCase()+".json";

        String jsonjava=null;
        try {
            InputStream is = getActivity().getAssets().open(fname);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonjava = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonjava);
            JSONArray events = jsonObject.getJSONArray("fresherresume");
            data.clear();
            for (int j=0; j < events.length(); j++){
                JSONObject cit = events.getJSONObject(j);
                String ques = cit.getString("freshresume");
                String ans = cit.getString("freshtit");
                Data fresherresume = new Data(ques,ans);
                data.add(fresherresume);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }


        horizontalAdapter = new HorizontalAdapter(data, getContext());
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);

        horizontal_recycler_view2 = view.findViewById(R.id.idRecyclerViewHorizontalList2);
        String jsonexpjava = null;
        try {
            InputStream is = getActivity().getAssets().open(fname);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonexpjava = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonexpjava);
            JSONArray events = jsonObject.getJSONArray("experienceresume");
            data2.clear();
            for (int j=0; j < events.length(); j++){
                JSONObject cit = events.getJSONObject(j);
                String ques = cit.getString("expresume");
                String ans = cit.getString("exprestit");
                Data2 expresumes = new Data2(ques,ans);
                data2.add(expresumes);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        horizontalAdapter2 = new HorizontalAdapterexp(data2, getContext());
        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view2.setLayoutManager(horizontalLayoutManager2);
        horizontal_recycler_view2.setAdapter(horizontalAdapter2);


        return view;
    }

//for fresher  horizonataladapter class..

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


        List<Data> horizontalList = Collections.emptyList();
        Context context;


        HorizontalAdapter(List<Data> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView, imageView2, imageView3, imageView4;
            TextView txtview;

            MyViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.resume_icon);
                imageView2 = view.findViewById(R.id.view);
                imageView3 = view.findViewById(R.id.download);
                imageView4 = view.findViewById(R.id.share);
                txtview = view.findViewById(R.id.reume_title);
            }
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumehorizontalmodel, parent, false);

            return new MyViewHolder(itemView);

        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.txtview.setText(horizontalList.get(position).txt);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                    String restitle=horizontalList.get(position).txt;
                    String freshres = horizontalList.get(position).resid;
                    Intent i = new Intent(getActivity(), ResumeLookActivity.class);
                    i.putExtra("ResType", freshres);
                    i.putExtra("Restitle",restitle);
                    startActivity(i);

                }

            });
            holder.imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String restitle=horizontalList.get(position).txt;
                    String freshres = horizontalList.get(position).resid;
                    Intent intent = new Intent(getActivity(), ResumeLookActivity.class);
                    intent.putExtra("ResType", freshres);
                    intent.putExtra("Restitle",restitle);
                    startActivity(intent);
//                    String freshres = horizontalList.get(position).resid;
//
//                    try {
//                        openfile(freshres);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }


                }
            });
            holder.imageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String freshres = horizontalList.get(position).resid;

                    copyAsset(freshres+".doc");

                }
            });
            holder.imageView4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    try {
                        String freshres = horizontalList.get(position).resid;

                        getFilesFromAssets(freshres);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

        }
        void getFilesFromAssets(String freshres) throws IOException {
            //fname = course.toLowerCase()+".json";
            AssetManager assetManager = getActivity().getAssets();
            //replace the name by your file name, make sure file is inside your assets folder
            InputStream in = assetManager.open(freshres+".doc");



            if (in != null) {
                File attachment = stream2file(in,freshres);
              //  sendMail(getActivity(), mailID, mailSubject, attachment, null);
                Intent intentShareFile = new Intent(Intent.ACTION_SEND);
                intentShareFile.setType("application/pdf");
                intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+attachment));

                intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                        freshres+"_resume");
                intentShareFile.putExtra(Intent.EXTRA_TEXT, freshres+"_resume");

                startActivity(Intent.createChooser(intentShareFile, "Share Resume"));
            }
            else
            {
                Log.d(TAG, "getFilesFromAssets: file not found");
            }
        }




//        void openfile(String freshres) throws IOException {
//            //fname = course.toLowerCase()+".json";
//            AssetManager assetManager = getActivity().getAssets();
//            //replace the name by your file name, make sure file is inside your assets folder
//            InputStream in = assetManager.open(freshres+".doc");
//
//
//
//            if (in != null) {
//                File attachment = stream2file(in,freshres);
//                //  sendMail(getActivity(), mailID, mailSubject, attachment, null);
//                Intent intentShareFile = new Intent(Intent.ACTION_VIEW);
//                intentShareFile.setType("application/pdf");
//               intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+attachment));
//
//              //  intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
//              //          freshres+"_resume");
//               // intentShareFile.putExtra(Intent.EXTRA_TEXT, freshres+"_resume");
//
//                startActivity(intentShareFile);
//            }
//            else
//            {
//                Log.d(TAG, "getFilesFromAssets: file not found");
//            }
//        }





        // Creating temp file, then only we can add this file as attachment

        public File stream2file(InputStream in,String fname) throws IOException {

            final File tempFile = File.createTempFile(fname, ".doc",
                    getActivity().getExternalCacheDir());
            tempFile.deleteOnExit();

            FileOutputStream out = new FileOutputStream(tempFile);

            // for this you need add the following dependency in your build.gradle
            // compile 'org.apache.commons:commons-io:1.3.2'

            IOUtils.copy(in, out);
            return tempFile;
        }

        private void sendMail(Context context, String mailID, String subject, File attachment, Uri uri) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("vnd.android.cursor.dir/email");
            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            sharingIntent.putExtra(Intent.EXTRA_EMAIL, mailID);
           // sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "For APP sharing -testing.");
            // sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(attachment));
           // startActivity(Intent.createChooser(sharingIntent, "Sharing Options"));
            if (attachment != null)
                sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(attachment));
            else if (uri != null)
                sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
            if (!TextUtils.isEmpty(subject))
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

            if (isNetworkAvailable(context)) {
                if (isAppAvailable(context, "com.google.android.gm"))
                    sharingIntent.setPackage("com.google.android.gm");
                startActivityForResult(sharingIntent, 101);
            }
        }


        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }

    public static Boolean isAppAvailable(Context context, String appName) {
        PackageManager pm = context.getPackageManager();
        boolean isInstalled;
        try {
            pm.getPackageInfo(appName,PackageManager.GET_ACTIVITIES);
            isInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isInstalled = false;
        }
        return isInstalled;
    }
    private boolean isNetworkAvailable(Context context) {

        if (checkInternetConnection) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting())
                return true;
            else {
                if (showErrorMessage)
                    Toast.makeText(context, networkErrorMessage, Toast.LENGTH_SHORT).show();

                return false;
            }
        } else
            return true;

    }


    //exp horizontal adapter

    public class HorizontalAdapterexp extends RecyclerView.Adapter<HorizontalAdapterexp.MyViewHolder2> {


        List<Data2> horizontalList = Collections.emptyList();
        Context context;


        private HorizontalAdapterexp(List<Data2> horizontalList, Context context) {
            this.horizontalList = horizontalList;
            this.context = context;
        }


        public class MyViewHolder2 extends RecyclerView.ViewHolder {

            ImageView imageView, imageView2, imageView3, imageView4;
            TextView txtview;

            private MyViewHolder2(View view) {
                super(view);
                imageView = view.findViewById(R.id.exp_resume_icon);
                imageView2 = view.findViewById(R.id.exp_view);
                imageView3 = view.findViewById(R.id.exp_download);
                imageView4 = view.findViewById(R.id.exp_share);
                txtview = view.findViewById(R.id.exp_reume_title);
            }
        }


        @Override
        public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumehorizontalexpmodel, parent, false);

            return new MyViewHolder2(itemView2);
        }

        @Override
        public void onBindViewHolder(HorizontalAdapterexp.MyViewHolder2 holder, final int position) {

            //holder.imageView.setImageResource(horizontalList.get(position).imageId);
            holder.txtview.setText(horizontalList.get(position).resid);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    String expTitle=horizontalList.get(position).resid;
                    String expres = horizontalList.get(position).txt;
                    Intent i = new Intent(getActivity(), ExpResumeLookActivity.class);
                    i.putExtra("ExpResType", expres);
                    i.putExtra("Exptitle",expTitle);
                    startActivity(i);

                }

            });
            holder.imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String expTitle=horizontalList.get(position).resid;
                    String expres = horizontalList.get(position).txt;
                    Intent intent = new Intent(getActivity(), ExpResumeLookActivity.class);
                    intent.putExtra("ExpResType", expres);
                    intent.putExtra("Exptitle",expTitle);
                    startActivity(intent);


                }
            });
            holder.imageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String experience = horizontalList.get(position).txt;
                    copyAsset(experience+".doc");
                   // String expTitle=horizontalList.get(position).resid;
                 //   String fname = expTitle+".doc";
                   // copyAsset("resume1.doc");
                }
            });
            holder.imageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        String experience = horizontalList.get(position).txt;

                        getexpFilesFromAssets(experience);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                    sharingIntent.setType("text/plain");
//                    String shareBodyText = "Check it out. Your message goes here";
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
//                    startActivity(Intent.createChooser(sharingIntent, "Sharing Options"));
                }
            });
        }


        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }

    void getexpFilesFromAssets(String experience) throws IOException {
        //fname = course.toLowerCase()+".json";
        AssetManager assetManager = getActivity().getAssets();
        //replace the name by your file name, make sure file is inside your assets folder
        InputStream in = assetManager.open(experience+".doc");



        if (in != null) {
            File attachment = stream3file(in,experience);
            //  sendMail(getActivity(), mailID, mailSubject, attachment, null);
            Intent intentShareFile = new Intent(Intent.ACTION_SEND);
            intentShareFile.setType("application/pdf");
            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+attachment));

            intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                    experience+"_resume");
            intentShareFile.putExtra(Intent.EXTRA_TEXT, experience+"_resume");

            startActivity(Intent.createChooser(intentShareFile, "Share Resume"));
        }
        else
        {
            Log.d(TAG, "getFilesFromAssets: file not found");
        }
    }

    // Creating temp file, then only we can add this file as attachment

    public File stream3file(InputStream in,String fname) throws IOException {

        final File tempFile = File.createTempFile(fname, ".doc",
                getActivity().getExternalCacheDir());
        tempFile.deleteOnExit();

        FileOutputStream out = new FileOutputStream(tempFile);

        // for this you need add the following dependency in your build.gradle
        // compile 'org.apache.commons:commons-io:1.3.2'

        IOUtils.copy(in, out);
        return tempFile;
    }




    private void copyAsset(String filename){
        String dirpath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/IKIT/Resume/";

        File dir=new File(dirpath);

        if(!dir.exists()){
            dir.mkdirs();
        }
        AssetManager assetManager=getActivity().getAssets();
        InputStream in=null;
        OutputStream out=null;
        try{
            in=assetManager.open(filename);
            File outFile=new File(dirpath,filename);
            out=new FileOutputStream(outFile);
            copyFile(in,out);
            Toast.makeText(getActivity(),"downloaded! in internal storage at IKIT/Resume folder",Toast.LENGTH_LONG).show();

        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(getActivity(),"Failed!",Toast.LENGTH_LONG).show();

        }finally {
            if(in!=null){
                try{
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(out!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }}
    }

    private void copyFile(InputStream in, OutputStream out)throws IOException{

        byte[] buffer=new byte[1024];
        int read;
        while ((read=in.read(buffer))!=-1){
            out.write(buffer,0,read);
        }
    }



    }


