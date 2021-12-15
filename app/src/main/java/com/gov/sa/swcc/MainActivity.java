package com.gov.sa.swcc;

import android.annotation.TargetApi;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    public  final Fragment home = new Home();
    public final Fragment services = new Services();
    public final Fragment login = new Login();
    public final FragmentManager fm = getSupportFragmentManager();
    public  Fragment active = home;


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
                    fm.beginTransaction().hide(active).show(login).commit();
                    active = login;
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        fm.beginTransaction().add(R.id.main_container, login, "3").hide(login).commit();
        fm.beginTransaction().add(R.id.main_container, services, "2").hide(services).commit();
        fm.beginTransaction().add(R.id.main_container,home, "1").commit();


        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
