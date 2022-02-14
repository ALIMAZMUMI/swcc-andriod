package com.gov.sa.swcc;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.gov.sa.swcc.Adapter.GridAdapter;
import com.gov.sa.swcc.Adapter.SliderAdapter;
import com.gov.sa.swcc.model.GridItem;
import com.gov.sa.swcc.model.PersonalResult;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeInfo extends Fragment {


    public HomeInfo() {
    }
TextView Emppic,EmpName,EmpJob;
    Global global;
    GridView gridView;
    GridAdapter adapter;
    CardView idCard,detials,Transactions
            ,sal_cer,leave,salmonth,ITCom,EskanService,IndustrialSecurity,HrRequest,InsuranceInfo
            ,Sharkhom,EmpCard,photolip,Proplan,libiry,logout;



    TextView Empdept,EmpID,EmpNat,EmpNatID,EmpMobile,JobTitle,down,badge;
    ArrayList<GridItem> birdList;

    LinearLayout moreinfo;
    int height,width;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home_info, container, false);



        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = view.findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(R.drawable.slide11));
        sliderDataArrayList.add(new SliderData(R.drawable.slide22));
        sliderDataArrayList.add(new SliderData(R.drawable.slide33));

        // passing this array list inside our adapter class.


        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();




        ((ImageView)view.findViewById(R.id.showQr)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),QRCodeActivity.class));
            }
        });


        global=new Global(getContext());
        Emppic=(TextView)view.findViewById(R.id.Emppic);
        EmpName=(TextView)view.findViewById(R.id.EmpName);
        EmpJob=(TextView)view.findViewById(R.id.EmpJob);


        Empdept=(TextView)view.findViewById(R.id.Empdept);
        EmpID=(TextView)view.findViewById(R.id.EmpID);
        EmpNat=(TextView)view.findViewById(R.id.EmpNat);
        EmpNatID=(TextView)view.findViewById(R.id.EmpNatID);
        JobTitle=(TextView)view.findViewById(R.id.JobTitle);
        EmpMobile=(TextView)view.findViewById(R.id.EmpMobile);
        down=(TextView)view.findViewById(R.id.down);
        moreinfo=(LinearLayout) view.findViewById(R.id.moreinfo);
        badge=(TextView)view.findViewById(R.id.badge);


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(moreinfo.getVisibility()==moreinfo.VISIBLE){
                    moreinfo.setVisibility(View.GONE);
                    //down.setRotationY(180);
                    down.setBackgroundResource(R.drawable.dropdown);
                }else {
                    moreinfo.setVisibility(View.VISIBLE);
                   // down.setRotationY(180);
                    down.setBackgroundResource(R.drawable.up);

                }
            }
        });

        PersonalResult per=global.GetPData("PersonalResult");

        String text = "<font color=#000000>الادارة: </font> <font color=#0066CC> "+per.getResultObject().getDepartment()+"</font>";
        Empdept.setText(Html.fromHtml(text));

//        text = "<font color=#000000>الهوية الوطنية:</font> <font color=#0066CC> "+per.getResultObject().getNationalId()+"</font>";
//        EmpID.setText(Html.fromHtml(text));

         text = "<font color=#000000>الجنسية:</font> <font color=#0066CC> "+per.getResultObject().getNationality()+"</font>";
        EmpNat.setText(Html.fromHtml(text));

        text = "<font color=#000000>الهوية الوطنية:</font> <font color=#0066CC> "+per.getResultObject().getNationalId()+"</font>";
        EmpNatID.setText(Html.fromHtml(text));

         text = "<font color=#000000>المسمى الوظيفي:</font> <font color=#0066CC> "+per.getResultObject().getTitle()+"</font>";
        JobTitle.setText(Html.fromHtml(text));
        text = "<font color=#000000>رقم الجوال:</font> <font color=#0066CC> "+per.getResultObject().getMobile()+"</font>";
        EmpMobile.setText(Html.fromHtml(text));



        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        birdList = new ArrayList<GridItem>();


        SliderAdapter adapter1 = new SliderAdapter(getContext(), sliderDataArrayList,width,width);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        // sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        sliderView.setSliderAdapter(adapter1);

