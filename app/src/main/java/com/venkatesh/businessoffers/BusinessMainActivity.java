package com.venkatesh.businessoffers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class BusinessMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Integer Request_code_mainactivty = 1213;
    ArrayList<Integer> titles = new ArrayList<>();
    private boolean doubleBackToExitPressedOnce;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(BusinessMainActivity.this, AddOfferActivity.class);
                startActivityForResult(intent, Request_code_mainactivty);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_get_location) {
            startActivity(new Intent(this, MapsActivityCurrentPlace.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        selectMenuItem(item);


      /*  int id = item.getItemId();

        if (id == R.id.nav_offers) {




        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void selectMenuItem(MenuItem item) {

        View v = new View(BusinessMainActivity.this);
        v.setId(item.getItemId());
        doClick(v);
    }

    private void doClick(View v) {

        Fragment fragment = null;
        int title = R.string.offers_;

        switch (v.getId()) {
           /* case R.id.nav_offers:
                Intent profile = new Intent(BusinessMainActivity.this, RecruiterProfileCopy.class);
                startActivity(profile);
                break;*/

            case R.id.nav_offers:
                title = R.string.offers_;
                fragment =  AllOffersFragment.newInstance(10);

//                fragment.newInstance(10);
                break;
        }


        if (fragment!=null){

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            titles.add(title);
            ft.add(R.id.frame_container, fragment);
            ft.addToBackStack("offers");

        }

    }


  /*  @Override
    public void onBackPressed() {

        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();

            int index = titles.size() - 1;
            if (index > -1)
                titles.remove(titles.get(index));
            if (titles.size() == 0)
                toolbar.setTitle(R.string.offers_);
            else
                toolbar.setTitle(titles.get(titles.size() - 1));

        } else {
            if (doubleBackToExitPressedOnce) {
                Toast.makeText(this, "pressed twice", Toast.LENGTH_SHORT).show();
               *//* Intent in = new Intent(this, Home.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                in.putExtra("exit", true);
                startActivity(in);*//*
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
*/

}
