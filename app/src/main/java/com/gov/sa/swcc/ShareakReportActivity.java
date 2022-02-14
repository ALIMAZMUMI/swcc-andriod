package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gov.sa.swcc.Adapter.ProjectAdapter;
import com.gov.sa.swcc.model.ReportModel;
import com.gov.sa.swcc.model.Sharekproject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareakReportActivity extends AppCompatActivity {

    Spinner projecttype,reporttype,date;
    Global global;
    List<ReportModel> sharekproject;
    List<String> projectname,reporttypetxt;
    List<String> Year,Month,Day;
    List<String> datedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareak_report);




        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        // TextView back=(TextView)findViewById(R.id.back);
        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
        global=new Global(ShareakReportActivity.this);
        projecttype=(Spinner) findViewById(R.id.projecttype);
        reporttype=(Spinner) findViewById(R.id.reporttype);
        date=(Spinner) findViewById(R.id.date);


        projecttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i<sharekproject.size()-1){
if(sharekproject.get(i).getRoleType().equals("PM")) {

    reporttypetxt=new ArrayList<>();
    reporttypetxt.add("تقرير تقييم العمالة رسم");
    reporttypetxt.add("تقرير الحضور حسب المشروع رسم");
    reporttypetxt.add("نوع التقرير");
    ArrayAdapter<String> project = new ArrayAdapter<String>(ShareakReportActivity.this, R.layout.spinnericon,R.id.spinneritem, reporttypetxt)
    {
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return super.getDropDownView(position , convertView, parent);
        }

        public int getCount() {
            return reporttypetxt.size() - 1;
        }
    };

    reporttype.setAdapter(project);
    reporttype.setSelection(reporttypetxt.size()-1);
    date.setAdapter(null);

}else{

    reporttypetxt=new ArrayList<>();
    reporttypetxt.add("تقرير حضور العمالة يومي جدول");
    reporttypetxt.add("تقرير تقييم العمالة جدول");
    reporttypetxt.add("تقرير الحضور حسب المراقب جدول");
    reporttypetxt.add("تقرير الحضور حسب المشروع رسم");

    reporttypetxt.add("نوع التقرير");
    ArrayAdapter<String> project = new ArrayAdapter<String>(ShareakReportActivity.this, R.layout.spinnericon,R.id.spinneritem, reporttypetxt)
    {
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return super.getDropDownView(position , convertView, parent);
        }

        public int getCount() {
            return reporttypetxt.size() - 1;
        }
    };

    reporttype.setAdapter(project);
    reporttype.setSelection(reporttypetxt.size()-1);
    date.setAdapter(null);

}}

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        reporttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(reporttype.getSelectedItemPosition()!=(reporttypetxt.size()-1)) {

                    if (reporttype.getAdapter().getCount() == 2) {
                        datedata = GetMonth();
                        ArrayAdapter<String> dateadp = new ArrayAdapter<String>(ShareakReportActivity.this, R.layout.spinnericon, R.id.spinneritem, datedata) {
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                return super.getDropDownView(position, convertView, parent);
                            }

                            public int getCount() {
                                return datedata.size();
                            }
                        };

                        date.setAdapter(dateadp);

                    } else if (reporttype.getAdapter().getCount() == 4) {
                        if (reporttype.getSelectedItemPosition() == 0) {
                            datedata = GetDay();
                            ArrayAdapter<String> dateadp = new ArrayAdapter<String>(ShareakReportActivity.this, R.layout.spinnericon, R.id.spinneritem, datedata) {
                                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                    return super.getDropDownView(position, convertView, parent);
                                }

                                public int getCount() {
                                    return datedata.size();
                                }
                            };

                            date.setAdapter(dateadp);
                        } else {
                            datedata = GetMonth();
                            ArrayAdapter<String> dateadp = new ArrayAdapter<String>(ShareakReportActivity.this, R.layout.spinnericon, R.id.spinneritem, datedata) {
                                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                    return super.getDropDownView(position, convertView, parent);
                                }

                                public int getCount() {
                                    return datedata.size();
                                }
                            };

                            date.setAdapter(dateadp);
                        }


                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        Button submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URLLink="https://apitest.swcc.gov.sa/FupsApi/reports/";
                if(projecttype.getSelectedItemPosition()==(projectname.size()-1)||reporttype.getSelectedItemPosition()==(reporttypetxt.size()-1))
                {
                    Toast.makeText(ShareakReportActivity.this,"فضلا تحديد جميع الخيارات",Toast.LENGTH_SHORT).show();
                }else{




                    if(sharekproject.get((projecttype.getSelectedItemPosition())).getRoleType().equals("PM"))
                    {
                            if(reporttype.getSelectedItemPosition()==0){
                                URLLink+="GetProjectEval?year="+Year.get(date.getSelectedItemPosition())+"&ProjectsManagerId="+sharekproject.get(projecttype.getSelectedItemPosition()).getProjectsManagerId()+"&month="+Month.get(date.getSelectedItemPosition());
                            }else if(reporttype.getSelectedItemPosition()==1){
                                URLLink+="GetChartOfAttendanceByProjectManagerId?year="+Year.get(date.getSelectedItemPosition())+"&ProjectsManagerId="+sharekproject.get(projecttype.getSelectedItemPosition()).getProjectsManagerId()+"&month="+Month.get(date.getSelectedItemPosition());

                            }
                    }else {

                        if(reporttype.getSelectedItemPosition()==0){
                            URLLink+="GetEmployeeTransactions?date="+Year.get(date.getSelectedItemPosition())+"-"+Month.get(date.getSelectedItemPosition())+"-"
                                    +Day.get(date.getSelectedItemPosition())+"&SuperVisorId="+sharekproject.get(projecttype.getSelectedItemPosition()).getSupervisorsId();
                        }else if(reporttype.getSelectedItemPosition()==1){
                            URLLink+="GetEmpEval?year="+Year.get(date.getSelectedItemPosition())+"&SupervisorId="+sharekproject.get(projecttype.getSelectedItemPosition()).getSupervisorsId()+"&month="+Month.get(date.getSelectedItemPosition());

                        }else if(reporttype.getSelectedItemPosition()==2){
                            URLLink+="GetEmployeeTransactionsMonthaly?year="+Year.get(date.getSelectedItemPosition())+"&SupervisorId="+sharekproject.get(projecttype.getSelectedItemPosition()).getSupervisorsId()+"&month="+Month.get(date.getSelectedItemPosition());

                        }else if(reporttype.getSelectedItemPosition()==3){
                            URLLink+="GetChartOfAttendanceBySupervisorId?year="+Year.get(date.getSelectedItemPosition())+"&SupervisorId="+sharekproject.get(projecttype.getSelectedItemPosition()).getSupervisorsId()+"&month="+Month.get(date.getSelectedItemPosition());

                        }
                    }

                    Intent Link=  new Intent(ShareakReportActivity.this,ShowLinkActivity.class);
                    Link.putExtra("URL_LINK",URLLink);
                    Link.putExtra("Auth","N");
                    Link.putExtra("Share","0");
                    startActivity(Link);


                }

            }
        });

        CallSharek();


    }


    public List<String> GetMonth(){
        Year=new ArrayList<>();
        Month=new ArrayList<>();
        List<String> Data=new ArrayList<>();
        Calendar start = Calendar.getInstance();
        Calendar last = Calendar.getInstance();
        last.add(Calendar.YEAR,-3);
        while(start.getTimeInMillis()>last.getTimeInMillis()){
            Year.add(start.get(Calendar.YEAR)+"");
            Month.add((start.get(Calendar.MONTH)+1)+"");
            Data.add("شهري "+(start.get(Calendar.MONTH)+1)+"-"+start.get(Calendar.YEAR));
            if((start.get(Calendar.MONTH)+1)==12){
                Year.add(start.get(Calendar.YEAR)+"");
                Month.add("");
                Data.add("سنوي *"+start.get(Calendar.YEAR)+"*");
            }
            start.add(Calendar.MONTH,-1);
        }
        return Data;
    }

    public List<String> GetDay(){
        Year=new ArrayList<>();
        Month=new ArrayList<>();
        Day=new ArrayList<>();
        List<String> Data=new ArrayList<>();
        Calendar start = Calendar.getInstance();
        Calendar last = Calendar.getInstance();
        last.add(Calendar.YEAR,-3);
        while(start.getTimeInMillis()>last.getTimeInMillis()){
            Year.add(start.get(Calendar.YEAR)+"");
            Month.add((start.get(Calendar.MONTH)+1)+"");
            Day.add(start.get(Calendar.DAY_OF_MONTH)+"");
            Data.add(start.get(Calendar.DAY_OF_MONTH)+"-"+(start.get(Calendar.MONTH)+1)+"-"+start.get(Calendar.YEAR));
//            if(start.get(Calendar.MONTH)==12){
//                Year.add(start.get(Calendar.YEAR)+"");
//                Month.add("");
//                Data.add("سنوي *"+start.get(Calendar.YEAR)+"*");
//            }
            start.add(Calendar.DAY_OF_MONTH,-1);
        }
        return Data;
    }

    private void CallSharek() {
        String user=global.GetValue("Username").replace("u","");



        Call<List<ReportModel>> call = RetrofitClient.getInstance(Api.Sharek).getMyApi().GetReportProjects(user);
        ProgressDialog dialog = ProgressDialog.show(ShareakReportActivity.this, "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<List<ReportModel>>() {
            @Override
            public void onResponse(Call<List<ReportModel>> call, Response<List<ReportModel>> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().size()>0){
                        dialog.dismiss();
                        sharekproject=response.body();
                        projectname=new ArrayList<>();
                        for(int i=0;i<sharekproject.size();i++){
                            if(sharekproject.get(i).getRoleType().equals("PM")){
                                projectname.add("مدير مشروع - "+sharekproject.get(i).getProjectName());
                            }else{
                                projectname.add("مراقب - "+sharekproject.get(i).getProjectName());
                            }

                        }

                        projectname.add("اسم المشروع");
                        ArrayAdapter<String> project = new ArrayAdapter<String>(ShareakReportActivity.this, R.layout.spinneritem,R.id.spinneritem, projectname)
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
                        global.ShowMessageF("لا توجد مشاريع مسجلة",ShareakReportActivity.this);
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<ReportModel>>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

}