if(global.GetValue("HRFav").contains("HR3")) {
    birdList.add(new GridItem("الحضور و الإنصراف", R.drawable.checkin,"HR3"));
}
if(global.GetValue("HRFav").contains("HR2")) {
            birdList.add(new GridItem("مسيّر الراتب",R.drawable.profile,"HR2"));
        }
        if(global.GetValue("HRFav").contains("HR1")) {
            birdList.add(new GridItem("الاجازات",R.drawable.leave,"HR1"));
        }
        if(global.GetValue("HRFav").contains("HR6")) {
            birdList.add(new GridItem("التعريف بالراتب",R.drawable.salary,"HR6"));
        }
        if(global.GetValue("HRFav").contains("HR5")) {
            birdList.add(new GridItem("البحث عن العاملين",R.drawable.searchicon,"HR5"));
        }
        if(global.GetValue("HRFav").contains("HR4")) {
            birdList.add(new GridItem("التأمين الصحي",R.drawable.insur,"HR4"));
        }
        if(global.GetValue("TEFav").contains("TE1")) {
            birdList.add(new GridItem("تقنية المعلومات",R.drawable.iticon,"TE1"));
        }

        if(global.GetValue("TEFav").contains("TE2")) {
            birdList.add(new GridItem("العناية بالعاملين",R.drawable.hricon,"TE2"));
        }

        if(global.GetValue("TEFav").contains("TE3")) {
            birdList.add(new GridItem("الملاحظات والبلاغات",R.drawable.complintnote,"TE3"));
        }






        birdList.add(new GridItem("AddItem",R.drawable.plus,""));


        adapter=new GridAdapter(getContext(),R.layout.griditem,birdList,width,height,0);
        gridView=(GridView)view.findViewById(R.id.servicegrid);
        gridView.setAdapter(adapter);
        getHeight(adapter,gridView);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Animation animZoomIn = AnimationUtils.loadAnimation(getActivity(),R.anim.zoom_in);
                Animation animZoomOut = AnimationUtils.loadAnimation(getActivity(),R.anim.zoom_out);

                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);

                        if(birdList.get(i).getType().contains("HR3")) {
                            startActivity(new Intent(getActivity(),TransactionsActivity.class));
                        }
                        if(birdList.get(i).getType().contains("HR2")) {
                            startActivity(new Intent(getActivity(),PayslipActivity.class));
                        }
                        if(birdList.get(i).getType().contains("HR1")) {
                            startActivity(new Intent(getActivity(),LeaveActivity.class));
                        }
                        if(birdList.get(i).getType().contains("HR6")) {
                            startActivity(new Intent(getActivity(),EmployeeIdentificationActivity.class));
                        }
                        if(birdList.get(i).getType().contains("HR5")) {
                            startActivity(new Intent(getActivity(),EmpSearchActivity.class));
                        }
                        if(birdList.get(i).getType().contains("HR4")) {
                            startActivity(new Intent(getActivity(),InsuranceInfoActivity.class));
                        }
                        if(birdList.get(i).getType().contains("TE3")) {
                            startActivity(new Intent(getActivity(),OpenTeqActivity.class));
                        }
                        if(birdList.get(i).getType().contains("TE2")) {
                            startActivity(new Intent(getActivity(),HrRequestActivity.class));
                        }
                        if(birdList.get(i).getType().contains("TE1")) {
                            startActivity(new Intent(getActivity(),ITComActivity.class));
                        }
                        if(birdList.get(i).getServiceName().contains("AddItem")){
                            startActivityForResult(new Intent(getActivity(),FaveroitActivity.class),1001);
                        }

//                        if(i==0){
//                            startActivity(new Intent(getActivity(),FaveroitActivity.class));
//
//                        }else if(i==0){
//                            startActivity(new Intent(getActivity(),EmpCardActivity.class));
//
//                        }
//                        else if(i==1){
//                            startActivity(new Intent(getActivity(),JobDetailsActivity.class));
//                        }
//                        else if(i==2){
//                            startActivity(new Intent(getActivity(),TransactionsActivity.class));
//                        }
//                        else if(i==3){
//                            startActivity(new Intent(getActivity(),EmployeeIdentificationActivity.class));
//                        }
//                        else if(i==4){
//                            startActivity(new Intent(getActivity(),LeaveActivity.class));
//                        }
//                        else if(i==5){
//                            startActivity(new Intent(getActivity(),PayslipActivity.class));
//                        }
//                        else if(i==6){
//                            startActivity(new Intent(getActivity(),EmpSearchActivity.class));
//                        }
//                        else if(i==7){
//                            startActivity(new Intent(getActivity(),EskanServiceActivity.class));
//                        }
//                        else if(i==8){
//                            startActivity(new Intent(getActivity(),ITComActivity.class));
//                        }
//                        else if(i==9){
//                            startActivity(new Intent(getActivity(),IndustrialSecurityActivity.class));
//                        }
//                        else if(i==10){
//                            startActivity(new Intent(getActivity(),HrRequestActivity.class));
//                        }
//                        else if(i==11){
//                            startActivity(new Intent(getActivity(),InsuranceInfoActivity.class));
//                        }
//                        else if(i==12){
//                            startActivity(new Intent(getActivity(),QRCodeActivity.class));
//                        }
//                        else if(i==13){
//                            Intent Link=new Intent(getActivity(),ShowLinkActivity.class);
//                        Link.putExtra("URL_LINK","https://ext.swcc.gov.sa/elib");
//                        Link.putExtra("Auth","T");
//                        Link.putExtra("Share","0");
//                        startActivity(Link);
//                        }
//                        else if(i==14){
//                            startActivity(new Intent(getActivity(),Pro_planningActivity.class));
//                        }
//                        else if(i==15){
//                            Intent Link=new Intent(getActivity(),ShowLinkActivity.class);
//                        Link.putExtra("URL_LINK","https://ext.swcc.gov.sa/news");
//                        Link.putExtra("Auth","T");
//                        Link.putExtra("Share","0");
//                        startActivity(Link);
//                        }
//                        else if(i==16){
//                            startActivity(new Intent(getActivity(),TrainingActivity.class));
//
//                        }
//                        else if(i==18){
//                            global.SaveValue("Home","N");
//                global.SaveValue("Authentication","YY");
//                global.SaveValue("Username","");
//                global.SaveValue("Password","");
//                Bundle bundle = new Bundle();
//                MainActivity.login.setArguments(bundle);
//                MainActivity.Home="n";
//                MainActivity.changelayout(0);
//                        }else if(i==17){
//
//                            startActivity(new Intent(getActivity(),SharekMenuActivity.class));
//
//
//
//                        }


                    }
                }, 150);


            }
        });

