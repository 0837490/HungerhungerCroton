package com.himanshu.hungercroton.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.himanshu.hungercroton.R;
import com.himanshu.hungercroton.adapter.ExpandableNavigationListView;
import com.himanshu.hungercroton.fragments.HomeFragment;
import com.himanshu.hungercroton.helper.Common;
import com.himanshu.hungercroton.helper.SaveSharedPreference;
import com.himanshu.hungercroton.login.LoginActivity;
import com.himanshu.hungercroton.models.ChildModel;
import com.himanshu.hungercroton.models.HeaderModel;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ExpandableNavigationListView navigationExpandableListView;
    private Toolbar toolbar;
    private SharedPreferences prf;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Common.isInternetAvailable(this)) {
            setContentView(R.layout.activity_main);
            inIt();
            preenrencesData();
            dashbaordScreen();
        } else {
            setContentView(R.layout.error_layout);
            Common.InternetError(this);
        }

    }

    private void dashbaordScreen() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new HomeFragment());
        fragmentTransaction.commit();
    }

    private void preenrencesData() {
        prf = getSharedPreferences("fields", MODE_PRIVATE);
    }

    private void inIt() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        navigationExpandableListView = findViewById(R.id.expandable_navigation);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationExpandableListView
                .init(this)
                .addHeaderModel(new HeaderModel("Home", R.drawable.ic_user_circle, false))
                .addHeaderModel(new HeaderModel("ORDERS", R.drawable.ic_user_circle, true)
                                .addChildModel(new ChildModel("New Orders"))
                                .addChildModel(new ChildModel("Confirm Orders"))
                                .addChildModel(new ChildModel("Delivered Orders"))
                                .addChildModel(new ChildModel("Cancel Orders")))
                .addHeaderModel(new HeaderModel("MENU LIST", R.drawable.ic_user_circle, false))
                .addHeaderModel(new HeaderModel("DAILY OFFER", R.drawable.ic_user_circle, false))

                .addHeaderModel(new HeaderModel("CONTACT", R.drawable.ic_user_circle, false))
                .addHeaderModel(new HeaderModel("SETTING", R.drawable.ic_user_circle, false))
                .addHeaderModel(new HeaderModel("ABOUT US", R.drawable.ic_user_circle, false))
                .addHeaderModel(new HeaderModel("LOGOUT", R.drawable.ic_user_circle, false))
                .build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition);

//                        finalDrawer.closeDrawer(GravityCompat.START);
                        if (id == 0) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 2) {
                            Intent intent = new Intent(MainActivity.this, MenuListActivity.class);
                            startActivity(intent);
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 3) {
                            Intent intent = new Intent(MainActivity.this, DailyOfferActivity.class);
                            startActivity(intent);
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 4) {

                            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                            startActivity(intent);
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 5) {
                            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(intent);
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 6) {
                            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                            startActivity(intent);
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 7) {

                            SharedPreferences.Editor editor = prf.edit();
                            editor.clear();
                            editor.apply();
                            SaveSharedPreference.setLoggedIn(getApplicationContext(), false);

                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        return false;
                    }
                }).addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                navigationExpandableListView.setSelected(groupPosition, childPosition);

                Intent intent;

                if (childPosition == 0) {
                    intent = new Intent(MainActivity.this,NewOrderActivity.class);
                    startActivity(intent);

                } else if (childPosition == 1) {
                    intent = new Intent(MainActivity.this,ConfirmOrderActivity.class);
                    startActivity(intent);
                } else if (childPosition == 2) {
                    intent = new Intent(MainActivity.this,DeliveredOrderActivity.class);
                    startActivity(intent);
                } else if (childPosition == 3) {
                    intent = new Intent(MainActivity.this,CancelOrderActivity.class);
                    startActivity(intent);
                }
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        navigationExpandableListView.expandGroup(2);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}