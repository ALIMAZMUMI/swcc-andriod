package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FinancialCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_calculator);

        ((TextView)findViewById(R.id.Endofservice)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinancialCalculatorActivity.this,EndofServiceActivity.class));
            }
        });
     /*   ((Button)findViewById(R.id.OWork)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinancialCalculatorActivity.this,OvertimeWorkActivity.class));

            }
        });*/
        ((TextView)findViewById(R.id.Vbalance)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(FinancialCalculatorActivity.this,VBalanceActivity.class));

            }
        });
    }

}