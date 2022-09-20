package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VBalanceActivity extends AppCompatActivity {
    Button submit;
    EditText BasicSalary, TotalAllowances, NumofVacations;
    Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vbalance);

        BasicSalary = (EditText) findViewById(R.id.BasicSalary);
        TotalAllowances = (EditText) findViewById(R.id.TotalAllowances);
        NumofVacations = (EditText) findViewById(R.id.NumofVacations);
        submit = (Button) findViewById(R.id.submit);

        global = new Global(VBalanceActivity.this);

        ((ImageView) findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        submit.setEnabled(false);

        BasicSalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(BasicSalary.getText().length()>0&&TotalAllowances.getText().length()>0&&NumofVacations.getText().length()>0){
                    submit.setBackgroundResource(R.drawable.blueroundfull);
                    submit.setEnabled(true);
                }else{
                    submit.setBackgroundResource(R.drawable.grayroundbtn);
                    submit.setEnabled(false);
                }
            }
        });

        TotalAllowances.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(BasicSalary.getText().length()>0&&TotalAllowances.getText().length()>0 && NumofVacations.getText().length()>0){
                    submit.setBackgroundResource(R.drawable.blueroundfull);
                    submit.setEnabled(true);
                }else{
                    submit.setBackgroundResource(R.drawable.grayroundbtn);
                    submit.setEnabled(false);
                }
            }
        });

        NumofVacations.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(BasicSalary.getText().length()>0 && TotalAllowances.getText().length()>0&&NumofVacations.getText().length()>0){
                    submit.setBackgroundResource(R.drawable.blueroundfull);
                    submit.setEnabled(true);
                }else{
                    submit.setBackgroundResource(R.drawable.grayroundbtn);
                    submit.setEnabled(false);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalculateVacation();
            }
        });

    }
    private void CalculateVacation() {
        String BasicSalary1 = BasicSalary.getText().toString();
        String TotalAllowances1 = TotalAllowances.getText().toString();
        String NumofVacations1 = NumofVacations.getText().toString();

        Call<Double> call = RetrofitClient.getInstance(Api.FURL).getMyApi().CalculateVacation("",BasicSalary1,TotalAllowances1,NumofVacations1);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {
                    if(response.body() != null){
                        Double result = response.body();
                        DecimalFormat formatter = new DecimalFormat("#,###,###.##");
                            global.ShowMessageNF1(formatter.format(result) + " " + "ريـال", VBalanceActivity.this);
                            dialog.dismiss();
                    }else {
                        dialog.dismiss();
                    }
                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Double>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }
        });
    }
}