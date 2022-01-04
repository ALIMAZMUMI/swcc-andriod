package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gov.sa.swcc.Adapter.CardsAdapter;
import com.gov.sa.swcc.model.InsuranceInfo;
import com.gov.sa.swcc.model.ListInsuranceDatum;
import com.gov.sa.swcc.model.ProPlanning;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pro_planningActivity extends AppCompatActivity {
Global global;
    ImageView next,prev;
    TextView saldate,addimage;
    Calendar c,Current;
    String SelDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_planning);
         c = Calendar.getInstance();
        Current=Calendar.getInstance();
        c.add(Calendar.DATE,-1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SelDate=sdf.format(c.getTime());


        saldate=(TextView)findViewById(R.id.saldate);
        addimage=(TextView)findViewById(R.id.addimage);
        next=(ImageView) findViewById(R.id.next);
        prev=(ImageView) findViewById(R.id.prev);

        saldate.setText(SelDate);
        CallProPlanning(SelDate);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.add(Calendar.DATE,1);
                SelDate=sdf.format(c.getTime());
                if(Current.getTimeInMillis()>c.getTimeInMillis()){

                    saldate.setText(SelDate);
                    CallProPlanning(SelDate);
                }else {
                    c.add(Calendar.DATE,-1);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.add(Calendar.DATE,-1);
                SelDate=sdf.format(c.getTime());
                try {

                    saldate.setText(SelDate);
                    CallProPlanning(SelDate);

                }catch (Exception e){
                    Log.d("Error --------",e.toString());
                }
            }
        });

    }


    private void CallProPlanning(String Date) {



        Call<ProPlanning> call = RetrofitClient.getInstance(Api.ProPlaning).getMyApi().ProPlaning(Date);
        ProgressDialog dialog = ProgressDialog.show(Pro_planningActivity.this, "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<ProPlanning>() {
            @Override
            public void onResponse(Call<ProPlanning> call, Response<ProPlanning> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(true){
                        dialog.dismiss();

                        String V1=response.body().getResultObject().substring(11,response.body().getResultObject().indexOf(","));
                        String V2=response.body().getResultObject().substring(response.body().getResultObject().indexOf("forecast_exp")+13,response.body().getResultObject().length());
                        float v1=Float.parseFloat(V1);
                        float v2=Float.parseFloat(V2);
float pers=(v1/v2)*100;

                        addimage.setBackground(writeTextOnDrawable(Date,V1,V2,Math.round(pers)+"%"));

                    }else {
                        dialog.dismiss();

                        global.ShowMessage("");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ProPlanning> call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }

    private BitmapDrawable writeTextOnDrawable(String date_pro,String V1,String V2,String V3) {

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.prdplanning)
                .copy(Bitmap.Config.ARGB_8888, true);

        Typeface tf = ResourcesCompat.getFont(Pro_planningActivity.this, R.font.swcc);

        //Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTypeface(tf);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        paint.setTextSize(convertToPixels(Pro_planningActivity.this, 65));

        Log.d("Image Size",bm.getHeight()+"------"+bm.getWidth());
        Rect textRect = new Rect();
        //paint.getTextBounds(text, 0, text.length(), textRect);

        Canvas canvas = new Canvas(bm);

        //If the text is bigger than the canvas , reduce the font size
//        if(textRect.width() >= (canvas.getWidth() - 4))     //the padding on either sides is considered as 4, so as to appropriately fit in the text
//            paint.setTextSize(convertToPixels(EmployeeIdentificationActivity.this, 7));        //Scaling needs to be used for different dpi's

        //Calculate the positions
        int xPos = (canvas.getWidth() / 2) - 2;     //-2 is for regulating the x position offset

        //"- ((paint.descent() + paint.ascent()) / 2)" is the distance from the baseline to the center.
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        //Arabic Name
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("بيانات يوم :"+date_pro,1330, 821, paint);
        paint.setTextSize(convertToPixels(Pro_planningActivity.this, 38));
        paint.setColor(Color.WHITE);
        canvas.drawText(V1+" مليون متر مكعب",1673, 1490, paint);
        canvas.drawText(V2+" مليون متر مكعب",2073, 1990, paint);
        canvas.drawText(V3,1873, 2700, paint);

//        paint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText("PIN: "+insuranceInfo.getMedgulfPIN(),215, 900, paint);
//        canvas.drawText("REL: "+insuranceInfo.getRel(),215, 1100, paint);
//        canvas.drawText("Birth Date: "+insuranceInfo.getBirdthDateAsDmy(),215, 1300, paint);
//

        return new BitmapDrawable(Pro_planningActivity.this.getResources(), bm);
    }


    public static int convertToPixels(Context context, int nDP)
    {
        final float conversionScale = context.getResources().getDisplayMetrics().density;

        return (int) ((nDP * conversionScale) + 0.5f) ;

    }

}