//        sal_cer=(CardView)view.findViewById(R.id.sal_cer);
//        leave=(CardView)view.findViewById(R.id.leave);
//        Transactions=(CardView)view.findViewById(R.id.Transactions);
//
//        idCard=(CardView)view.findViewById(R.id.idCard);
//        detials=(CardView)view.findViewById(R.id.detials);
//        salmonth=(CardView)view.findViewById(R.id.salmonth);
//        EskanService=(CardView)view.findViewById(R.id.EskanService);
//        IndustrialSecurity=(CardView)view.findViewById(R.id.IndustrialSecurity);
//        HrRequest=(CardView)view.findViewById(R.id.HrRequest);
//        InsuranceInfo=(CardView)view.findViewById(R.id.InsuranceInfo);
//        Sharkhom=(CardView)view.findViewById(R.id.Sharkhom);
//        EmpCard=(CardView)view.findViewById(R.id.EmpCard);
////photolip,Proplan,libiry,logout;
//        photolip=(CardView)view.findViewById(R.id.photolip);
//        Proplan=(CardView)view.findViewById(R.id.Proplan);
//        libiry=(CardView)view.findViewById(R.id.libiry);
//        logout=(CardView)view.findViewById(R.id.logout);
//
//        ITCom=(CardView)view.findViewById(R.id.ITCom);


       try {
            per=global.GetPData("PersonalResult");
           EmpName.setText(per.getResultObject().getFullName());
           EmpJob.setText(global.GetValue("Username"));
           badge.setText(global.GetValue("Username"));

           byte[] decodedString = Base64.decode(per.getResultObject().getPhoto(), Base64.DEFAULT);
           Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
           Drawable d = new BitmapDrawable(getResources(), decodedByte);
           Emppic.setBackground(d);
       }catch (Exception e){

       }






        Animation animZoomIn = AnimationUtils.loadAnimation(getActivity(),R.anim.zoom_in);
        Animation animZoomOut = AnimationUtils.loadAnimation(getActivity(),R.anim.zoom_out);

