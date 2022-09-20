package com.gov.sa.swcc;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.gov.sa.swcc.model.locationAttend.SWCCLocations;
import com.gov.sa.swcc.model.locationAttend.TransactionLocationData;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationAttendActivity extends AppCompatActivity implements OnMapReadyCallback {
//String testU = "U290550";
    private static final String TAG = MainActivity.class.getSimpleName();

    Global global;
    GoogleMap gMap;

    /**
     * Code used in requesting runtime permissions.
     */
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    /**
     * Constant used in the location settings dialog.
     */
    private static final int REQUEST_CHECK_SETTINGS = 0x1;

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    // Keys for storing activity state in the Bundle.
    private final static String KEY_REQUESTING_LOCATION_UPDATES = "requesting-location-updates";
    private final static String KEY_LOCATION = "location";
    private final static String KEY_LAST_UPDATED_TIME_STRING = "last-updated-time-string";

    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    /**
     * Provides access to the Location Settings API.
     */
    private SettingsClient mSettingsClient;

    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */
    private LocationRequest mLocationRequest;

    /**
     * Stores the types of location services the client is interested in using. Used for checking
     * settings to determine if the device has optimal location settings.
     */
    private LocationSettingsRequest mLocationSettingsRequest;

    /**
     * Callback for Location events.
     */
    private LocationCallback mLocationCallback;

    /**
     * Represents a geographical location.
     */
    private Location mCurrentLocation;

    Button attendBtn;
    TextView msgTextView;
    SWCCLocations swccLocations;
    TransactionLocationData transactionLocationData;
    CountDownTimer Timer;
    private boolean isInZoon = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_attend_activity);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        global = new Global(LocationAttendActivity.this);
        //TODO: test
