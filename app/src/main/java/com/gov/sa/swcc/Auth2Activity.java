package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Auth2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth2);





                if(new DeviceUtils().isDeviceRooted()){
                    Global global=new Global(Auth2Activity.this);
                    global.ShowMessage("عفوا التطبيق غير مدعوم للعمل على الجهاز الخاص بك.");
                }else {

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                        // Do something for lollipop and above versions

                         //startActivity(new Intent(getApplicationContext(), MyTaskActivity.class));
                        //startActivity(new Intent(getApplicationContext(), ApprovalActivity.class));

                         startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                        Auth2Activity.this.finish();
                    } else {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                Auth2Activity.this.finish();
                            }
                        }, 5000);
                    }

                }




    }
}