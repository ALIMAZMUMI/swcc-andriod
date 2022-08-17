package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.GridAdapter;
import com.gov.sa.swcc.Adapter.PartnerAdapter;
import com.gov.sa.swcc.Adapter.ProjectAdapter;
import com.gov.sa.swcc.model.PartnerItem;
import com.gov.sa.swcc.model.PartnerModel;
import com.gov.sa.swcc.model.RatingModel;
import com.gov.sa.swcc.model.Sharekproject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class ShareakPartnerActivity extends AppCompatActivity {
    SegmentedGroup days;
    ListView projrcts,projrcts1;
    List<PartnerModel> Day1,Day2;
    PartnerAdapter adapter1,adapter2;
    Global global;
    String Sid,ProjectMangerId,Classfcation_LK,SelDate;
    RadioButton button1,button2;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareak_partner);
         global=new Global(ShareakPartnerActivity.this);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        ((TextView)findViewById(R.id.headertxt)).setText(getIntent().getExtras().getString("HT",""));
        ((ImageView)findViewById(R.id.backarrow)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });


        // TextView back=(TextView)findViewById(R.id.back);
        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SelDate=sdf.format(c.getTime());

        Sid=getIntent().getStringExtra("Sid");
        ProjectMangerId=getIntent().getStringExtra("ProjectMangerId");
        Classfcation_LK=getIntent().getStringExtra("Classfcation_LK");



        button1=(RadioButton)findViewById(R.id.button1);
        button2=(RadioButton)findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                projrcts.setVisibility(View.VISIBLE);
                projrcts1.setVisibility(View.GONE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
projrcts.setVisibility(View.GONE);
projrcts1.setVisibility(View.VISIBLE);
            }
        });
        submit=(Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button1.isChecked()){
                    Log.d("GIIEEI",global.GetValue(SelDate+"1"+Classfcation_LK+ProjectMangerId));
                    if(!global.GetValue(SelDate+"1"+Classfcation_LK+ProjectMangerId).equals("true")){

                        boolean iscompleted=false;
for(int i=0;i<Day1.size();i++){
    Day1.get(i).setSupervisorId(Sid);

if(Day1.get(i).getRoundResult()!=null) {
    if ((boolean) Day1.get(i).getRoundResult() == true) {
        iscompleted = true;
    } else {
        iscompleted = false;
        break;
    }
} else {
    iscompleted = false;
    break;
}
}
                   if(iscompleted) {
                       for (int i = 0; i < Day1.size(); i++) {
                           if (Day1.get(i).getCmt() == null) {
                               Day1.get(i).setCmt("");
                           }
                           if (Day1.get(i).getPriorityLK() == null) {
                               Day1.get(i).setPriorityLK("");
                           }
                           if (Day1.get(i).getPhoto() == null) {
                               Day1.get(i).setPhoto("");
                           }
                       }
                       InsertContractsEvaluation(Day1);
                   }
                }

                }

                else if(button2.isChecked()){

                    if(!global.GetValue(SelDate+"2"+Classfcation_LK+ProjectMangerId).equals("true")){

                        boolean iscompleted=false;
                        for(int i=0;i<Day2.size();i++){
                            Day2.get(i).setSupervisorId(Sid);

                            if(Day2.get(i).getRoundResult()!=null) {
                                if ((boolean) Day2.get(i).getRoundResult() == true) {
                                    iscompleted = true;
                                } else {
                                    iscompleted = false;
                                    break;
                                }
                            } else {
                                iscompleted = false;
                                break;
                            }
                        }
                        if(iscompleted) {
                            for (int i = 0; i < Day2.size(); i++) {
                                if (Day2.get(i).getCmt() == null) {
                                    Day2.get(i).setCmt("");
                                }
                                if (Day2.get(i).getPriorityLK() == null) {
                                    Day2.get(i).setPriorityLK("");
                                }
                                if (Day2.get(i).getPhoto() == null) {
                                    Day2.get(i).setPhoto("");
                                }
                            }
                            InsertContractsEvaluation(Day2);
                        }
                    }


                }
            }




        });



        projrcts=(ListView) findViewById(R.id.projrcts);
        projrcts1=(ListView) findViewById(R.id.projrcts1);
        CallSharek( ProjectMangerId
                , Sid
                , Classfcation_LK
                , "1"
                , SelDate);
        projrcts.setVisibility(View.VISIBLE);

        CallSharek( ProjectMangerId
                , Sid
                , Classfcation_LK
                , "2"
                , SelDate);


        Log.d("GIIEEI",SelDate+"1"+Classfcation_LK+ProjectMangerId);

        projrcts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(Day1.get(i).getRoundResult()!=null){
                    if(!global.GetValue(SelDate+"1"+Classfcation_LK+ProjectMangerId).equals("true")){

                    if((boolean)Day1.get(i).getRoundResult()==true){

                        Intent intent=new Intent(ShareakPartnerActivity.this,AddNoteActivity.class);
                        intent.putExtra("header",Day1.get(i).getContractEvaluationElementName());
                        intent.putExtra("index",i);
                        intent.putExtra("Round",Day1.get(i).getRoundNumber());


                        intent.putExtra("Prio",Day1.get(i).getPriorityLK());
                        intent.putExtra("Cmt",Day1.get(i).getCmt());
                        intent.putExtra("image",Day1.get(i).getPhoto());


                        startActivityForResult(intent,1001);
                    }
                }}
            }
        });

        projrcts1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(Day2.get(i).getRoundResult()!=null){
                    if(!global.GetValue(SelDate+"2"+Classfcation_LK+ProjectMangerId).equals("true")){

                        if((boolean)Day2.get(i).getRoundResult()==true){

                            Intent intent=new Intent(ShareakPartnerActivity.this,AddNoteActivity.class);
                            intent.putExtra("header",Day2.get(i).getContractEvaluationElementName());
                            intent.putExtra("index",i);
                            intent.putExtra("Round",Day2.get(i).getRoundNumber());

                            startActivityForResult(intent,1001);
                        }
                    }}
            }
        });



        if (global.GetValue("Lan").equals("en")) {

            button1.setText("first visit");
            button2.setText("second visit");
            ((TextView)findViewById(R.id.hname)).setText("task evaluations");
            ((LinearLayout)findViewById(R.id.sline)).setGravity(Gravity.LEFT);

//            rowtext1.setGravity(Gravity.LEFT);
submit.setText("send");
        }
    }


    public void getHeight(PartnerAdapter listadp, ListView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i <listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getDividerHeight() * ((int)(listadp.getCount()+1)));
        listview.setLayoutParams(params);
        listview.requestLayout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 1001:
                        Log.d("Getting Value","true");

                        int round=data.getIntExtra("Round",1);
                        int index=data.getIntExtra("index",1);
                        String details=data.getStringExtra("details");
                        String radio=data.getStringExtra("radio");
                        String Image=data.getStringExtra("Image");

                        Log.d("Getting Value",round+"");
                        Log.d("Getting Value",radio);
                    Log.d("Getting Value",radio);




                        if(round==1){
    Day1.get(index).setPriorityLK(radio);
    Day1.get(index).setCmt(details);
    Day1.get(index).setPhoto(Image);
                            adapter1=new PartnerAdapter(ShareakPartnerActivity.this,Day1,Classfcation_LK,ProjectMangerId);
                            projrcts.setAdapter(adapter1);

                        }
