package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ListView;

import com.gov.sa.swcc.Adapter.TransactionAdapter;
import com.gov.sa.swcc.model.PersonalResult;
import com.gov.sa.swcc.model.TransactionsApiResult;

import java.nio.charset.StandardCharsets;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TransactionsActivity extends AppCompatActivity {
Global global;
ListView Transactions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        global=new Global(TransactionsActivity.this);
        Transactions=(ListView)findViewById(R.id.Transactions);

        CallTransactions();


    }

    private void CallTransactions() {
        String user=global.GetValue("Username");



        Call<List<TransactionsApiResult>> call = RetrofitClient.getInstance(Api.SingINOutBASE_URL).getMyApi().Transactions(user);
        ProgressDialog dialog = ProgressDialog.show(TransactionsActivity.this, "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<List<TransactionsApiResult>>() {
            @Override
            public void onResponse(Response<List<TransactionsApiResult>> response, Retrofit retrofit) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();
                if(response.isSuccess())
                {

                    if(response.body().size()>0){


                        TransactionAdapter adp=new TransactionAdapter(TransactionsActivity.this,response.body());
                        Transactions.setAdapter(adp);


                    }else {

                        global.ShowMessage("لا توجد عمليات مسجلة");
                    }

                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }
}
