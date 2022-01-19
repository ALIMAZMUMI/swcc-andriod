package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EmpSearchActivity extends AppCompatActivity {
EditText Name;
ListView searchres;
Global global;
    ArrayAdapter<String> EmpNames;
    List<String> Names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_search);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);



        Name=(EditText) findViewById(R.id.searchvalue);
        searchres=(ListView) findViewById(R.id.searchres);


        searchres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!EmpNames.getItem(i).equals("لا يوجد نتائج")&&!EmpNames.getItem(i).equals("البحث متاح بالغة العربية فقط")){
                    Intent intent=new Intent(EmpSearchActivity.this,PersonalCardActivity.class);
                    intent.putExtra("Emp",Names.get(i));
                    startActivity(intent);
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

        global=new Global(EmpSearchActivity.this);
        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(Name.getText().toString().length()>2){
                    Names=global.GetEmps(Name.getText().toString());

                    if(Names.size()>0){
                    List<String> Detiles=new ArrayList<>();
                    for(int i=0;i<Names.size();i++)
                    {
                        String [] empdata=Names.get(i).split("#\\$#");
                        Detiles.add(empdata[0]+" - "+empdata[6]);
                    }
                     EmpNames = new ArrayAdapter<String>(EmpSearchActivity.this, R.layout.listitem,R.id.EmpName, Detiles);
                    searchres.setAdapter(EmpNames);
                    }else {

                        List<String> Detiles=new ArrayList<>();
                        Detiles.add("لا يوجد نتائج");
                        Detiles.add("البحث متاح بالغة العربية فقط");
                        EmpNames = new ArrayAdapter<String>(EmpSearchActivity.this, R.layout.listitem,R.id.EmpName, Detiles);
                        searchres.setAdapter(EmpNames);
                    }
                }else{
                    List<String> Detiles=new ArrayList<>();
                    EmpNames = new ArrayAdapter<String>(EmpSearchActivity.this, R.layout.listitem,R.id.EmpName, Detiles);
                    searchres.setAdapter(EmpNames);
                }
            }
        });

    }
}