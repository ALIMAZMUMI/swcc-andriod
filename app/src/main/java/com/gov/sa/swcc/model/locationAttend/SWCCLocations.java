package com.gov.sa.swcc.model.locationAttend;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class SWCCLocations {


    @SerializedName("Locations")
    @Expose
    public ArrayList<Location> Locations;
    @SerializedName("currentDatetime")
    @Expose
    public String currentDatetime;
    @SerializedName("Timeallowed")
    @Expose
    public String Timeallowed;

    @SerializedName("startTime")
    @Expose
    public String startTime;

    @SerializedName("endTime")
    @Expose
    public String endTime;


    @SerializedName("Mes")
    @Expose
    public String Mes;

    @SerializedName("Showbutton")
    @Expose
    public boolean Showbutton;

    public class Location{
        @SerializedName("Id")
        @Expose
        public Integer Id;
        @SerializedName("latitude")
        @Expose
        public Double latitude;
        @SerializedName("longitude")
        @Expose
        public Double longitude;
        @SerializedName("Distance")
        @Expose
        public Integer Distance;
        @SerializedName("LocationName")
        @Expose
        public String LocationName;

        //not in json
        public float distanceFromMyLocation;
        public android.location.Location swccLocation;
        public LatLng swccLatLng;
        public void setData(){
            swccLocation = new android.location.Location(LocationName);
            swccLocation.setLatitude(latitude);
            swccLocation.setLongitude(longitude);

            swccLatLng = new LatLng(latitude,longitude);
        }
    }

}