//        global.SaveValue("Username","290404");

        msgTextView = findViewById(R.id.header3);
        msgTextView.setVisibility(View.GONE);

        attendBtn = findViewById(R.id.attendButton);
        setAttendBtn(false,"");
        attendBtn.setOnClickListener(v -> {
            InsertAttendance();
        });

        TextView Header = (TextView) findViewById(R.id.header);
        String text = "<font color=#004C86>خدمات الموارد البشرية /</font> <font color=#0066CC>تحضير بالموقع</font>";
        Header.setText(Html.fromHtml(text));

        ((Button) findViewById(R.id.close)).setOnClickListener(view -> {
            stopLocationUpdates();
            finish();
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);

        // Kick off the process of building the LocationCallback, LocationRequest, and
        // LocationSettingsRequest objects.
        createLocationCallback();
        createLocationRequest();
        buildLocationSettingsRequest();
        checkForAllowMockLocationsApps(LocationAttendActivity.this);
    }

    /**
     * API get swcc locations
     */
    private void getSWCCLocations(boolean drawcircle) {
//        String Token=global.GetValue("TaskToken");
        String UID=global.GetValue("Username");
        if(!UID.contains("u"))
        {
            UID="U"+UID;
        }
        //TODO: test
//        UID=testU;
        Call<SWCCLocations> call = RetrofitClient.getInstance(Api.LOCATION_ATT_URL).getMyApi().GetSWCCLocations("",UID);
//        PorgressDilog dialog =  new PorgressDilog(this);
//        dialog.show();
        call.enqueue(new Callback<SWCCLocations>() {
            @Override
            public void onResponse(Call<SWCCLocations> call, Response<SWCCLocations> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body() != null){
//                        dialog.dismiss();
                        swccLocations = response.body();
                        //TODO test
//                        swccLocations.Timeallowed = "1";//test

                        for (SWCCLocations.Location item:swccLocations.Locations) {
                            item.setData();
                            if(drawcircle) {
                                drawCircle(item.swccLatLng);
                                gMap.addMarker(new MarkerOptions()
                                        .position(item.swccLatLng)
                                        .title(item.LocationName));
                            }
                        }
                        if(isInZoon) {
                            if (swccLocations.Showbutton) {
                                setAttendBtn(true,"");
                            }else{
                                setAttendBtn(false,swccLocations.Mes);
                            }
                        }else{
                            setAttendBtn(false,swccLocations.Mes);
                        }
                    }else {
//                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {
//                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SWCCLocations>call, Throwable t) {
//                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

    private void setAttendBtn(boolean enabled,String text){
        attendBtn.setText(text.length()>0?text:"تسجيل دخول الكتروني");
        attendBtn.setAlpha(enabled ? 1f : 0.4f);
        attendBtn.setEnabled(enabled);
    }
    /**
     * post attendance
     */
    int allawedMints = 20;//default value updated from backend
    boolean isTimerRun = false;
    private void InsertAttendance() {

        String uid = "u"+global.GetValue("Username");
        if(mCurrentLocation == null)
            return;
        //TODO: send empolyee id here
//        uid = testU;
        Call<TransactionLocationData> call = RetrofitClient.getInstance(Api.LOCATION_ATT_URL).getMyApi().InsertAttendance("",mCurrentLocation.getLatitude()+"",mCurrentLocation.getLongitude()+"",uid,"in");
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<TransactionLocationData>() {
            @Override
            public void onResponse(Call<TransactionLocationData> call, Response<TransactionLocationData> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {
                    if(response.body() != null){
                        dialog.dismiss();
                        transactionLocationData = response.body();
                        try {
                            allawedMints = (int) Double.parseDouble(transactionLocationData.Timeallowed);
                        }catch (Exception ex){

                        }
                        //TODO: testing
//                        allawedMints = 1;//test
                        setAttendBtn(false,getCounterText(allawedMints*60*1000));
//                        attendBtn.setBackgroundResource(R.drawable.grayroundbtn);
//                        attendBtn.setEnabled(false);
//                        setCounterText(allawedMints*60*1000);

                        global.SaveValue("allawedMints", allawedMints + "");
                        Date d = Calendar.getInstance().getTime();
                        global.SaveValue("AttendTimerTime", d.getTime() + "");
                        startTimer(0);
                        global.ShowMessage(transactionLocationData.Mes);
                    }else {
                        dialog.dismiss();
//                        global.ShowMessage("");
                    }
                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TransactionLocationData>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }
        });
    }

    public boolean checkForAllowMockLocationsApps(Context context) {
        int count = 0;
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages =
                pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages) {
            try {
                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName,
                        PackageManager.GET_PERMISSIONS);

                // Get Permissions
                String[] requestedPermissions = packageInfo.requestedPermissions;

                if (requestedPermissions != null) {
                    for (int i = 0; i < requestedPermissions.length; i++) {
                        if (requestedPermissions[i]
                                .equals("android.permission.ACCESS_MOCK_LOCATION")
                                && !applicationInfo.packageName.equals(context.getPackageName())) {
                            count++;
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("Error", "Got exception " + e.getMessage());
            }
        }

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    private String getCounterText(long millsec){
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("SA"));
        c.setTimeInMillis(millsec);
        String str = String.format("%02d:%02d:%02d", c.get(Calendar.HOUR), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
//        String str = c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
//        Log.d("-----setText:",str);
//        attendBtn.setText(str);
        return str;
    }


    //google maps and locations
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gMap = googleMap;
        getSWCCLocations(true);
        //default value until get locations
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(24.6964447242478,46.6813158547335), 15.0f));
    }

    private void drawCircle(LatLng point) {
        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();
        // Specifying the center of the circle
        circleOptions.center(point);
        // Radius of the circle
        circleOptions.radius(500);
        // Border color of the circle
        circleOptions.strokeColor(0xff004C86);
        List<PatternItem> pattern = Arrays.<PatternItem>asList(new Dash(10),new Gap(5));
         circleOptions.strokePattern(pattern);
        // Fill color of the circle//004C86
        circleOptions.fillColor(0x20004C86);
        // Border width of the circle
        circleOptions.strokeWidth(5f);
        // Adding the circle to the GoogleMap
        gMap.addCircle(circleOptions);
    }

    /**
     * Sets up the location request. Android has two location request settings:
     * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These settings control
     * the accuracy of the current location. This sample uses ACCESS_FINE_LOCATION, as defined in
     * the AndroidManifest.xml.
     * <p/>
     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
     * interval (5 seconds), the Fused Location Provider API returns location updates that are
     * accurate to within a few feet.
     * <p/>
     * These settings are appropriate for mapping applications that show real-time location
     * updates.
     */
    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    /**
     * Creates a callback for receiving location events.
     */
    private void createLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                mCurrentLocation = locationResult.getLastLocation();

                if(mCurrentLocation.isFromMockProvider()){
                    global.ShowMessage("الرجاء تفعيل الموقع الفعلي");
                    finish();
                }
                if (ActivityCompat.checkSelfPermission(LocationAttendActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationAttendActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    gMap.setMyLocationEnabled(true);
                }

                //Move the camera to the user's location and zoom in!
                if(swccLocations != null && swccLocations.Locations!=null&&swccLocations.Locations.size()>0){
                    for (SWCCLocations.Location item:swccLocations.Locations) {
                        item.distanceFromMyLocation =mCurrentLocation . distanceTo(item.swccLocation);
                    }
                    Collections.sort(swccLocations.Locations, (o1, o2) -> {
                            return (int) (o1.distanceFromMyLocation - o2.distanceFromMyLocation);
                    });
                    //zoom to uer location
                    LatLng cl = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
                    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cl, 15.0f));

                    //zoom to nearest location
                    SWCCLocations.Location nsl = swccLocations.Locations.get(0);
//                    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(nsl.swccLatLng, 15.0f));
                    //inside the zoon

                    //Log.d("iuehfkwedh",global.checktimeattend(swccLocations.startTime,swccLocations.endTime,global.GetTimess(swccLocations.currentDatetime))+"");
                    if(nsl.distanceFromMyLocation < nsl.Distance && !isInZoon ){
                        isInZoon = true;
                        if(!isTimerRun)
                            getSWCCLocations(false);
                    }else{
                        isInZoon = false;
                    }
                }

            }
        };
    }

    /**
     * Uses a {@link LocationSettingsRequest.Builder} to build
     * a {@link LocationSettingsRequest} that is used for checking
     * if a device has the needed location settings.
     */
    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        break;
                    case Activity.RESULT_CANCELED:

                        //go back if user cancel location
                        //TODO: enable this
                        stopLocationUpdates();
                         this.finish();
                        break;
                }
                break;
        }
    }


    /**
     * Requests location updates from the FusedLocationApi. Note: we don't call this unless location
     * runtime permission has been granted.
     */
    private void startLocationUpdates() {
        // Begin by checking if the device has the necessary location settings.
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(LocationAttendActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);
                                Toast.makeText(LocationAttendActivity.this, errorMessage, Toast.LENGTH_LONG).show();
//                                mRequestingLocationUpdates = false;
                        }
                    }
                });
    }



    /**
     * Removes location updates from the FusedLocationApi.
     */
    private void stopLocationUpdates() {
//        if (!mRequestingLocationUpdates) {
//            Log.d(TAG, "stopLocationUpdates: updates never requested, no-op.");
//            return;
//        }

        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        mRequestingLocationUpdates = false;
//                        setButtonsEnabledState();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();

        //restart timer
        try {
            String m = global.GetValue("allawedMints");
            String t = global.GetValue("AttendTimerTime");

            if(m.length() > 0 && t.length()>0){
                allawedMints = Integer.parseInt(m);
                long diff = (new Date().getTime() - Long.parseLong(t));
                if(diff > 0 && diff< allawedMints *60*1000) {
                    setAttendBtn(false,getCounterText(diff));
//                    attendBtn.setBackgroundResource(R.drawable.grayroundbtn);
//                    attendBtn.setEnabled(false);
//                    setCounterText( diff);
                    startTimer(diff);
                }
            }
        }catch (Exception ex){

        }
        // Within {@code onPause()}, we remove location updates. Here, we resume receiving
        // location updates if the user has requested them.
        if (/*mRequestingLocationUpdates &&*/ checkPermissions()) {
            startLocationUpdates();
        } else if (!checkPermissions()) {
            requestPermissions();
        }
    }

    private void startTimer(long diff){
        if (isTimerRun && Timer != null)
            Timer.cancel();
        Timer = new CountDownTimer(allawedMints * 60 * 1000 - diff, 1000) {
            public void onTick(long millisUntilFinished) {
                setAttendBtn(false,getCounterText(millisUntilFinished));
//                attendBtn.setEnabled(false);
//                setCounterText(millisUntilFinished);
            }

            public void onFinish() {
                isTimerRun = false;
                getSWCCLocations(false);
//                                attendBtn.setEnabled(true);
//                if(swccLocations.Showbutton) {
//                    attendBtn.setText("تسجيل الحضور الكتروني");
//                    attendBtn.setEnabled(true);
//                    attendBtn.setAlpha(1.0f);
//                }
//                isTimerRun = false;
//                global.SaveValue("allawedMints", "");
//                global.SaveValue("AttendTimerTime", "");
            }
        };
        isTimerRun = true;
        Timer.start();
        msgTextView.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
        if(Timer != null){
            Timer.cancel();
        }
    }

    /**
     * Stores activity data in the Bundle.
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
//        savedInstanceState.putBoolean(KEY_REQUESTING_LOCATION_UPDATES, mRequestingLocationUpdates);
        savedInstanceState.putParcelable(KEY_LOCATION, mCurrentLocation);
//        savedInstanceState.putString(KEY_LAST_UPDATED_TIME_STRING, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Shows a {@link Snackbar}.
     *
     * @param  msg id for the string resource for the Snackbar text.
     * @param actionString   The text of the action item.
     * @param listener         The listener associated with the Snackbar action.
     */
    private void showSnackbar(final String msg, final String actionString,
                              View.OnClickListener listener) {
        Snackbar.make(
                findViewById(android.R.id.content),
                msg,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(actionString, listener).show();
    }

    /**
     * Return the current state of the permissions needed.
     */
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");
            showSnackbar(" الوصول للموقع الجغرافي",
                    "تفعيل", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(LocationAttendActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    });
        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            ActivityCompat.requestPermissions(LocationAttendActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                if (mRequestingLocationUpdates) {
//                    Log.i(TAG, "Permission granted, updates requested, starting location updates");
//                    startLocationUpdates();
//                }
                startLocationUpdates();
            } else {
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
                showSnackbar("يجب تفعيل خدمة الموقع الجغرافي",
                        "الاعدادات", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
                stopLocationUpdates();

            }
        }
    }


}