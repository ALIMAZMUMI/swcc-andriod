package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndofServiceActivity extends AppCompatActivity {
    TextView EndWorkingDate, StartWorkingDate;
    TextView txts, txts2, txts3, txts4;
    Button submit;
EditText BasicSalary, TotalAllowances;
Global global;
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar1 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endof_service);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        BasicSalary = (EditText) findViewById(R.id.BasicSalary);
        TotalAllowances = (EditText) findViewById(R.id.TotalAllowances);
        EndWorkingDate = (TextView) findViewById(R.id.EndWorkingDate);
        StartWorkingDate = (TextView) findViewById(R.id.startWorkingDate);
        txts = (TextView) findViewById(R.id.txt1);
        txts2 = (TextView) findViewById(R.id.txt2);
        txts3 = (TextView) findViewById(R.id.txt3);
        txts4 = (TextView) findViewById(R.id.txt4);
        submit = (Button) findViewById(R.id.submit);

        global = new Global(EndofServiceActivity.this);

        String txts1 = "<font color=#0066CC>* للسعوديين</font> <font color=#CACCCE>عدم ادخال بدل تعويض الوردية</font>";
        txts.setText(Html.fromHtml(txts1 + " "));

        String txts12 = "<font color=#0066CC>* لغير السعوديين</font> <font color=#CACCCE>عدم ادخال بدل النقل وبدل تعويض الوردية</font>";
        txts2.setText(Html.fromHtml(txts12 + " "));

        String txts13 = "<font color=#0066CC>* للسعوديين</font> <font color=#CACCCE>يجب ادخال التاريخ بالهجري</font>";
        txts3.setText(Html.fromHtml(txts13 + " "));

        String txts14 = "<font color=#0066CC>* لغير السعوديين</font> <font color=#CACCCE>يجب ادخال التاريخ بالميلادي</font>";
        txts4.setText(Html.fromHtml(txts14 + " "));

        ((ImageView) findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
//        submit.setBackgroundResource(R.drawable.grayroundbtn);
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
                if(BasicSalary.getText().length()>0&&TotalAllowances.getText().length()>0){
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
                if(BasicSalary.getText().length()>0&&TotalAllowances.getText().length()>0){
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
                //global.ShowMessageNF1("شكرا لك !",EndofServiceActivity.this);

                CalculateEndOfService();
            }
        });
        DatePickerDialog.OnDateSetListener datep = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                String myFormat1 = "dd MMM yyyy";
                SimpleDateFormat dateFormat1 = new SimpleDateFormat(myFormat1, Locale.US);
                StartWorkingDate.setText(dateFormat1.format(myCalendar.getTime()));
            }
        };


        StartWorkingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EndofServiceActivity.this, datep, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        myCalendar.get(Calendar.YEAR);
        myCalendar.get(Calendar.MONTH);
        myCalendar.get(Calendar.DAY_OF_MONTH);
        String myFormat1 = "dd MMM yyyy";
        SimpleDateFormat dateFormat1 = new SimpleDateFormat(myFormat1, Locale.US);
        StartWorkingDate.setText(dateFormat1.format(myCalendar.getTime()));

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, month);
                myCalendar1.set(Calendar.DAY_OF_MONTH, day);
                String myFormat = "dd MMM yyyy";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                EndWorkingDate.setText(dateFormat.format(myCalendar1.getTime()));
            }
        };


        EndWorkingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EndofServiceActivity.this, date, myCalendar1.get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH), myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        myCalendar1.get(Calendar.YEAR);
        myCalendar1.get(Calendar.MONTH);
        myCalendar1.get(Calendar.DAY_OF_MONTH);
        String myFormat = "dd MMM yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        EndWorkingDate.setText(dateFormat.format(myCalendar1.getTime()));

    }

    private void CalculateEndOfService() {
        String BasicSalary1 = BasicSalary.getText().toString();
        String TotalAllowances1 = TotalAllowances.getText().toString();
        String StartWorkingDate1 = StartWorkingDate.getText().toString();
        String EndWorkingDate1 = EndWorkingDate.getText().toString();

        Call<Double> call = RetrofitClient.getInstance(Api.FURL).getMyApi().CalculateEndOfService("",BasicSalary1,TotalAllowances1,StartWorkingDate1,EndWorkingDate1);
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
                        // formatter.format(result)
                        global.ShowMessageNF1(formatter.format(result) + " " + "ريـال", EndofServiceActivity.this);
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