package com.fit.i_kit.Acitivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.fit.i_kit.Fragments.DefinitionsFragment;
import com.fit.i_kit.Fragments.ExperiencedFragment;
import com.fit.i_kit.Fragments.FresherFragment;
import com.fit.i_kit.Fragments.JobsFragment;
import com.fit.i_kit.Fragments.ProgramsFragment;
import com.fit.i_kit.Fragments.QuizFragment;
import com.fit.i_kit.Fragments.ResumeFragment;
import com.fit.i_kit.Fragments.StudymaterialFragment;
import com.fit.i_kit.Fragments.VideosFragment;
import com.fit.i_kit.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseContentAcitivity extends AppCompatActivity {
    String course;
    ViewPager viewPager;
    Toolbar toolbar;
    TextView course_name;
    ShareActionProvider mShareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);

        int position = 0;
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            position = extras.getInt("viewpager_position");
        }

        course_name=findViewById(R.id.coursename);
        SharedPreferences sharedpreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        course = sharedpreferences.getString("title", "I-KIT");
        course_name.setText(course);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setCurrentItem(position);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new StudymaterialFragment(), "StudyMaterial");
        adapter.addFrag(new DefinitionsFragment(), "Concepts");
        adapter.addFrag(new FresherFragment(), "Fresher I.Q's");
        adapter.addFrag(new ExperiencedFragment(), "Experience I.Q's");
        adapter.addFrag(new ResumeFragment(), "Resume");
        adapter.addFrag(new ProgramsFragment(), "Programs");
        adapter.addFrag(new VideosFragment(), "Videos");
        //adapter.addFrag(new QuizFragment(), "Quiz");
        adapter.addFrag(new JobsFragment(), "Jobs");
        viewPager.setAdapter(adapter);
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
            case android.R.id.home:
                // todo: goto back activity from here
                  onBackPressed();
//                Intent intent = new Intent(CourseContentAcitivity.this, HomeActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                return true;
            case R.id.home:
                Intent intent =new Intent(getApplicationContext(), HomeActivity.class);
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
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Interview Kit (IKIT)");
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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
