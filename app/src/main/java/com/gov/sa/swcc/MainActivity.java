package com.gov.sa.swcc;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    public static  final Fragment home = new Home();
    public static final Fragment services = new Services();
    public static final Fragment login = new Login();
    public static final Fragment otp = new Otp();
    public static final Fragment homeInfo = new HomeInfo();
    public static  FragmentManager fm =null ;

    public static  Fragment active = home;
    public static int Fragmentid=0;

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

                    if(Fragmentid==1) {
                        fm.beginTransaction().hide(active).show(otp).commit();
                        active = otp;
                    }else if(Fragmentid==2){
                        fm.beginTransaction().hide(active).show(homeInfo).commit();
                        active = homeInfo;
                    }else{
                        fm.beginTransaction().hide(active).show(login).commit();
                        active = login;
                    }
                    return true;

            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fm=getSupportFragmentManager();
        sidemeun=(ImageView)findViewById(R.id.sidemeun);
        sidemeun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SideMenuActivity.class));
            }
        });

        fm.beginTransaction().add(R.id.main_container, login, "3").hide(login).commit();
        fm.beginTransaction().add(R.id.main_container, services, "2").hide(services).commit();
        fm.beginTransaction().add(R.id.main_container,home, "1").commit();
        fm.beginTransaction().add(R.id.main_container,homeInfo, "4").hide(homeInfo).commit();
        fm.beginTransaction().add(R.id.main_container,otp, "5").hide(otp).commit();


        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }



    public static void changelayout(int id){

        Fragmentid=id;
        if(Fragmentid==1) {
            fm.beginTransaction().hide(active).show(otp).commit();
            active = otp;
        }else if(Fragmentid==2){
            fm.beginTransaction().hide(active).show(homeInfo).commit();
            active = homeInfo;
        }else{
            fm.beginTransaction().hide(active).show(login).commit();
            active = login;
        }

    }

}
