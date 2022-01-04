package com.gov.sa.swcc;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
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
    CardView idCard,detials,Transactions
            ,sal_cer,leave,salmonth,ITCom,EskanService,IndustrialSecurity,HrRequest,InsuranceInfo
            ,Sharkhom,EmpCard;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home_info, container, false);
        global=new Global(getContext());
        Emppic=(TextView)view.findViewById(R.id.Emppic);
        EmpName=(TextView)view.findViewById(R.id.EmpName);
        EmpJob=(TextView)view.findViewById(R.id.EmpJob);

        sal_cer=(CardView)view.findViewById(R.id.sal_cer);
        leave=(CardView)view.findViewById(R.id.leave);
        Transactions=(CardView)view.findViewById(R.id.Transactions);

        idCard=(CardView)view.findViewById(R.id.idCard);
        detials=(CardView)view.findViewById(R.id.detials);
        salmonth=(CardView)view.findViewById(R.id.salmonth);
        EskanService=(CardView)view.findViewById(R.id.EskanService);
        IndustrialSecurity=(CardView)view.findViewById(R.id.IndustrialSecurity);
        HrRequest=(CardView)view.findViewById(R.id.HrRequest);
        InsuranceInfo=(CardView)view.findViewById(R.id.InsuranceInfo);
        Sharkhom=(CardView)view.findViewById(R.id.Sharkhom);
        EmpCard=(CardView)view.findViewById(R.id.EmpCard);


        ITCom=(CardView)view.findViewById(R.id.ITCom);


        PersonalResult per=global.GetPData("PersonalResult");

        EmpName.setText(per.getResultObject().getFullName());
        EmpJob.setText(per.getResultObject().getTitle());
        byte[] decodedString = Base64.decode(per.getResultObject().getPhoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Drawable d = new BitmapDrawable(getResources(), decodedByte);
        Emppic.setBackground(d);




        Animation animZoomIn = AnimationUtils.loadAnimation(getActivity(),R.anim.zoom_in);
        Animation animZoomOut = AnimationUtils.loadAnimation(getActivity(),R.anim.zoom_out);

        idCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),EmpCardActivity.class));
                    }
                }, 150);

            }
        });
        detials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),JobDetailsActivity.class));
                    }
                }, 150);
            }
        });
        Transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),TransactionsActivity.class));
                    }
                }, 150);

            }
        });

        sal_cer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),EmployeeIdentificationActivity.class));
                    }
                }, 150);

            }
        });
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),LeaveActivity.class));
                    }
                }, 150);

            }
        });
        salmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),PayslipActivity.class));
                    }
                }, 150);
            }
        });

        ITCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),ITComActivity.class));
                    }
                }, 150);
            }
        });

        EskanService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),EskanServiceActivity.class));
                    }
                }, 150);
            }
        });


        IndustrialSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),IndustrialSecurityActivity.class));
                    }
                }, 150);
            }
        });

        HrRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),HrRequestActivity.class));
                    }
                }, 150);
            }
        });
        InsuranceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),InsuranceInfoActivity.class));
                    }
                }, 150);
            }
        });


        Sharkhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);

                        Intent Link=new Intent(getActivity(),ShowLinkActivity.class);
                        Link.putExtra("URL_LINK","https://ext.swcc.gov.sa/news");
                        Link.putExtra("Auth","T");
                        Link.putExtra("Share","0");
                        startActivity(Link);
                    }
                }, 150);

            }
        });

        EmpCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(animZoomIn);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animZoomOut);
                        startActivity(new Intent(getActivity(),QRCodeActivity.class));
                    }
                }, 150);
            }
        });





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

}
