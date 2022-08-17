package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gov.sa.swcc.Adapter.EmpRaitingAdapter;
import com.gov.sa.swcc.Adapter.ProjectRaitingAdapter;
import com.gov.sa.swcc.model.RatingModel;
import com.gov.sa.swcc.model.Signproject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpRatingActivity extends AppCompatActivity {
    Global global ;
    ListView projrcts;
    String EID,EName;
    TextView textheader;
    Button submit;
    int w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_rating);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        global=new Global(EmpRatingActivity.this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        w = displayMetrics.heightPixels;

        textheader=(TextView)findViewById(R.id.textheader);
        EID=getIntent().getExtras().getString("EID");
        EName=getIntent().getExtras().getString("EName");
        submit=(Button) findViewById(R.id.submit);
        Button back=(Button) findViewById(R.id.back);

        ((TextView)findViewById(R.id.headertxt)).setText(getIntent().getExtras().getString("HT",""));
        ((ImageView)findViewById(R.id.backarrow)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
        if (global.GetValue("Lan").equals("en")) {
            textheader.setText("employee evaluation "+EName);
            submit.setText("send");
            back.setText("back");

        }else
            {
        textheader.setText("تقييم العامل "+EName);
        }

        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });


        projrcts=(ListView)findViewById(R.id.projrcts);
        //String ID=getIntent().getExtras().getString("Sid","");


        Calendar Current=Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String SelDate=sdf.format(Current.getTime());

        CallSharek("",SelDate);


        submit.setBackgroundResource(R.drawable.grayroundbtn);
        submit.setEnabled(false);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmpRaitingAdapter adapter=(EmpRaitingAdapter)projrcts.getAdapter();
                int i=0;
                for (i=0;i<adapter.getCount();i++){
if(adapter.getItem(i).getValue()==0){
    Toast.makeText(EmpRatingActivity.this,"فضلا تقييم جميع الفئات.",Toast.LENGTH_SHORT).show();
break;
}
                }


                if(i==adapter.getCount()){
                   List<RatingModel> ratingModels =adapter.getTitem();
                    for (i=0;i<ratingModels.size();i++){
                    ratingModels.get(i).setEmployeeId(EID);
                        ratingModels.get(i).setCreatedBy("");
                        ratingModels.get(i).setModifyBy("");

                    }
                    InsertContractsEvaluation(ratingModels);
                }

            }
        });




    }


    public void change(){

        EmpRaitingAdapter adapter=(EmpRaitingAdapter)projrcts.getAdapter();
int i=0;
        for (i=0;i<adapter.getCount();i++){
            if(adapter.getItem(i).getValue()==0){
                submit.setBackgroundResource(R.drawable.grayroundbtn);
                submit.setEnabled(false);
                break;
            }else {
                submit.setBackgroundResource(R.drawable.blueroundfull);
                submit.setEnabled(true);
            }
        }
    }

    private void CallSharek(String ID,String Date) {
        String user=global.GetValue("Username").replace("u","");
        Call<List<RatingModel>> call = RetrofitClient.getInstance(Api.Global).getMyApi().GetAllLookupEvaluation(user);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<List<RatingModel>>() {
            @Override
            public void onResponse(Call<List<RatingModel>> call, Response<List<RatingModel>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        dialog.dismiss();
                        EmpRaitingAdapter adp=new EmpRaitingAdapter(EmpRatingActivity.this,response.body(),w);
                        projrcts.setAdapter(adp);
                        getHeight(adp,projrcts);
                    }else {
                        dialog.dismiss();
                        global.ShowMessage("لا يوجد بيانات مسجلة");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<RatingModel>>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

    private void InsertContractsEvaluation(List<RatingModel> List) {
        String user=global.GetValue("Username").replace("u","");
        Call<Boolean> call = RetrofitClient.getInstance(Api.Global).getMyApi().InsertContractsEvaluation(List);
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
                        if(global.GetValue("Lan").equals("en")){
                            global.ShowMessageNF("Thanks","en",EmpRatingActivity.this);

                        }
                        else{
                        global.ShowMessageNF("شكرا لك !",EmpRatingActivity.this);
                        }
                    }else {
                        dialog.dismiss();
                        global.ShowMessage("لا يوجد بيانات مسجلة");
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


    public void getHeight(EmpRaitingAdapter listadp, ListView listview)
    {
        int totalHeight = 0;
        for (int i = 0; i < listadp.getCount(); i++) {
            View listItem = listadp.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = totalHeight + (listview.getDividerHeight() * (listadp.getCount() - 1));
        listview.setLayoutParams(params);
        listview.requestLayout();
    }
}