else if(round==2){

}



                    break;




            }
        }
    }



    private void InsertContractsEvaluation(List<PartnerModel> List) {
        String user=global.GetValue("Username").replace("u","");
        Call<Boolean> call = RetrofitClient.getInstance(Api.Global).getMyApi().InsertContractsEvaluation1(List);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body()){
                        dialog.dismiss();

                        if(List.get(0).getRoundNumber()==1) {

                            global.SaveValue(SelDate + "1" + Classfcation_LK+ProjectMangerId, "true");
                            global.ShowMessageNF("تم إرسال الزيارة بنجاح", ShareakPartnerActivity.this);
                        }else if(List.get(0).getRoundNumber()==2) {

                            global.SaveValue(SelDate + "2" + Classfcation_LK+ProjectMangerId, "true");
                            global.ShowMessageNF("تم إرسال الزيارة بنجاح", ShareakPartnerActivity.this);
                        }

                    }else {
                        dialog.dismiss();
                        global.SaveValue(SelDate+"1"+Classfcation_LK+ProjectMangerId,"false");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Boolean>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }



    //
    private void CallSharek(String ProjectMangerId
            ,String SupervisorId
            ,String Classfcation_LK
            ,String roundNumber
            ,String date) {

        Call<List<PartnerModel>> call = RetrofitClient.getInstance(Api.Global).getMyApi().GetAllContractElementsEvaluation( ProjectMangerId
                , SupervisorId
                , Classfcation_LK
                , roundNumber
                ,date);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<List<PartnerModel>>() {
            @Override
            public void onResponse(Call<List<PartnerModel>> call, Response<List<PartnerModel>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){

                        dialog.dismiss();
                        if(roundNumber.equals("1")){

                        Day1=response.body();
                        adapter1=new PartnerAdapter(ShareakPartnerActivity.this,response.body(),Classfcation_LK,ProjectMangerId);
                        projrcts.setAdapter(adapter1);
                            getHeight(adapter1,projrcts);
                            boolean t=false;
                            for(int i=0;i<Day1.size();i++){
                                if(Day1.get(i).getRoundResult()!=null){
                                    if((boolean)Day1.get(i).getRoundResult()==true){
                                        t=true;
                                    }else {
                                        t=false;
                                        break;
                                    }
                                }
                            }

                            if(t){
                                global.SaveValue(SelDate+"1"+Classfcation_LK+ProjectMangerId,"true");
                            }



                        }else if(roundNumber.equals("2")) {
                            Day2=response.body();
                            adapter2=new PartnerAdapter(ShareakPartnerActivity.this,response.body(),Classfcation_LK,ProjectMangerId);
                            projrcts1.setAdapter(adapter2);
                            getHeight(adapter2,projrcts1);
                            boolean t=false;
                           if(!global.GetValue(SelDate+"1"+Classfcation_LK+ProjectMangerId).equals("true")){

                            for(int i=0;i<Day2.size();i++){
                                if(Day2.get(i).getRoundResult()!=null){
                                    if((boolean)Day2.get(i).getRoundResult()==true){
                                        t=true;
                                    }else {
                                        t=false;
                                        break;
                                    }
                                }
                            }

                            if(t){
                                global.SaveValue(SelDate+"2"+Classfcation_LK+ProjectMangerId,"true");
                            }
                        }

                        }
                    }else {
                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<PartnerModel>>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

}