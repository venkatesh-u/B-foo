package com.venkatesh.businessoffers;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.venkatesh.businessoffers.fragments.AllOffersFragment;
import com.venkatesh.businessoffers.interfaces.ConformationListener;

import java.util.ArrayList;

public class BusinessMainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Integer Request_code_mainactivty = 1213;
    ArrayList<Integer> titles = new ArrayList<>();
    private boolean doubleBackToExitPressedOnce;
    Toolbar toolbar;

    FragmentManager fragmentManager= getSupportFragmentManager();

    private Dialog globalShowConfDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_business);
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

        doClick(R.id.nav_offers);
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
        int id = item.getItemId();
        doClick(id);
    }

    private void doClick(int id) {

        Fragment fragment = null;
        int title = R.string.offers_;

        switch (id) {
            case R.id.nav_offers:
                title = R.string.offers_;
                fragment = new AllOffersFragment();
                break;

            case R.id.nav_sign_out:
                if (this.checkConnection()) {
                    globalShowConfDialog = Utils.globalShowConfirmationDialog(BusinessMainActivity.this,
                            null, "Are you sure you want to Sign out?", new ConformationListener() {
                        @Override
                        public void conformed() {
                            Utils.signOut(BusinessMainActivity.this);
                        }
                    });
                    globalShowConfDialog.show();
                }
                else {
                    Utils.showNetworkErrorDilaogue(BusinessMainActivity.this);
                }
                break;

            case R.id.nav_profile:
                Toast.makeText(mMyApp, "Profile..", Toast.LENGTH_SHORT).show();
                break;
        }


        if (fragment!=null){
            titles.add(title);
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment)
                    .addToBackStack("offers").commit();;

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Utils.REQ_CODE_EDIT_OFFER){
            Toast.makeText(this, "Back to Main page", Toast.LENGTH_SHORT).show();
        }
    }

}
