package com.fit.i_kit.Adapters;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
                    case "dot net":
                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder.setMessage("It is upadating, It will come soon...")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert = builder.create();
                        //Setting the title manually
                        alert.setTitle("Sorry !");
                        alert.show();

                       // c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "android":
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(c);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder2.setMessage("It is upadating, It will come soon...")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert2 = builder2.create();
                        //Setting the title manually
                        alert2.setTitle("Sorry !");
                        alert2.show();
                      // c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "oracle":
                        AlertDialog.Builder builder3 = new AlertDialog.Builder(c);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder3.setMessage("It is upadating, It will come soon...")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert3 = builder3.create();
                        //Setting the title manually
                        alert3.setTitle("Sorry !");
                        alert3.show();
                      // c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "web design":
                        AlertDialog.Builder builder4 = new AlertDialog.Builder(c);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder4.setMessage("It is upadating, It will come soon...")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert4 = builder4.create();
                        //Setting the title manually
                        alert4.setTitle("Sorry !");
                        alert4.show();
                       //c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "c":
                        AlertDialog.Builder builder5= new AlertDialog.Builder(c);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder5.setMessage("It is upadating, It will come soon...")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert5 = builder5.create();
                        //Setting the title manually
                        alert5.setTitle("Sorry !");
                        alert5.show();
                     // c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "c++":
                        AlertDialog.Builder builder6 = new AlertDialog.Builder(c);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder6.setMessage("It is upadating, It will come soon...")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert6= builder6.create();
                        //Setting the title manually
                        alert6.setTitle("Sorry !");
                        alert6.show();
                       //c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "php":
                        AlertDialog.Builder builder7 = new AlertDialog.Builder(c);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder7.setMessage("It is upadating, It will come soon...")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert7 = builder7.create();
                        //Setting the title manually
                        alert7.setTitle("Sorry !");
                        alert7.show();
                       //c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "testing":
                        AlertDialog.Builder builder8 = new AlertDialog.Builder(c);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder8.setMessage("It is upadating, It will come soon...")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert8 = builder8.create();
                        //Setting the title manually
                        alert8.setTitle("Sorry !");
                        alert8.show();
                     // c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;
                    case "python":
                        AlertDialog.Builder builder9 = new AlertDialog.Builder(c);
                        //Uncomment the below code to Set the message and title from the strings.xml file
                        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder9.setMessage("It is upadating, It will come soon...")
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                    }
                                });

                        //Creating dialog box
                        AlertDialog alert9 = builder9.create();
                        //Setting the title manually
                        alert9.setTitle("Sorry !");
                        alert9.show();
                       //c.startActivity(new Intent(c, CourseContentAcitivity.class));
                        break;

                }

            }
        });

        return view;
    }
}
