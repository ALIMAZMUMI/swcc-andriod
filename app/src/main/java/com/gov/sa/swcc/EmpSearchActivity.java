package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.SearchAdapter;
import com.gov.sa.swcc.model.SearchItem;

import java.util.ArrayList;
import java.util.List;

public class EmpSearchActivity extends AppCompatActivity {
EditText Name;
ListView searchres;
Global global;
    ArrayAdapter<String> EmpNames;
    List<String> Names;
    TextView nodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_search);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


        TextView Header=(TextView)findViewById(R.id.header);
        String text = "<font color=#004C86>خدمات الموارد البشرية /</font> <font color=#0066CC>البحث عن العاملين</font>";
        Header.setText(Html.fromHtml(text));


        Name=(EditText) findViewById(R.id.searchvalue);
        searchres=(ListView) findViewById(R.id.searchres);
        nodata=(TextView)findViewById(R.id.nodata);

        searchres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  if(!EmpNames.getItem(i).equals("لا يوجد نتائج")&&!EmpNames.getItem(i).equals("البحث متاح بالغة العربية فقط")){
                    Intent intent=new Intent(EmpSearchActivity.this,PersonalCardActivity.class);
                    intent.putExtra("Emp",Names.get(i));
                    startActivity(intent);
               // }
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
                        nodata.setVisibility(View.GONE);
                        searchres.setVisibility(View.VISIBLE);
                    List<SearchItem> Detiles=new ArrayList<>();
                    for(int i=0;i<Names.size();i++)
                    {
                        String [] empdata=Names.get(i).split("#\\$#");
                        SearchItem searchItem=new SearchItem();
                        searchItem.setName(empdata[0]);
                        searchItem.setCity(empdata[6]);
                        Detiles.add(searchItem);
                    }
                        SearchAdapter EmpNames = new SearchAdapter(EmpSearchActivity.this, Detiles);
                    searchres.setAdapter(EmpNames);
                    }else {

                        nodata.setVisibility(View.VISIBLE);
                        searchres.setVisibility(View.GONE);
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