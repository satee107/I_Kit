package com.fit.i_kit.Adapters;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fit.i_kit.Acitivities.CourseContentAcitivity;
import com.fit.i_kit.R;
import com.fit.i_kit.Javaclasses.Homemodel;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter{
    private Context c;
    private ArrayList<Homemodel> spacecrafts;

    public CustomAdapter(Context c, ArrayList<Homemodel> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int i) {
        return spacecrafts.get(i);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        final Homemodel s= (Homemodel) this.getItem(i);

        ImageView img=  view.findViewById(R.id.coarse_icon);
        final TextView nameTxt=  view.findViewById(R.id.title);
        TextView propTxt= view.findViewById(R.id.description);

        //BIND
        nameTxt.setText(s.getName());
        propTxt.setText(s.getPropellant());
        img.setImageResource(s.getImage());
        img.setBackgroundResource(s.getDid());

        String course = s.getName();

        if(course.toLowerCase().equals("java")||course.toLowerCase().equals("c")){
             propTxt.setTextColor(Color.BLUE);
        }else{
            propTxt.setTextColor(Color.RED);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String course = s.getName();
                SharedPreferences sharedpreferences = c.getSharedPreferences("pref",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("title", course);
                editor.commit();

                switch(course.toLowerCase()){
                    case "java":
                        c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "c":
                        c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "dot net":
                       // c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "android":
                      // c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "oracle":
                      // c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "web design":
                       //c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;

                    case "c++":
                       //c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "php":
                       //c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "testing":
                     // c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "python":
                       //c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;

                }

            }
        });

        return view;
    }
}
