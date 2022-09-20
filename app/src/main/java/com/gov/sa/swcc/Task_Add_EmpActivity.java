package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gov.sa.swcc.Adapter.ShowEmpAdapter;
import com.gov.sa.swcc.model.HirereachyManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Task_Add_EmpActivity extends AppCompatActivity {
Global global;
List<HirereachyManager> hirereachyManagers;
    List<HirereachyManager> hirereachysearch;

ListView searchres;
    ShowEmpAdapter showEmpAdapter;
    Button submit;
    EditText Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add_emp);
        global=new Global(Task_Add_EmpActivity.this);
        Name=(EditText) findViewById(R.id.schvalue);

        submit=(Button)findViewById(R.id.submit);
        submit.setEnabled(false);






        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                if(Name.getText().toString().length()>=3){
                    hirereachysearch=new ArrayList<>();

                    for (int i=0;i<hirereachyManagers.size();i++){
                        if(hirereachyManagers.get(i).getName().contains(Name.getText().toString())||hirereachyManagers.get(i).getUid().contains(Name.getText().toString()))
                        {
                            hirereachysearch.add(hirereachyManagers.get(i));
                        }
                    }

if (hirereachysearch.size()>0) {
    if (Emp_Add_TaskActivity.uid != null) {
        for (int i = 0; i < hirereachysearch.size(); i++) {
            if (Emp_Add_TaskActivity.uid.contains(hirereachysearch.get(i).getUid())) {
                hirereachysearch.get(i).setSelected(true);
                submit.setEnabled(true);
                submit.setBackgroundResource(R.drawable.blueroundfull);

            }
        }
    }

    showEmpAdapter = new ShowEmpAdapter(Task_Add_EmpActivity.this, hirereachysearch);
    searchres.setAdapter(showEmpAdapter);

}else {
    showEmpAdapter = new ShowEmpAdapter(Task_Add_EmpActivity.this, new ArrayList<>());
    searchres.setAdapter(showEmpAdapter);

}
                }else{
                    if(Emp_Add_TaskActivity.uid!=null){
                        for(int i=0;i<hirereachyManagers.size();i++)
                        {
                            if(Emp_Add_TaskActivity.uid.contains(hirereachyManagers.get(i).getUid())){
                                hirereachyManagers.get(i).setSelected(true);
                                submit.setEnabled(true);
                                submit.setBackgroundResource(R.drawable.blueroundfull);

                            }
                        }}

                    showEmpAdapter=new ShowEmpAdapter(Task_Add_EmpActivity.this,hirereachyManagers);
                    searchres.setAdapter(showEmpAdapter);

                }

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> uid=new ArrayList<>();
                List<String> Names=new ArrayList<>();

                for(int i=0;i<searchres.getAdapter().getCount();i++){

                    ShowEmpAdapter x=(ShowEmpAdapter)searchres.getAdapter();
                    HirereachyManager hi=x.getItem(i);



                    if(hi.isSelected()){
                       uid.add(hi.getUid());
                        Names.add(hi.getName());
                    }
                }
                Emp_Add_TaskActivity.uid=uid;
                Emp_Add_TaskActivity.Name=Names;

                Intent intent=new Intent();
                setResult(1400,intent);

                Task_Add_EmpActivity.this.finish();

            }
        });
        searchres=(ListView) findViewById(R.id.searchres);
        searchres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

        GetEmpTran();

    }

    public void Clickme(View v){
        CheckBox checkBox=(CheckBox) v;
        int pos=searchres.getPositionForView(v);
        ShowEmpAdapter xx=(ShowEmpAdapter)searchres.getAdapter();
        xx.getItem(pos).setSelected(checkBox.isChecked());

        for(int i=0;i<searchres.getAdapter().getCount();i++){

            ShowEmpAdapter x=(ShowEmpAdapter)searchres.getAdapter();
            HirereachyManager hi=x.getItem(i);
            if(hi.isSelected()){
                submit.setEnabled(hi.isSelected());
                submit.setBackgroundResource(R.drawable.blueroundfull);
                break;
            }else{
                submit.setEnabled(hi.isSelected());
                submit.setBackgroundResource(R.drawable.grayroundbtn);
            }

        }

    }


    private void GetEmpTran() {


        //Call<List<HirereachyManager>> call = RetrofitClient.getInstance(Api.Global).getMyApi().HirereachyManager(global.GetValue("Username").replaceAll("u",""));
        Call<List<HirereachyManager>> call = RetrofitClient.getInstance(Api.Global).getMyApi().HirereachyManager(global.GetValue("MID"));

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
                        if(Emp_Add_TaskActivity.uid!=null){
                        for(int i=0;i<hirereachyManagers.size();i++)
                        {
if(Emp_Add_TaskActivity.uid.contains(hirereachyManagers.get(i).getUid())){
    hirereachyManagers.get(i).setSelected(true);
    submit.setEnabled(true);
    submit.setBackgroundResource(R.drawable.blueroundfull);

}
                        }}

                         showEmpAdapter=new ShowEmpAdapter(Task_Add_EmpActivity.this,hirereachyManagers);
                        searchres.setAdapter(showEmpAdapter);






                    }else {
                        dialog.dismiss();
                        global.ShowMessageF("لا يوجد عاملين لديك",Task_Add_EmpActivity.this);
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