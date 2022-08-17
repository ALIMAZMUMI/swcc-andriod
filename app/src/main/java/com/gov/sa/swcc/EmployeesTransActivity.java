package com.gov.sa.swcc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.EmployeesTransactionAdapter;
import com.gov.sa.swcc.Adapter.TaskHistoryAdapter;
import com.gov.sa.swcc.Adapter.TransactionAdapter;
import com.gov.sa.swcc.model.HirereachyManager;
import com.gov.sa.swcc.model.TaskHistory.TaskHistory;
import com.gov.sa.swcc.model.TransactionsApiResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeesTransActivity extends AppCompatActivity {
ListView listView;
Global global;
List<HirereachyManager> hirereachyManagers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_employees_trans);
        global=new Global(EmployeesTransActivity.this);

        listView=(ListView)findViewById(R.id.Emplist);
        TextView Header=(TextView)findViewById(R.id.header);
        String text = "<font color=#004C86>خدمات الموارد البشرية /</font> <font color=#0066CC>حضور المرؤوسين</font>";
        Header.setText(Html.fromHtml(text));

        EditText searchvalue=(EditText)findViewById(R.id.searchvalue);

        searchvalue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(searchvalue.getText().length()>0) {
                    List<HirereachyManager> serch = new ArrayList<>();
                    for (int i = 0; i < hirereachyManagers.size(); i++) {
                        if (hirereachyManagers.get(i).getName().contains(searchvalue.getText().toString())) {
                            serch.add(hirereachyManagers.get(i));

                        }

                    }
                    EmployeesTransactionAdapter adp = new EmployeesTransactionAdapter(EmployeesTransActivity.this, serch);
                    listView.setAdapter(adp);
                }else
                {
                    EmployeesTransactionAdapter adp = new EmployeesTransactionAdapter(EmployeesTransActivity.this, hirereachyManagers);
                    listView.setAdapter(adp);
                }


            }
        });


        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });






        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(EmployeesTransActivity.this,TransactionsActivity.class);
                intent.putExtra("UIDT",((EmployeesTransactionAdapter)listView.getAdapter()).getItem(i).getUid());
                startActivity(intent);
            }
        });

        GetEmpTran();


    }

    private void CallTransactions(String ID,String Date,int ix) {



        Call<List<TransactionsApiResult>> call = RetrofitClient.getInstance(Api.Global).getMyApi().Transactions(ID);

        call.enqueue(new Callback<List<TransactionsApiResult>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TransactionsApiResult>> call, Response<List<TransactionsApiResult>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        List<TransactionsApiResult> neworder =new ArrayList<>(response.body().size());
                        int hasin=0;
                        for (int i=0;i<response.body().size();i++){
                            Log.d("Date Check",response.body().get(i).getPunchDate()+"---");


                            Date c = Calendar.getInstance().getTime();

                            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                            SimpleDateFormat df1 = new SimpleDateFormat("EEEE", new Locale("ar"));

                            String formattedDate = df.format(c);

Log.d("Date Check",response.body().get(i).getPunchDate()+"---"+formattedDate);
                            if(response.body().get(i).getPunchDate().contains(formattedDate)){
                                if(response.body().get(i).getPunchState().equals("I")&&hasin==0) {
                                    hasin++;
                                    hirereachyManagers.get(ix).setFrom(response.body().get(i).getPunchTime());
                                }else {
                                    hirereachyManagers.get(ix).setTo(response.body().get(i).getPunchTime());
                                }
                            }

                            hirereachyManagers.get(ix).setDate(df.format(c)+"-"+df1.format(c));
                        }
                        if(hasin==0){
                            hirereachyManagers.get(ix).setStatus("2");
                        }





                    }else {
                        hirereachyManagers.get(ix).setStatus("1");



                    }

                    if((ix+1)==hirereachyManagers.size()){

                        EmployeesTransactionAdapter adp=new EmployeesTransactionAdapter(EmployeesTransActivity.this,hirereachyManagers);
                        listView.setAdapter(adp);
                    }


                }
            }

            @Override
            public void onFailure(Call<List<TransactionsApiResult>> call, Throwable t) {
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });


    }
    private void GetEmpTran() {


        Call<List<HirereachyManager>> call = RetrofitClient.getInstance(Api.Global).getMyApi().HirereachyManager(global.GetValue("Username").replaceAll("u",""));
       //Call<List<HirereachyManager>> call = RetrofitClient.getInstance(Api.Global).getMyApi().HirereachyManager("290550");
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<List<HirereachyManager>>() {
            @Override
            public void onResponse(Call<List<HirereachyManager>> call, Response<List<HirereachyManager>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        hirereachyManagers=response.body();
                        dialog.dismiss();

                        for (int i=0;i<response.body().size();i++){
                            CallTransactions(response.body().get(i).getUid(),"",i);
                        }






                    }else {
                        dialog.dismiss();
                        global.ShowMessageF("لا يوجد عاملين لديك",EmployeesTransActivity.this);
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<HirereachyManager>>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }





}