//        idCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),EmpCardActivity.class));
//                    }
//                }, 150);
//
//            }
//        });
//        detials.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),JobDetailsActivity.class));
//                    }
//                }, 150);
//            }
//        });
//        Transactions.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),TransactionsActivity.class));
//                    }
//                }, 150);
//
//            }
//        });
//
//        sal_cer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),EmployeeIdentificationActivity.class));
//                    }
//                }, 150);
//
//            }
//        });
//        leave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),LeaveActivity.class));
//                    }
//                }, 150);
//
//            }
//        });
//        salmonth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),PayslipActivity.class));
//                    }
//                }, 150);
//            }
//        });
//
//        ITCom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),ITComActivity.class));
//                    }
//                }, 150);
//            }
//        });
//
//        EskanService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),EskanServiceActivity.class));
//                    }
//                }, 150);
//            }
//        });
//
//
//        IndustrialSecurity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),IndustrialSecurityActivity.class));
//                    }
//                }, 150);
//            }
//        });
//
//        HrRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),HrRequestActivity.class));
//                    }
//                }, 150);
//            }
//        });
//        InsuranceInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),InsuranceInfoActivity.class));
//                    }
//                }, 150);
//            }
//        });
//
//
//        Sharkhom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//
//                        Intent Link=new Intent(getActivity(),ShowLinkActivity.class);
//                        Link.putExtra("URL_LINK","https://ext.swcc.gov.sa/news");
//                        Link.putExtra("Auth","T");
//                        Link.putExtra("Share","0");
//                        startActivity(Link);
//                    }
//                }, 150);
//
//            }
//        });
//
//        EmpCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),QRCodeActivity.class));
//                    }
//                }, 150);
//            }
//        });
//        //photolip,Proplan,libiry,logout;
//        photolip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        Intent Link=new Intent(getActivity(),ShowLinkActivity.class);
//                        Link.putExtra("URL_LINK","https://ext.swcc.gov.sa/elib");
//                        Link.putExtra("Auth","T");
//                        Link.putExtra("Share","0");
//                        startActivity(Link);                    }
//                }, 150);
//            }
//        });
//        Proplan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),Pro_planningActivity.class));
//                    }
//                }, 150);
//            }
//        });
//        libiry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                view.startAnimation(animZoomIn);
//
//                final Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        view.startAnimation(animZoomOut);
//                        startActivity(new Intent(getActivity(),TrainingActivity.class));
//                    }
//                }, 150);
//            }
//        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                global.SaveValue("Home","N");
//                global.SaveValue("Authentication","YY");
//                global.SaveValue("Username","");
//                global.SaveValue("Password","");
//                Bundle bundle = new Bundle();
//                MainActivity.login.setArguments(bundle);
//                MainActivity.changelayout(0);
////
//            }
//        });
//
//
//
//
//
        Log.d("Token++",MyFirebaseMessagingService.getToken(getActivity()));
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Token+_+_", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        Log.d("Token+++",token);

                    }
                });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        birdList = new ArrayList<GridItem>();


        if(global.GetValue("HRFav").contains("HR3")) {
            birdList.add(new GridItem("الحضور و الإنصراف", R.drawable.checkin,"HR3"));
        }
        if(global.GetValue("HRFav").contains("HR2")) {
            birdList.add(new GridItem("مسيّر الراتب",R.drawable.profile,"HR2"));
        }
        if(global.GetValue("HRFav").contains("HR1")) {
            birdList.add(new GridItem("الاجازات",R.drawable.leave,"HR1"));
        }
        if(global.GetValue("HRFav").contains("HR6")) {
            birdList.add(new GridItem("التعريف بالراتب",R.drawable.salary,"HR6"));
        }
        if(global.GetValue("HRFav").contains("HR5")) {
            birdList.add(new GridItem("البحث عن العاملين",R.drawable.searchicon,"HR5"));
        }
        if(global.GetValue("HRFav").contains("HR4")) {
            birdList.add(new GridItem("التأمين الصحي",R.drawable.insur,"HR4"));
        }
        if(global.GetValue("TEFav").contains("TE3")) {
            birdList.add(new GridItem("الملاحظات والبلاغات",R.drawable.complintnote,"TE3"));
        }
        if(global.GetValue("TEFav").contains("TE2")) {
            birdList.add(new GridItem("العناية بالعاملين",R.drawable.hricon,"TE2"));
        }
        if(global.GetValue("TEFav").contains("TE1")) {
            birdList.add(new GridItem("تقنية المعلومات",R.drawable.iticon,"TE1"));
        }
        birdList.add(new GridItem("AddItem",R.drawable.idcard,""));

        GridAdapter adapter=new GridAdapter(getContext(),R.layout.griditem,birdList,width,height,0);
        gridView.setAdapter(adapter);
        getHeight(adapter,gridView);
    }

    public void getHeight(GridAdapter listadp, GridView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i < listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            i=i+2;
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getVerticalSpacing() * ((int)(listadp.getCount()/3)))+100;
        listview.setLayoutParams(params);
        listview.requestLayout();
    }

    public void update(){

        try {
            PersonalResult per=new Global(getActivity()).GetPData("PersonalResult");
            EmpName.setText(per.getResultObject().getFullName());
            EmpJob.setText(per.getResultObject().getTitle());
            byte[] decodedString = Base64.decode(per.getResultObject().getPhoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Drawable d = new BitmapDrawable(getResources(), decodedByte);
            Emppic.setBackground(d);
        }catch (Exception e){

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            PersonalResult per=global.GetPData("PersonalResult");
            EmpName.setText(per.getResultObject().getFullName());
            EmpJob.setText(per.getResultObject().getTitle());
            byte[] decodedString = Base64.decode(per.getResultObject().getPhoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Drawable d = new BitmapDrawable(getResources(), decodedByte);
            Emppic.setBackground(d);
        }catch (Exception e){

        }
    }
}
