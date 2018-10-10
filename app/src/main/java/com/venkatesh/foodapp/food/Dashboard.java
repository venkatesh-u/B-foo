package com.venkatesh.foodapp.food;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.venkatesh.foodapp.R;

import java.util.ArrayList;


public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Integer> titles = new ArrayList<>();
    FragmentManager fragmentManager = getSupportFragmentManager();
    android.support.v4.app.Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        doClick(R.id.first_page);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doClick(int id) {
        int title = R.string.dashboard;

        if (id == R.id.nav_gallery) {
            title = R.string.gallery;

            // Handle the camera action
//            fragment=new WorshipTimingsFrag();
//            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, "nav_gallery")
//                    .addToBackStack("nav_gallery").commit();
        }
//        else if (id == R.id.bible) {
//            title = R.string.bible;
//            fragment=new BibleFragment();
//            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, "bible")
//                    .addToBackStack("bible").commit();
//        } else if (id == R.id.contact) {
//            title = R.string.contact_us;
//            fragment  = new ContactUsFragment();
//            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, "ContactUs")
//                    .addToBackStack("ContactUs").commit();
//        } else if (id == R.id.gallery) {
//            title = R.string.gallery;
//            fragment=new Gallery();
//            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, "Gallery")
//                    .addToBackStack("Gallery").commit();
//        }
        else if (id == R.id.first_page){
            title = R.string.dashboard;
            fragment = new Restaurants();
            Bundle bundle = new Bundle();
            bundle.putString("userName", "mUsername");
            fragment.setArguments( bundle);

            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, "Restaurants")
                    .addToBackStack("Restaurants").commit();
        }
//        else if (id == R.id.almanac) {
//            fragment=new AlmanacFragment();
//            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, "Almanac")
//                    .addToBackStack("Gallery").commit();
//        }
        titles.add(title);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        doClick(id);
        return true;
    }
}
