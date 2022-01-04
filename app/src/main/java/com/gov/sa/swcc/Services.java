package com.gov.sa.swcc;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Services extends Fragment {


    public Services() {
        // Required empty public constructor
    }

    CardView proplan,Eskan,Trinning,research0,research1,research2,research3
            ,camputerper,car,cardpercontractor,cameraper,returnper,cardper,docment,truck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_services, container, false);
        proplan=(CardView)view.findViewById(R.id.proplan);
        Eskan=(CardView)view.findViewById(R.id.Eskan);
        Trinning=(CardView)view.findViewById(R.id.Trinning);
        research0=(CardView)view.findViewById(R.id.research0);
        research1=(CardView)view.findViewById(R.id.research1);
        research2=(CardView)view.findViewById(R.id.research2);
        research3=(CardView)view.findViewById(R.id.research3);
        camputerper=(CardView)view.findViewById(R.id.camputerper);
        car=(CardView)view.findViewById(R.id.car);
        cardpercontractor=(CardView)view.findViewById(R.id.cardpercontractor);
        cameraper=(CardView)view.findViewById(R.id.cameraper);
        returnper=(CardView)view.findViewById(R.id.returnper);
        cardper=(CardView)view.findViewById(R.id.cardper);
        docment=(CardView)view.findViewById(R.id.docment);
        truck=(CardView)view.findViewById(R.id.truck);

        proplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Pro_planningActivity.class));
            }
        });
        Eskan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),EskanServiceoutActivity.class));
            }
        });
        research0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Prospects%20for%20improving%20the%20performance%20of%20SWRO%20plants%20by%20implementing%20advanced%20NFRO%20techniques.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
            }
        });
        research1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Evaluating%20suitability%20of%20source%20water%20for%20a%20proposed%20SWRO%20plant%20location.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);            }
        });
        research2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Perspective%20on%20desalination%20discharges%20and%20coastal%20environments%20of%20the%20Arabian%20Peninsula.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);            }
        });
        research3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Effect%20of%20cooling%20water%20chlorination%20on%20entrained%20selected%20copepods%20species.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);            }
        });
        camputerper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Lap.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);             }
        });
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="
                        +"https://www.swcc.gov.sa/uploads/CarSup.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);                  }
        });
        cardpercontractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
                        "https://www.swcc.gov.sa/uploads/IDSup.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
            }
        });
        cameraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
                        "https://www.swcc.gov.sa/uploads/PhotoReq.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
            }
        });
        returnper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
                        "https://www.swcc.gov.sa/uploads/IDSupRe.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
            }
        });
        cardper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
                        "https://www.swcc.gov.sa/uploads/IDSupLi.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
            }
        });
        truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+
                        "https://www.swcc.gov.sa/uploads/Material-GatepassSup.pdf");
                Link.putExtra("Auth","N");
                Link.putExtra("Share","1");
                startActivity(Link);
            }
        });



//        proplan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Link=  new Intent(getActivity(),ShowLinkActivity.class);
//                Link.putExtra("URL_LINK","http://docs.google.com/gview?embedded=true&url="+"https://www.swcc.gov.sa/uploads/Lap.pdf");
//                Link.putExtra("Auth","N");
//                Link.putExtra("Share","1");
//                startActivity(Link);
//            }
//        });
        Trinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),TrainingActivity.class));
            }
        });
        docment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ReturnDocmentsActivity.class));
            }
        });

        return view;
    }

}
