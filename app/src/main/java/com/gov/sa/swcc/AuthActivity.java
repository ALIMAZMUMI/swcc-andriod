package com.gov.sa.swcc;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.biometric.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AuthActivity extends AppCompatActivity {
    private CancellationSignal cancellationSignal = null;

    // create an authenticationCallback
    private BiometricPrompt.AuthenticationCallback authenticationCallback;
Global global;
boolean finger=false;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        global=new Global(AuthActivity.this);

        authenticationCallback = new BiometricPrompt.AuthenticationCallback() {
            // here we need to implement two methods
            // onAuthenticationError and
            // onAuthenticationSucceeded If the
            // fingerprint is not recognized by the
            // app it will call onAuthenticationError
            // and show a toast
            @Override
            public void onAuthenticationError(
                    int errorCode, CharSequence errString)
            {
                super.onAuthenticationError(errorCode, errString);

if(errorCode==BiometricPrompt.BIOMETRIC_ERROR_CANCELED){
    Intent home=new Intent(AuthActivity.this,MainActivity.class);
                global.SaveValue("Home","N");
                global.SaveValue("Authentication","n");
                home.putExtra("Home","N");
                //MainActivity.changelayout(3);
                startActivity(home);
                AuthActivity.this.finish();
}

//
            }
            // If the fingerprint is recognized by the
            // app then it will call
            // onAuthenticationSucceeded and show a
            // toast that Authentication has Succeed
            // Here you can also start a new activity
            // after that


            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result)
            {
                super.onAuthenticationSucceeded(result);
                Intent home=new Intent(AuthActivity.this,MainActivity.class);
                global.SaveValue("Home","y");
                home.putExtra("Home","y");
                //MainActivity.changelayout(3);
                startActivity(home);
AuthActivity.this.finish();
                // or start a new Activity
            }
        };

        checkBiometricSupport();

                if(global.GetValue("Authentication").equals("Y")&&finger) {
                    Calendar calendar=Calendar.getInstance();
                    calendar.setTimeInMillis(global.getDate("Login"));
                    calendar.add(Calendar.DAY_OF_MONTH,10);
                    Calendar cu=Calendar.getInstance();

                    if(cu.getTimeInMillis()<=calendar.getTimeInMillis()){
                    // This creates a dialog of biometric
                    // auth and it requires title , subtitle
                    // , and description In our case there
                    // is a cancel button by clicking it, it
                    // will cancel the process of
                    // fingerprint authentication

                        BiometricPrompt biometricPrompt ;

                        if (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.P) {

                            biometricPrompt = new BiometricPrompt
                                    .Builder(getApplicationContext())
                                    .setTitle("تطبيق التحلية")
                                    .setSubtitle("")
                                    .setDescription("")
                                    .setNegativeButton("إلغاء",AuthActivity.this.getMainExecutor(), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .build();
                            biometricPrompt.authenticate(
                                    getCancellationSignal(),
                                    getMainExecutor(),
                                    authenticationCallback);
                        }else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                            biometricPrompt = new BiometricPrompt
                                    .Builder(getApplicationContext())
                                    .setTitle("تطبيق التحلية")
                                    .setSubtitle("")
                                    .setDescription("")
                                    .setDeviceCredentialAllowed(true)
                                    .build();
                            biometricPrompt.authenticate(
                                    getCancellationSignal(),
                                    getMainExecutor(),
                                    authenticationCallback);
                        }

                        // start the authenticationCallback in
                    // mainExecutor

                    }else {

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent home=new Intent(AuthActivity.this,MainActivity.class);
                                global.SaveValue("Home","N");
                                global.SaveValue("Authentication","n");
                                home.putExtra("Home","N");
                                //MainActivity.changelayout(3);
                                startActivity(home);
                                AuthActivity.this.finish();
                            }
                        }, 2000);
                    }
                }else if(finger){
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent home=new Intent(AuthActivity.this,MainActivity.class);
                            global.SaveValue("Home","N");
                            global.SaveValue("Authentication","n");
                            home.putExtra("Home","N");
                            //MainActivity.changelayout(3);
                            startActivity(home);
                            AuthActivity.this.finish();
                        }
                    }, 2000);
                }



    }

    @RequiresApi(Build.VERSION_CODES.M)
    private Boolean checkBiometricSupport()
    {
        KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
        if (!keyguardManager.isDeviceSecure()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent home=new Intent(AuthActivity.this,MainActivity.class);
                    global.SaveValue("Home","N");
                    global.SaveValue("Authentication","n");
                    home.putExtra("Home","N");
                    //MainActivity.changelayout(3);
                    startActivity(home);
                    AuthActivity.this.finish();
                }
            }, 2000);
            finger=false;
            return false;
        }else{
            finger=true;
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC)!= PackageManager.PERMISSION_GRANTED) {

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent home=new Intent(AuthActivity.this,MainActivity.class);
                    global.SaveValue("Home","N");
                    global.SaveValue("Authentication","n");
                    home.putExtra("Home","N");
                    //MainActivity.changelayout(3);
                    startActivity(home);
                    AuthActivity.this.finish();
                }
            }, 2000);

            finger=false;
            return false;
        }else{
            finger=true;
        }
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            finger=true;
            return true;
        }
        else{
            finger=true;
            return true;}
    }

    // it will be called when
    // authentication is cancelled by
    // the user
    private CancellationSignal getCancellationSignal()
    {
        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(
                new CancellationSignal.OnCancelListener() {
                    @Override public void onCancel()
                    {
                        global.SaveValue("Authentication","N");
                        global.SaveValue("Home","N");
                        Intent home=new Intent(AuthActivity.this,MainActivity.class);
                        startActivity(home);
                        AuthActivity.this.finish();


                    }
                });
        return cancellationSignal;
    }

    private void notifyUser(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}