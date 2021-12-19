package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.gov.sa.swcc.Adapter.TransactionAdapter;
import com.gov.sa.swcc.model.Body;
import com.gov.sa.swcc.model.LeaveEnvelope;
import com.gov.sa.swcc.model.TransactionsApiResult;
import com.gov.sa.swcc.model.ZhrLeavesInfo;

import java.nio.charset.StandardCharsets;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LeaveActivity extends AppCompatActivity {
Global global;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        global=new Global(LeaveActivity.this);
        CallLeaves();

    }

    private void CallLeaves() {
        String user=global.GetValue("Username");
        byte[] data = "P2001713316:sap@1234".getBytes(StandardCharsets.UTF_8);
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);

        LeaveEnvelope leaveEnvelope=new LeaveEnvelope();
        leaveEnvelope.Body=new Body();
        leaveEnvelope.Body.ZhrLeavesInfo=new ZhrLeavesInfo();
        leaveEnvelope.Body.ZhrLeavesInfo.IBegda=user;
        Call<List<Object>> call = RetrofitClient.getInstance(Api.SAPBASE_URL).getMyApi().Leaves("Basic "+base64,leaveEnvelope);
        ProgressDialog dialog = ProgressDialog.show(LeaveActivity.this, "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Response<List<Object>> response, Retrofit retrofit) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();
                if(response.isSuccess())
                {

                   Log.d("Show xxaaxx",""+response.body().size());

                    }else {

                        global.ShowMessage("لا توجد عمليات مسجلة");
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
