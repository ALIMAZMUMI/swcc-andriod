package com.gov.sa.swcc;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gov.sa.swcc.model.PersonalResult;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeInfo extends Fragment {


    public HomeInfo() {
        // Required empty public constructor
    }
TextView Emppic,EmpName,EmpJob;
    Global global;
    CardView idCard,detials,Transactions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home_info, container, false);
        global=new Global(getContext());
        Emppic=(TextView)view.findViewById(R.id.Emppic);
        EmpName=(TextView)view.findViewById(R.id.EmpName);
        EmpJob=(TextView)view.findViewById(R.id.EmpJob);

        idCard=(CardView)view.findViewById(R.id.idCard);
        detials=(CardView)view.findViewById(R.id.detials);
        Transactions=(CardView)view.findViewById(R.id.Transactions);


        PersonalResult per=global.GetPData("PersonalResult");

        EmpName.setText(per.getResultObject().getFullName());
        EmpJob.setText(per.getResultObject().getTitle());
        byte[] decodedString = Base64.decode(per.getResultObject().getPhoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Drawable d = new BitmapDrawable(getResources(), decodedByte);
        Emppic.setBackground(d);



        idCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),EmpCardActivity.class));
            }
        });
        detials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),JobDetailsActivity.class));
            }
        });
        Transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),LeaveActivity.class));

                // startActivity(new Intent(getActivity(),TransactionsActivity.class));
            }
        });




        return view;
    }

}