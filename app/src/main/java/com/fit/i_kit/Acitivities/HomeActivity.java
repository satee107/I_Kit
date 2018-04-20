package com.fit.i_kit.Acitivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.fit.i_kit.Adapters.CustomAdapter;
import com.fit.i_kit.Fragments.FresherFragment;
import com.fit.i_kit.R;
import com.fit.i_kit.Javaclasses.Homemodel;

import java.util.ArrayList;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    CustomAdapter adapter;
    GridView gv;
    private Toolbar toolbar;
    TextView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);

        home=findViewById(R.id.home_name);
        home.setText("Home");

        gv=findViewById(R.id.gv);
        adapter=new CustomAdapter(this,getData());
        gv.setAdapter(adapter);

    }
    private ArrayList<Homemodel> getData()
    {
        ArrayList<Homemodel> spacecrafts=new ArrayList<>();

        Homemodel s=new Homemodel();
        s.setName("Java");
        s.setPropellant("Programming...");
        s.setImage(R.drawable.ic_java);
        s.setDid(R.drawable.circlebackgroundpurple);
        spacecrafts.add(s);

        s=new Homemodel();
        s.setName("C");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundblue);
        s.setImage(R.drawable.c_language);
        spacecrafts.add(s);

        s=new Homemodel();
        s.setName("Android");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundteal);
        s.setImage(R.drawable.android);
        spacecrafts.add(s);

        s=new Homemodel();
        s.setName("Python");
        s.setPropellant("Coming Soon...");
        s.setImage(R.drawable.ic_call_black_24dp);
        s.setDid(R.drawable.circlebackgroundgreen);
        s.setImage(R.drawable.python);
        spacecrafts.add(s);

        s=new Homemodel();
        s.setName("Web Design");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundred);
        s.setImage(R.drawable.ic_webdesign);
        spacecrafts.add(s);

        s=new Homemodel();
        s.setName("Dot Net");
        s.setPropellant("Coming Soon...");
        s.setImage(R.drawable.ic_dotnet);
        s.setDid(R.drawable.circlebackgroundpink);
        spacecrafts.add(s);

        s=new Homemodel();
        s.setName("C++");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundbrown);
        s.setImage(R.drawable.ic_c);
        spacecrafts.add(s);

        s=new Homemodel();
        s.setName("PHP");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundyello);
        s.setImage(R.drawable.ic_php_logo);
        spacecrafts.add(s);

        s=new Homemodel();
        s.setName("Testing");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundorange);
        s.setImage(R.drawable.ic_selenium);
        spacecrafts.add(s);

        s=new Homemodel();
        s.setName("Oracle");
        s.setPropellant("Coming Soon...");
        s.setDid(R.drawable.circlebackgroundamber);
        s.setImage(R.drawable.ic_orcle);
        spacecrafts.add(s);

        return spacecrafts;
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
                Intent intent =new Intent(getApplicationContext(), AboutusActivity.class);
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
