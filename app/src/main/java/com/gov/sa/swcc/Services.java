package com.gov.sa.swcc;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;

import com.gov.sa.swcc.Adapter.GridAdapter;
import com.gov.sa.swcc.Adapter.GridBAdapter;
import com.gov.sa.swcc.Adapter.GridSAdapter;
import com.gov.sa.swcc.Adapter.SliderAdapter;
import com.gov.sa.swcc.model.GridItem;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Services extends Fragment {



    public Services() {
        // Required empty public constructor
    }

    CardView proplan,Eskan,Trinning,research0,research1,research2,research3
            ,camputerper,car,cardpercontractor,cameraper,returnper,cardper,docment,truck;
    int height,width;
    SliderView sliderView;
    ArrayList<SliderData> sliderDataArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_services, container, false);





      sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        sliderView = view.findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(R.drawable.slide11));
        sliderDataArrayList.add(new SliderData(R.drawable.slide22));
        sliderDataArrayList.add(new SliderData(R.drawable.slide33));

        // passing this array list inside our adapter class.






        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
       // sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);


        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();



        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
         height = displayMetrics.heightPixels;
         width = displayMetrics.widthPixels;
Log.d("wksdjhbfsilkf",width+"");
//        proplan=(CardView)view.findViewById(R.id.proplan);
//        Eskan=(CardView)view.findViewById(R.id.Eskan);
//        Trinning=(CardView)view.findViewById(R.id.Trinning);
//        research0=(CardView)view.findViewById(R.id.research0);
//        research1=(CardView)view.findViewById(R.id.research1);
//        research2=(CardView)view.findViewById(R.id.research2);
//        research3=(CardView)view.findViewById(R.id.research3);
//        camputerper=(CardView)view.findViewById(R.id.camputerper);
//        car=(CardView)view.findViewById(R.id.car);
//        cardpercontractor=(CardView)view.findViewById(R.id.cardpercontractor);
//        cameraper=(CardView)view.findViewById(R.id.cameraper);
//        returnper=(CardView)view.findViewById(R.id.returnper);
//        cardper=(CardView)view.findViewById(R.id.cardper);
//        docment=(CardView)view.findViewById(R.id.docment);
//        truck=(CardView)view.findViewById(R.id.truck);



        ArrayList<GridItem> birdList = new ArrayList<GridItem>();
        birdList.add(new GridItem("أكاديمية المياه",R.drawable.training));

        birdList.add(new GridItem("خدمات الإسكان",R.drawable.alarm));

        birdList.add(new GridItem("بيانات الانتاج",R.drawable.proplan));

        birdList.add(new GridItem("تسليم الوثائق الامنية",R.drawable.hrimage));


        GridBAdapter adapter=new GridBAdapter(getContext(),R.layout.griditem,birdList,width,height);
        GridView gridView=(GridView)view.findViewById(R.id.servicegrid1);
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

                        if(i==2){
                            startActivity(new Intent(getActivity(),Pro_planningActivity.class));

                        }
                        else if(i==1){
                            startActivity(new Intent(getActivity(),EskanServiceoutActivity.class));
                        }
                        else if(i==0){
                            startActivity(new Intent(getActivity(),TrainingActivity.class));
                        }
                        else if(i==3){
                            startActivity(new Intent(getActivity(),ReturnDocmentsActivity.class));
                        }
                    }}, 150);
            }
        });

        ArrayList<GridItem> birdList2 = new ArrayList<GridItem>();
        birdList2.add(new GridItem("Arabian Environments Research",R.drawable.research2));

        birdList2.add(new GridItem("SWRO Suitability",R.drawable.research3));
        birdList2.add(new GridItem("Water Treatment",R.drawable.research0));

        birdList2.add(new GridItem("Cooling Water Chlorination",R.drawable.research1));


        GridBAdapter adapter2=new GridBAdapter(getContext(),R.layout.griditem,birdList2,width,height);
        GridView gridView2=(GridView)view.findViewById(R.id.servicegrid2);
        gridView2.setAdapter(adapter2);
        getHeight(adapter2,gridView2);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                        if(i==2){
                            Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","https://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Prospects%20for%20improving%20the%20performance%20of%20SWRO%20plants%20by%20implementing%20advanced%20NFRO%20techniques.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
                        }
                        else if(i==1){

                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Evaluating%20suitability%20of%20source%20water%20for%20a%20proposed%20SWRO%20plant%20location.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
                        }
                        else if(i==0){

                                            Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Perspective%20on%20desalination%20discharges%20and%20coastal%20environments%20of%20the%20Arabian%20Peninsula.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);

                        }
                        else if(i==3){
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Effect%20of%20cooling%20water%20chlorination%20on%20entrained%20selected%20copepods%20species.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
                        }
                    }}, 150);
            }
        });






        ArrayList<GridItem> birdList3 = new ArrayList<GridItem>();
        birdList3.add(new GridItem("تصريح دخول جهاز حاسب آلي محمول",R.drawable.suitcase_));
        birdList3.add(new GridItem("إصدار تصريح سيارة مقاول",R.drawable.car));
        birdList3.add(new GridItem("إصدار بطاقة مقاول دائم - مؤقت",R.drawable.idcard));

        birdList3.add(new GridItem("نموذج طلب تصوير",R.drawable.camera));
        birdList3.add(new GridItem("نموذج إعادة بطاقات وتصاريح المركبات",R.drawable.profile));
        birdList3.add(new GridItem("تعهد بالمحافظة على البطاقات و التصاريح - مقاول",R.drawable.edit));

       // birdList3.add(new GridItem("تسليم الوثائق الامنية",R.drawable.hrimage));
        birdList3.add(new GridItem("تصريح مواد أو معدات",R.drawable.truck_));

        GridSAdapter adapter3=new GridSAdapter(getContext(),R.layout.griditem,birdList3,width,height,1);
        GridView gridView3=(GridView)view.findViewById(R.id.servicegrid3);
        gridView3.setAdapter(adapter3);
        getHeightLi(adapter3,gridView3);
        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                        if(i==0){
                            Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Lap.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
                        }
                        else if(i==1){
                                            Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="
                        +"https://www.swcc.gov.sa/uploads/CarSup.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
                        }
                        else if(i==2){

                            Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
                        "https://www.swcc.gov.sa/uploads/IDSup.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
                        }
                        else if(i==3){
                            Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
                        "https://www.swcc.gov.sa/uploads/PhotoReq.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
                        }
                        else if(i==4){
                            Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
                        "https://www.swcc.gov.sa/uploads/IDSupRe.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
                        }
                        else if(i==5){
                            Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                            Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
                                    "https://www.swcc.gov.sa/uploads/IDSupLi.pdf");
                            Link.putExtra("Auth","N");
                            Link.putExtra("Share","1");
                            startActivity(Link);
                        }
                        else if(i==10){
                            startActivity(new Intent(getActivity(),ReturnDocmentsActivity.class));

                        }else if(i==6){
                            Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
                        "https://www.swcc.gov.sa/uploads/Material-GatepassSup.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);

                        }
                    }}, 150);
            }
        });

