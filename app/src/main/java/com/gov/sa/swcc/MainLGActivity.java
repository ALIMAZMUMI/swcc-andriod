package com.gov.sa.swcc;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainLGActivity extends AppCompatActivity {
    private TextView mTextMessage;
    public static  final Fragment home = new HomeInfo();
    public static final Fragment services = new ServicesLGFragment();
    public static final Fragment myworkFragment = new MyworkFragment();
    public static  FragmentManager fm =null ;

    public static  Fragment active = home;
ImageView sidemeun;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {




        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(home).commit();
                    active = home;
                    return true;

                case R.id.navigation_service:
                    fm.beginTransaction().hide(active).show(services).commit();
                    active = services;
                    return true;

                case R.id.navigation_login:

                        fm.beginTransaction().hide(active).show(myworkFragment).commit();
                        active = myworkFragment;

                    return true;

            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlg);
        Global global=new Global(MainLGActivity.this);

        fm=getSupportFragmentManager();
        sidemeun=(ImageView)findViewById(R.id.sidemeun);
        sidemeun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent side=new Intent(MainLGActivity.this,SideMenuActivity.class);
                startActivity(side);
            }
        });


    fm.beginTransaction().add(R.id.main_container, myworkFragment, "3").hide(myworkFragment).commit();
    fm.beginTransaction().add(R.id.main_container, services, "2").hide(services).commit();
    fm.beginTransaction().add(R.id.main_container, home, "1").show(home).commit();
    active = home;


        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);

    }




}
