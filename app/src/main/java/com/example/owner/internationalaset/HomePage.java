package com.example.owner.internationalaset;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/*
    activity general home page
 */

public class HomePage extends AppCompatActivity implements FragmentListEvents.FragmentEventslistener,
        FragmentListKeynote.FragmentKeynotelistener,FragmentListArticle.FragmentArticlelistener{
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        fragment = null;

        BottomNavigationView bottomNavigation = findViewById(R.id.toolbarBottom);
        HelperBottomNavigationView.disableShiftMode(bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.events:
                        Bundle bundle = new Bundle();
                        fragment = new FragmentListEvents();
                        fragment.setArguments(bundle);
                        fragmentSwitch(fragment);
                        break;
                    case R.id.control:
                        Intent i = new Intent(HomePage.this, ActivityControlPanel.class);
                        startActivity(i);
                    default:
                }
                return true;
            }
        });
    }

    //fragment changes
    public void fragmentSwitch(Fragment f) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, f, "currentFragment");
        transaction.commit();
    }

    public void getEventKey(String k){
        Intent i = new Intent(HomePage.this, MainEvent.class);
        i.putExtra("eventKey", k);
        startActivity(i);
    }

    public void getKeynoteKey(String k){}
    public void getArticleKey(String k){}
}