//        proplan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),Pro_planningActivity.class));
//            }
//        });
//        Eskan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),EskanServiceoutActivity.class));
//            }
//        });
//        research0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","https://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Prospects%20for%20improving%20the%20performance%20of%20SWRO%20plants%20by%20implementing%20advanced%20NFRO%20techniques.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);
//            }
//        });
//        research1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Evaluating%20suitability%20of%20source%20water%20for%20a%20proposed%20SWRO%20plant%20location.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);            }
//        });
//        research2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Perspective%20on%20desalination%20discharges%20and%20coastal%20environments%20of%20the%20Arabian%20Peninsula.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);            }
//        });
//        research3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Effect%20of%20cooling%20water%20chlorination%20on%20entrained%20selected%20copepods%20species.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);            }
//        });
//        camputerper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Lap.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);             }
//        });
//        car.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="
//                        +"https://www.swcc.gov.sa/uploads/CarSup.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);                  }
//        });
//        cardpercontractor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
//                        "https://www.swcc.gov.sa/uploads/IDSup.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);
//            }
//        });
//        cameraper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
//                        "https://www.swcc.gov.sa/uploads/PhotoReq.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);
//            }
//        });
//        returnper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
//                        "https://www.swcc.gov.sa/uploads/IDSupRe.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);
//            }
//        });
//        cardper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
//                        "https://www.swcc.gov.sa/uploads/IDSupLi.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);
//            }
//        });
//        truck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
//                        "https://www.swcc.gov.sa/uploads/Material-GatepassSup.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);
//            }
//        });
//
//
//
////        proplan.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
////                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Lap.pdf");
////                Link.putExtra("Auth","N");
////                Link.putExtra("Share","1");
////                startActivity(Link);
////            }
////        });
//        Trinning.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),TrainingActivity.class));
//            }
//        });
//        docment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),ReturnDocmentsActivity.class));
//            }
//        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        sliderView.post(new Runnable() {
            @Override
            public void run() {
                SliderAdapter adapter1 = new SliderAdapter(getContext(), sliderDataArrayList,width,150);
                sliderView.setSliderAdapter(adapter1);
            }
        });
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
        params.height = totalHeight + (listview.getVerticalSpacing() * ((int)(listadp.getCount()/3)+1));
        listview.setLayoutParams(params);
        listview.requestLayout();
    }

    public void getHeight(GridBAdapter listadp, GridView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i < listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            i=i+2;
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getVerticalSpacing() * ((int)(listadp.getCount()/3)));
        listview.setLayoutParams(params);
        listview.requestLayout();
    }

    public void getHeight(GridSAdapter listadp, GridView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i < listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            i=i+2;
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getVerticalSpacing() * ((int)(listadp.getCount()/3)));
        listview.setLayoutParams(params);
        listview.requestLayout();
    }


    public void getHeightLi(GridSAdapter listadp, GridView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i < listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getVerticalSpacing() * ((int)(listadp.getCount())));
        listview.setLayoutParams(params);
        listview.requestLayout();
    }
}
