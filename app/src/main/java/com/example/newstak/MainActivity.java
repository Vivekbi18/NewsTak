package com.example.newstak;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem mhome, msports, mhealth, mscience, mentertainment, mtechnology;
    String api = "6a7e1dc9ac4241519b3ba7b3f181e616";
    Toolbar mtoolbar;
    PagerAdapter pagerAdapter;
    FirebaseAuth auth;
    FirebaseUser user;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        mhome = findViewById(R.id.home);
        msports = findViewById(R.id.sports);
        mhealth = findViewById(R.id.health);
        mscience = findViewById(R.id.science);
        mentertainment = findViewById(R.id.entertainment);
        mtechnology = findViewById(R.id.technology);
        viewPager = findViewById(R.id.fragmenrcontainer);
        tabLayout = findViewById(R.id.include);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();






        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 6);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.settings: {
                Intent i1 = new Intent(getApplicationContext(), Settings.class);
                startActivity(i1);
                finish();
                break;
            }


            case R.id.community: {
                Toast.makeText(this, "This field is empty", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.notification: {
                Toast.makeText(this, "This field is empty", Toast.LENGTH_SHORT).show();
                break;
            }


            case R.id.shareapp: {
                try {
                    Intent i3 = new Intent(Intent.ACTION_SEND);
                    i3.setType("text/plain");
                    i3.putExtra(Intent.EXTRA_SUBJECT, "News Tak");
                    i3.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                    startActivity(Intent.createChooser(i3, "Share Via"));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to share this app", Toast.LENGTH_SHORT).show();
                }
                break;
            }

            case R.id.contactus: {
                Toast.makeText(this, "This field is empty", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.signout: {
                auth.signOut();
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
                Toast.makeText(getApplicationContext(), "Signed out", Toast.LENGTH_SHORT).show();
                break;
            }

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;

    }

}
