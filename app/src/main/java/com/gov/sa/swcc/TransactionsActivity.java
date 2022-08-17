package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.TransactionAdapter;
import com.gov.sa.swcc.model.PersonalResult;
import com.gov.sa.swcc.model.TransactionsApiResult;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TransactionsActivity extends AppCompatActivity {
Global global;
ListView Transactions;
    TextView Header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


         Header=(TextView)findViewById(R.id.header);
        String text = "<font color=#004C86>خدمات الموارد البشرية /</font> <font color=#0066CC>الحضور والانصراف</font>";
        Header.setText(Html.fromHtml(text));


        global=new Global(TransactionsActivity.this);
        Transactions=(ListView)findViewById(R.id.Transactions);

        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
        if(global.CheckInternet(TransactionsActivity.this)) {
        }else {
            CallTransactions();
        }
//        ImageView back=(TextView)findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TransactionsActivity.this.finish();
//                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
//
//            }
//        });


    }

    private void CallTransactions() {
        String user,UID="";

        Intent intent = getIntent();

        if(intent.getExtras()==null){
            UID="";
        }else{
            String text = "<font color=#004C86>خدمات الموارد البشرية /</font> <font color=#0066CC>حضور وانصراف العامل لآخر 7 أيام</font>";
            Header.setText(Html.fromHtml(text));
             UID=getIntent().getExtras().getString("UIDT","");

        }
        if(UID.length()>1){
            user=UID;
        }else {
            user = global.GetValue("Username");
        }


        Call<List<TransactionsApiResult>> call = RetrofitClient.getInstance(Api.Global).getMyApi().Transactions(user);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<List<TransactionsApiResult>>() {
            @Override
            public void onResponse(Call<List<TransactionsApiResult>> call, Response<List<TransactionsApiResult>> response) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        List<TransactionsApiResult> neworder =new ArrayList<>(response.body().size());
                        for (int i=0;i<response.body().size();i++){

                            neworder.add(response.body().get(response.body().size()-(i+1)));
                        }


                        List<TransactionsApiResult> newq =new ArrayList<>(response.body().size());

//                        TransactionsApiResult N=new TransactionsApiResult();
//                        N.setEmpCode("Header");
//                        N.setPunchState("حضور وانصراف اليوم");
//                        newq.add(N);

                        for (int i=0;i<neworder.size();i++){
                            String dtStart = neworder.get(i).getPunchDate().substring(0,10);
                            Date c = Calendar.getInstance().getTime();

                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date date = format.parse(dtStart);
                                System.out.println("test----"+date);

                                if(TimeUnit.DAYS.convert(c.getTime()-date.getTime(), TimeUnit.MILLISECONDS)<7)
                                {
                                    newq.add(neworder.get(i));
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                        }

                        TransactionAdapter adp=new TransactionAdapter(TransactionsActivity.this,newq);
                        Transactions.setAdapter(adp);


                    }else {

                        global.ShowMessage("لا توجد عمليات مسجلة");
                    }

                }
            }

            @Override
            public void onFailure(Call<List<TransactionsApiResult>> call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }
}
