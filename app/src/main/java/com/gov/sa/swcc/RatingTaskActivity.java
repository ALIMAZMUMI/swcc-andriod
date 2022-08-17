package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.borjabravo.simpleratingbar.SimpleRatingBar;
import com.gov.sa.swcc.model.ExtendTask.ExtendTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingTaskActivity extends AppCompatActivity {
    Global global;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_task);

        global=new Global(RatingTaskActivity.this);
        SimpleRatingBar  ratingBar=(SimpleRatingBar)findViewById(R.id.simple_rating_bar);
        ratingBar.setRating(3);
        Button submit=(Button)findViewById(R.id.submit);
        Button cancel=(Button)findViewById(R.id.cancel);
        ID=getIntent().getExtras().getString("ID");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected=(int)ratingBar.getRating();
String RID="";
                if(selected==1)
                {
                    RID="15";
                }
                else if(selected==2)
                {
                    RID="16";

                }
                else if(selected==3)
                {
                    RID="17";

                }
                else if(selected==4)
                {
                    RID="18";


                }
                else
                {
                    RID="19";


                }

                RateTask(ID,RID);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RatingTaskActivity.this.finish();
            }
        });



    }



    private void RateTask(String ID,String RID) {
        String Token=global.GetValue("TaskToken");

        Call<ExtendTask> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().RateTask(Token,ID,RID);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<ExtendTask>() {
            @Override
            public void onResponse(Call<ExtendTask> call, Response<ExtendTask> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
                        Intent intent=new Intent();
                        intent.putExtra("update",true);
                        setResult(1002,intent);
                        global.ShowMessageNF(response.body().getMessage(),RatingTaskActivity.this);


                    }else {
                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {
                    try {
                        global.ShowMessageNF(response.body().getMessage(),RatingTaskActivity.this);

                    }catch (Exception e){

                    }
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ExtendTask>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

}