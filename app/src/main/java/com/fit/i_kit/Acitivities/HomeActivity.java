package com.fit.i_kit.Acitivities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.fit.i_kit.Adapters.CustomAdapter;
import com.fit.i_kit.Network.ConnectivityReceiver;
import com.fit.i_kit.Network.MyApplication;
import com.fit.i_kit.R;
import com.fit.i_kit.Javaclasses.Homemodel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    CustomAdapter adapter;
    GridView gv;
    private Toolbar toolbar;
    TextView home;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//For Advertisements
        MobileAds.initialize(this, "ca-app-pub-4682541119478126~8576979007");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener());

        checkConnection();


        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);

        home = findViewById(R.id.home_name);
        home.setText("Home");

        gv = findViewById(R.id.gv);
        adapter = new CustomAdapter(this, getData());
        gv.setAdapter(adapter);

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }
    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        } Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),message , Snackbar.LENGTH_LONG);
         snackbar.show();
//        Snackbar snackbar = Snackbar.make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    private ArrayList<Homemodel> getData() {
        ArrayList<Homemodel> spacecrafts = new ArrayList<>();
        Homemodel s = new Homemodel();
        s.setName("Java");
        s.setPropellant("Lets's begin..");
        s.setImage(R.drawable.ic_java);
        s.setDid(R.drawable.circlebackgroundred);
        spacecrafts.add(s);

        s = new Homemodel();
        s.setName("C");
        s.setPropellant("Let's begin..");
        s.setDid(R.drawable.circlebackgroundblue);
        s.setImage(R.drawable.c_language);
        spacecrafts.add(s);

        s = new Homemodel();
        s.setName("Android");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundteal);
        s.setImage(R.drawable.android);
        spacecrafts.add(s);

        s = new Homemodel();
        s.setName("Python");
        s.setPropellant("Coming Soon...");
        s.setImage(R.drawable.ic_call_black_24dp);
        s.setDid(R.drawable.circlebackgroundgreen);
        s.setImage(R.drawable.python);
        spacecrafts.add(s);

        s = new Homemodel();
        s.setName("Web Design");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundred);
        s.setImage(R.drawable.ic_webdesign);
        spacecrafts.add(s);

        s = new Homemodel();
        s.setName("Dot Net");
        s.setPropellant("Coming Soon...");
        s.setImage(R.drawable.ic_dotnet);
        s.setDid(R.drawable.circlebackgroundpink);
        spacecrafts.add(s);

        s = new Homemodel();
        s.setName("C++");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundbrown);
        s.setImage(R.drawable.ic_c);
        spacecrafts.add(s);

        s = new Homemodel();
        s.setName("PHP");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundyello);
        s.setImage(R.drawable.ic_php_logo);
        spacecrafts.add(s);

        s = new Homemodel();
        s.setName("Testing");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundorange);
        s.setImage(R.drawable.ic_selenium);
        spacecrafts.add(s);

        s = new Homemodel();
        s.setName("Oracle");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundamber);
        s.setImage(R.drawable.ic_orcle);
        spacecrafts.add(s);

        return spacecrafts;
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dots, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(getApplicationContext(), AboutusActivity.class);
                startActivity(intent);
                return true;
            case R.id.contact:
                intent = new Intent(getApplicationContext(), ContactusActivity.class);
                startActivity(intent);
                return true;
            case R.id.share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareapp = "\nLet me recommend you this application\n\n";
                    shareapp = shareapp + "https://play.google.com/store/apps/details?id=com.fit.i_kit";
                    i.putExtra(Intent.EXTRA_TEXT, shareapp);
                    startActivity(Intent.createChooser(i, "Choose One"));
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}
