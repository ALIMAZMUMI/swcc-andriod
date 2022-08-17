package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gov.sa.swcc.model.ReportModel;
import com.gov.sa.swcc.model.ReportRes.ReportRe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareakReportNActivity extends AppCompatActivity {
    Spinner projecttype,reporttype,CMType,LMType,supType;
TextView from,to;
Button submit;
Global global;
LinearLayout CM,LM,Sup;
    List<ReportRe> projecttypel,reporttypel,CMTypel,LMTypel,supTypel;
    final Calendar myCalendar= Calendar.getInstance();
    final Calendar myCalendar2= Calendar.getInstance();

    final Calendar Current= Calendar.getInstance();
    ArrayAdapter<String> reporttype1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareak_report_nactivity);
        global=new Global(ShareakReportNActivity.this);
        projecttype=(Spinner) findViewById(R.id.projecttype);
        reporttype=(Spinner) findViewById(R.id.reporttype);
        CMType=(Spinner) findViewById(R.id.CMtype);
        LMType=(Spinner) findViewById(R.id.LMType);
        supType=(Spinner) findViewById(R.id.supType);
        from=(TextView) findViewById(R.id.from);
        to=(TextView) findViewById(R.id.to);
        submit=(Button) findViewById(R.id.submit);
        CM=(LinearLayout) findViewById(R.id.CM);
        LM=(LinearLayout) findViewById(R.id.LM);
        Sup=(LinearLayout) findViewById(R.id.Sup);





        ArrayList<String> rtname=new ArrayList<>();

        rtname.add("تقرير الحضور و الانصراف");
        rtname.add("تقرير التقييم");
        rtname.add("تقرير ساعات العمل");
        rtname.add("تقرير حساب الحضور و الانصراف");

        rtname.add("نوع التقرير");

       reporttype1 = new ArrayAdapter<String>(ShareakReportNActivity.this, R.layout.spinnericon,R.id.spinneritem, rtname)
        {
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                return super.getDropDownView(position , convertView, parent);
            }

            public int getCount() {
                return rtname.size() - 1;
            }
        };
        reporttype.setAdapter(reporttype1);
        reporttype.setSelection(rtname.size()-1);


        DatePickerDialog.OnDateSetListener date1 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                String myFormat="yyyy-MM-dd";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                from.setText(dateFormat.format(myCalendar.getTime()));
                CheckSubmit();
            }
        };
        DatePickerDialog.OnDateSetListener date2 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH,month);
                myCalendar2.set(Calendar.DAY_OF_MONTH,day);
                String myFormat="yyyy-MM-dd";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                to.setText(dateFormat.format(myCalendar2.getTime()));
                CheckSubmit();
            }
        };
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ShareakReportNActivity.this,date1,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ShareakReportNActivity.this,date2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        CM.setVisibility(View.GONE);
        LM.setVisibility(View.GONE);
        Sup.setVisibility(View.GONE);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        ((TextView)findViewById(R.id.headertxt)).setText("اصدار التقارير");
        reporttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > reporttype1.getCount() - 1)
                {
                    ((TextView) adapterView.getChildAt(0).findViewById(R.id.spinneritem)).setTextColor(Color.parseColor("#CACCCE"));
                }
                CheckSubmit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        projecttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > projecttypel.size() - 1)
                {
                    ((TextView) adapterView.getChildAt(0).findViewById(R.id.spinneritem)).setTextColor(Color.parseColor("#CACCCE"));

                }else {
                    CheckSubmit();
                    String role=projecttypel.get(i).getRoleType();
                    CM.setVisibility(View.GONE);
                    LM.setVisibility(View.GONE);
                    Sup.setVisibility(View.GONE);

                    if(role.equals("CM")){
                        String id=projecttypel.get(i).getId();
                        if(projecttypel.get(i).getId().length()==0)
                        {id="0";}
                        CallCM(projecttypel.get(i).getId(),projecttypel.get(i).getContractsId(),projecttypel.get(i).getRoleType());
                    }else  if(role.equals("LM")){
                        String id=projecttypel.get(i).getId();
                        if(projecttypel.get(i).getId().length()==0)
                        {id="0";}
                        CallLM(projecttypel.get(i).getId(),projecttypel.get(i).getContractsId(),projecttypel.get(i).getRoleType());
                    }else if(role.equals("Super")){
                        String id=projecttypel.get(i).getId();
                        if(projecttypel.get(i).getId().length()==0)
                        {id="0";}
                        CallSup(projecttypel.get(i).getId(),projecttypel.get(i).getContractsId(),projecttypel.get(i).getRoleType());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        CMType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > CMTypel.size() - 1)
                {
                    ((TextView) adapterView.getChildAt(0).findViewById(R.id.spinneritem)).setTextColor(Color.parseColor("#CACCCE"));

                }else {
                    String role=CMTypel.get(i).getRoleType();
                    LM.setVisibility(View.GONE);
                    Sup.setVisibility(View.GONE);

                   if(role.equals("LM")){
                        String id=CMTypel.get(i).getId();
                        if(CMTypel.get(i).getId().length()==0)
                        {id="0";}
                        CallLM(CMTypel.get(i).getId(),CMTypel.get(i).getContractsId(),CMTypel.get(i).getRoleType());
                    }else if(role.equals("Super")){
                        String id=CMTypel.get(i).getId();
                        if(CMTypel.get(i).getId().length()==0)
                        {id="0";}
                        CallSup(CMTypel.get(i).getId(),CMTypel.get(i).getContractsId(),CMTypel.get(i).getRoleType());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        LMType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > LMTypel.size() - 1)
                {
                    ((TextView) adapterView.getChildAt(0).findViewById(R.id.spinneritem)).setTextColor(Color.parseColor("#CACCCE"));

                }else {
                    String role=LMTypel.get(i).getRoleType();
                    Sup.setVisibility(View.GONE);

 if(role.equals("Super")){
                        String id=LMTypel.get(i).getId();
                        if(LMTypel.get(i).getId().length()==0)
                        {id="0";}
                        CallSup(LMTypel.get(i).getId(),LMTypel.get(i).getContractsId(),LMTypel.get(i).getRoleType());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        CallAllProject();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URLLink=Api.Sharektest+"/Shareek/Reports/";
                if(reporttype.getSelectedItemPosition()==0)
                {

                    if(Sup.getVisibility()==View.VISIBLE&&supType.getSelectedItemPosition()!=(supType.getAdapter().getCount())){
                        URLLink+="GetEmployeeTransactionsNEW?ContractId="+supTypel.get(supType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+supTypel.get(supType.getSelectedItemPosition()).getId()+"&roletype="+supTypel.get(supType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else if(LM.getVisibility()==View.VISIBLE&&LMType.getSelectedItemPosition()!=(LMType.getAdapter().getCount())){
                        URLLink+="GetEmployeeTransactionsNEW?ContractId="+LMTypel.get(LMType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+LMTypel.get(LMType.getSelectedItemPosition()).getId()+"&roletype="+LMTypel.get(LMType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else if(CM.getVisibility()==View.VISIBLE&&CMType.getSelectedItemPosition()!=(CMType.getAdapter().getCount())){
                        Log.d("CMTypegetSelected",CMType.getSelectedItemPosition()+"");
                        URLLink+="GetEmployeeTransactionsNEW?ContractId="+CMTypel.get(CMType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+CMTypel.get(CMType.getSelectedItemPosition()).getId()+"&roletype="+CMTypel.get(CMType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else {
                        URLLink+="GetEmployeeTransactionsNEW?ContractId="+projecttypel.get(projecttype.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+projecttypel.get(projecttype.getSelectedItemPosition()).getId()+"&roletype="+projecttypel.get(projecttype.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }
                }
                if(reporttype.getSelectedItemPosition()==1)
                {

                    if(Sup.getVisibility()==View.VISIBLE&&supType.getSelectedItemPosition()!=(supType.getAdapter().getCount())){
                        URLLink+="GetProjectEvalNew?ContractId="+supTypel.get(supType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+supTypel.get(supType.getSelectedItemPosition()).getId()+"&roletype="+supTypel.get(supType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else if(LM.getVisibility()==View.VISIBLE&&LMType.getSelectedItemPosition()!=(LMType.getAdapter().getCount())){
                        URLLink+="GetProjectEvalNew?ContractId="+LMTypel.get(LMType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+LMTypel.get(LMType.getSelectedItemPosition()).getId()+"&roletype="+LMTypel.get(LMType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else if(CM.getVisibility()==View.VISIBLE&&CMType.getSelectedItemPosition()!=(CMType.getAdapter().getCount())){
                        Log.d("CMTypegetSelected",CMType.getSelectedItemPosition()+"");
                        URLLink+="GetProjectEvalNew?ContractId="+CMTypel.get(CMType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+CMTypel.get(CMType.getSelectedItemPosition()).getId()+"&roletype="+CMTypel.get(CMType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else {
                        URLLink+="GetProjectEvalNew?ContractId="+projecttypel.get(projecttype.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+projecttypel.get(projecttype.getSelectedItemPosition()).getId()+"&roletype="+projecttypel.get(projecttype.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }
                }

                if(reporttype.getSelectedItemPosition()==2)
                {

                    if(Sup.getVisibility()==View.VISIBLE&&supType.getSelectedItemPosition()!=(supType.getAdapter().getCount())){
                        URLLink+="GetTransactionReport?ContractId="+supTypel.get(supType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+supTypel.get(supType.getSelectedItemPosition()).getId()+"&roletype="+supTypel.get(supType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else if(LM.getVisibility()==View.VISIBLE&&LMType.getSelectedItemPosition()!=(LMType.getAdapter().getCount())){
                        URLLink+="GetTransactionReport?ContractId="+LMTypel.get(LMType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+LMTypel.get(LMType.getSelectedItemPosition()).getId()+"&roletype="+LMTypel.get(LMType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else if(CM.getVisibility()==View.VISIBLE&&CMType.getSelectedItemPosition()!=(CMType.getAdapter().getCount())){
                        Log.d("CMTypegetSelected",CMType.getSelectedItemPosition()+"");
                        URLLink+="GetTransactionReport?ContractId="+CMTypel.get(CMType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+CMTypel.get(CMType.getSelectedItemPosition()).getId()+"&roletype="+CMTypel.get(CMType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else {
                        URLLink+="GetTransactionReport?ContractId="+projecttypel.get(projecttype.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+projecttypel.get(projecttype.getSelectedItemPosition()).getId()+"&roletype="+projecttypel.get(projecttype.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }
                }

                if(reporttype.getSelectedItemPosition()==3)
                {

                    if(Sup.getVisibility()==View.VISIBLE&&supType.getSelectedItemPosition()!=(supType.getAdapter().getCount())){
                        URLLink+="GetAttendanceaAccount?ContractId="+supTypel.get(supType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+supTypel.get(supType.getSelectedItemPosition()).getId()+"&roletype="+supTypel.get(supType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else if(LM.getVisibility()==View.VISIBLE&&LMType.getSelectedItemPosition()!=(LMType.getAdapter().getCount())){
                        URLLink+="GetAttendanceaAccount?ContractId="+LMTypel.get(LMType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+LMTypel.get(LMType.getSelectedItemPosition()).getId()+"&roletype="+LMTypel.get(LMType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else if(CM.getVisibility()==View.VISIBLE&&CMType.getSelectedItemPosition()!=(CMType.getAdapter().getCount())){
                        Log.d("CMTypegetSelected",CMType.getSelectedItemPosition()+"");
                        URLLink+="GetAttendanceaAccount?ContractId="+CMTypel.get(CMType.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+CMTypel.get(CMType.getSelectedItemPosition()).getId()+"&roletype="+CMTypel.get(CMType.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }else {
                        URLLink+="GetAttendanceaAccount?ContractId="+projecttypel.get(projecttype.getSelectedItemPosition()).getContractsId()+"&FromStrdate="+from.getText().toString()+"&ToStrdate="+to.getText().toString()+"&id="+projecttypel.get(projecttype.getSelectedItemPosition()).getId()+"&roletype="+projecttypel.get(projecttype.getSelectedItemPosition()).getRoleType()+"&secretToken=123456789";
                    }
                }

                    Intent Link=  new Intent(ShareakReportNActivity.this,SharekShowActivity.class);
                    Link.putExtra("URL_LINK",URLLink);
                    Link.putExtra("Auth","N");
                    Link.putExtra("Share","0");
                    startActivity(Link);



            }
        });



    }


    public void CheckSubmit(){
        if(from.getText().toString().length()>3&&
                to.getText().toString().length()>3&&
                projecttype.getSelectedItemPosition()!=(projecttype.getAdapter().getCount())&&
                reporttype.getSelectedItemPosition()!=(reporttype.getAdapter().getCount())){
            submit.setBackgroundResource(R.drawable.blueroundfull);
            submit.setEnabled(true);
        }else {
            submit.setBackgroundResource(R.drawable.grayroundbtn);
            submit.setEnabled(false);
        }

    }


    private void CallAllProject() {
        String user=global.GetValue("Username").replace("u","");



        Call<List<ReportRe>> call = RetrofitClient.getInstance(Api.Sharektest).getMyApi().GetAllProject(user);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<List<ReportRe>>() {
            @Override
            public void onResponse(Call<List<ReportRe>> call, Response<List<ReportRe>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        dialog.dismiss();
                        projecttypel=response.body();
                        ArrayList<String> projectname=new ArrayList<>();
                        for(int i=0;i<projecttypel.size();i++){
                                projectname.add(projecttypel.get(i).getName());
                        }
                        projectname.add("اسم المشروع");

                        ArrayAdapter<String> project = new ArrayAdapter<String>(ShareakReportNActivity.this, R.layout.spinnericon,R.id.spinneritem, projectname)
                        {
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                return super.getDropDownView(position , convertView, parent);
                            }

                            public int getCount() {
                                return projectname.size() - 1;
                            }
                        };

                        projecttype.setAdapter(project);
                        projecttype.setSelection(projectname.size()-1);


                    }else {
                        dialog.dismiss();
                        global.ShowMessageF("لا توجد مشاريع مسجلة",ShareakReportNActivity.this);
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<ReportRe>>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }
    private void CallCM(String UID,String ContractsId,String RoleType) {
        String user=global.GetValue("Username").replace("u","");



        Call<List<ReportRe>> call = RetrofitClient.getInstance(Api.Sharektest).getMyApi().GetDDL(UID,ContractsId,RoleType);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<List<ReportRe>>() {
            @Override
            public void onResponse(Call<List<ReportRe>> call, Response<List<ReportRe>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        dialog.dismiss();
                        CMTypel=response.body();
                        ArrayList<String> Cmname=new ArrayList<>();
                        for(int i=0;i<CMTypel.size();i++){
                            Cmname.add(CMTypel.get(i).getName());
                        }
                        Cmname.add("اسم مدير الموقع");

                        ArrayAdapter<String> project = new ArrayAdapter<String>(ShareakReportNActivity.this, R.layout.spinnericon,R.id.spinneritem, Cmname)
                        {
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                return super.getDropDownView(position , convertView, parent);
                            }

                            public int getCount() {
                                return Cmname.size() - 1;
                            }
                        };

                        CMType.setAdapter(project);
                        CMType.setSelection(Cmname.size()-1);
                        CM.setVisibility(View.VISIBLE);

                    }else {
                        dialog.dismiss();
                        //global.ShowMessageF("لا توجد مشاريع مسجلة",ShareakReportNActivity.this);
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<ReportRe>>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }
    private void CallLM(String UID,String ContractsId,String RoleType) {
        String user=global.GetValue("Username").replace("u","");



        Call<List<ReportRe>> call = RetrofitClient.getInstance(Api.Sharektest).getMyApi().GetDDL(UID,ContractsId,RoleType);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<List<ReportRe>>() {
            @Override
            public void onResponse(Call<List<ReportRe>> call, Response<List<ReportRe>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        dialog.dismiss();
                        LMTypel=response.body();
                        ArrayList<String> lmname=new ArrayList<>();
                        for(int i=0;i<LMTypel.size();i++){
                            lmname.add(LMTypel.get(i).getName());
                        }
                        lmname.add("اسم المراقب");

                        ArrayAdapter<String> project = new ArrayAdapter<String>(ShareakReportNActivity.this, R.layout.spinnericon,R.id.spinneritem, lmname)
                        {
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                return super.getDropDownView(position , convertView, parent);
                            }

                            public int getCount() {
                                return lmname.size() - 1;
                            }
                        };

                        LMType.setAdapter(project);
                        LMType.setSelection(lmname.size()-1);
                        LM.setVisibility(View.VISIBLE);

                    }else {
                        dialog.dismiss();
                        //global.ShowMessageF("لا توجد مشاريع مسجلة",ShareakReportNActivity.this);
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<ReportRe>>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }
    private void CallSup(String UID,String ContractsId,String RoleType) {
        String user=global.GetValue("Username").replace("u","");



        Call<List<ReportRe>> call = RetrofitClient.getInstance(Api.Sharektest).getMyApi().GetDDL(UID,ContractsId,RoleType);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<List<ReportRe>>() {
            @Override
            public void onResponse(Call<List<ReportRe>> call, Response<List<ReportRe>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        dialog.dismiss();
                        supTypel=response.body();
                        ArrayList<String> supname=new ArrayList<>();
                        for(int i=0;i<supTypel.size();i++){
                            supname.add(supTypel.get(i).getName());
                        }
                        supname.add("اسم الموظف");

                        ArrayAdapter<String> project = new ArrayAdapter<String>(ShareakReportNActivity.this, R.layout.spinnericon,R.id.spinneritem, supname)
                        {
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                return super.getDropDownView(position , convertView, parent);
                            }

                            public int getCount() {
                                return supname.size() - 1;
                            }
                        };

                        supType.setAdapter(project);
                        supType.setSelection(supname.size()-1);
                        Sup.setVisibility(View.VISIBLE);

                    }else {
                        dialog.dismiss();
                        //global.ShowMessageF("لا توجد مشاريع مسجلة",ShareakReportNActivity.this);
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<ReportRe>>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }





}