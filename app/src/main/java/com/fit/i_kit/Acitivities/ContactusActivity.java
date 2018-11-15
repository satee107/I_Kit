package com.fit.i_kit.Acitivities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.fit.i_kit.Network.API;
import com.fit.i_kit.Network.VolleySingleton;
import com.fit.i_kit.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactusActivity extends AppCompatActivity {
    private Toolbar toolbar;
    TextView contact;
    TextView mobile, website,mail;
    EditText name, mobile_no, comment, emailid;
    Button button;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        MobileAds.initialize(this, "ca-app-pub-4682541119478126~8576979007");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        contact = findViewById(R.id.contactus);
        contact.setText("Contact Us");

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emails=emailid.getText().toString();
                String names=name.getText().toString();
                String comments=comment.getText().toString();
                String mobiles=mobile_no.getText().toString();
                if (!isValidEmail(emails)) {
                    emailid.setError("Invalid Email");
                }else if(names.length()==0){
                     name.setError("Name cannot be Blank");
                }else if(comments.length()==0){
                    comment.setError("Write your comment / query");
                }else if(!isValidPhone(mobiles)){
                    mobile_no.setError("Phone number is Invalid");
                }
                else{
                    feedBack();
                }
            }
        });
        name = findViewById(R.id.name);
        mobile_no = findViewById(R.id.mobile_no);
        emailid=findViewById(R.id.emailId);
        comment = findViewById(R.id.comment);

        mobile = findViewById(R.id.call);
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent implicit = new Intent(Intent.ACTION_CALL, Uri.parse("tel:9010990285"));
                if (ActivityCompat.checkSelfPermission(ContactusActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(implicit);
            }
        });
        website=findViewById(R.id.website);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.fratelloinnotech.com/"));
                startActivity(implicit);
            }
        });
        mail=findViewById(R.id.mail);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uriText =
                        "mailto:fratelloinnotech@gmail.com" +
                                "?subject=" + Uri.encode("Query on Courses to Fratello Innotech") +
                                "&body=" + Uri.encode("some text here");

                Uri uri = Uri.parse(uriText);

                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(sendIntent, "Send email"));
                }
            }
        });
    }

    private void feedBack() {
        String serverURL = API.contactusurl;
        StringRequest sr = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String res = jsonObj.getString("result");
                    if (res.equals("success")) {

                        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
                        name.setText(null);
                        mobile_no.setText(null);
                        emailid.setText(null);
                        comment.setText(null);

                    } else {

                        Toast.makeText(getApplicationContext(), "Error while Inseerting!!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.e("ERROR", "EXCEPTION");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Contactus", "Error: " + error.getMessage());
                Log.d("Contactus", "" + error.getMessage() + "," + error.toString());
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                String names = name.getText().toString();
                String mobiles = mobile_no.getText().toString();
                String emails=emailid.getText().toString();
                String comments=comment.getText().toString();
                Map<String, String> data = new HashMap<String, String>();
                data.put("name", names);
                data.put("mobile", mobiles);
                data.put("email", emails);
                data.put("msg", comments);

                return data;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(sr);

    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //validating mobile number
    public static boolean isValidPhone(String phone)
    {
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }}
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

                Intent intent = new Intent(ContactusActivity.this, CourseContentAcitivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.home:
                intent =new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.about:
                intent =new Intent(getApplicationContext(), AboutusActivity.class);
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
        }
}}
