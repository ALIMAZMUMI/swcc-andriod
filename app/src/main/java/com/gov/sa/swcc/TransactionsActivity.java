package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TransactionsActivity extends AppCompatActivity {
Global global;
ListView Transactions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


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
        TextView back=(TextView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransactionsActivity.this.finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

            }
        });


    }

    private void CallTransactions() {
        String user=global.GetValue("Username");



        Call<List<TransactionsApiResult>> call = RetrofitClient.getInstance(Api.SingINOutBASE_URL).getMyApi().Transactions(user);
        ProgressDialog dialog = ProgressDialog.show(TransactionsActivity.this, "",
                "يرجى الإنتظار", true);
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

                        TransactionAdapter adp=new TransactionAdapter(TransactionsActivity.this,neworder);
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
