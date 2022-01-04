package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gov.sa.swcc.Adapter.CardsAdapter;
import com.gov.sa.swcc.Adapter.TransactionAdapter;
import com.gov.sa.swcc.model.InsuranceInfo;
import com.gov.sa.swcc.model.ListInsuranceDatum;
import com.gov.sa.swcc.model.TransactionsApiResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsuranceInfoActivity extends AppCompatActivity {
    Global global;
    ListView Insur;
    List<BitmapDrawable> insurbitmap;
    TextView insparent,cuntactus,instable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_info);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        global=new Global(InsuranceInfoActivity.this);
        Insur=(ListView)findViewById(R.id.cards);
        insparent=(TextView) findViewById(R.id.insparent);
        cuntactus=(TextView) findViewById(R.id.cuntactus);
        instable=(TextView) findViewById(R.id.instable);

        insparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.swcc.gov.sa/uploads/Leaflet_And%20Providers_CLASS_AP.pdf";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);            }
        });

        cuntactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"HR-Medical@swcc.gov.sa"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT,"");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        instable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.swcc.gov.sa/uploads/Leaflet_And%20Providers_CLASS_A.pdf";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

Insur.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(isStoragePermissionGranted()){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:8001162700"));
        startActivity(callIntent);}
    }
});

        CallInsur();


    }

    private void CallInsur() {
        String user=global.GetValue("Username");



        Call<InsuranceInfo> call = RetrofitClient.getInstance(Api.Insur).getMyApi().Insurance(user);
        ProgressDialog dialog = ProgressDialog.show(InsuranceInfoActivity.this, "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<InsuranceInfo>() {
            @Override
            public void onResponse(Call<InsuranceInfo> call, Response<InsuranceInfo> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getListInsuranceData().size()>0){
insurbitmap=new ArrayList<>();
for (int i=0;i<response.body().getListInsuranceData().size();i++){
    insurbitmap.add(writeTextOnDrawable(response.body().getListInsuranceData().get(i)));
}
                        dialog.dismiss();

                        CardsAdapter adp=new CardsAdapter(InsuranceInfoActivity.this,insurbitmap);
                        Insur.setAdapter(adp);

                    }else {
                        dialog.dismiss();

                        global.ShowMessage("لا توجد عمليات مسجلة");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<InsuranceInfo> call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }


    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Swcc","Permission is granted");
                return true;
            } else {

                Log.v("SWCC","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("SWCC","Permission is granted");
            return true;
        }
    }


    private BitmapDrawable writeTextOnDrawable(ListInsuranceDatum insuranceInfo) {

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.insurancecard)
                .copy(Bitmap.Config.ARGB_8888, true);

        Typeface tf = ResourcesCompat.getFont(InsuranceInfoActivity.this, R.font.swcc);

        //Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTypeface(tf);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        paint.setTextSize(convertToPixels(InsuranceInfoActivity.this, 58));

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
        paint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText("Name: "+insuranceInfo.getFirstName()+" "+insuranceInfo.getFatherName()+" "+insuranceInfo.getFamilyName(),215, 700, paint);
        canvas.drawText("PIN: "+insuranceInfo.getMedgulfPIN(),215, 900, paint);
        canvas.drawText("REL: "+insuranceInfo.getRel(),215, 1100, paint);
        canvas.drawText("Birth Date: "+insuranceInfo.getBirdthDateAsDmy(),215, 1300, paint);
        canvas.drawText("Policy: "+insuranceInfo.getPolicyNo(),215, 1700, paint);
        canvas.drawText("ID: "+insuranceInfo.getOfficialID(),215, 1900, paint);
        canvas.drawText("Category: "+insuranceInfo.getCategory(),215, 2100, paint);
        canvas.drawText("EMP ID: "+insuranceInfo.getEmployeeID(),2290, 1100, paint);
        canvas.drawText(""+insuranceInfo.getG(),2290, 1300, paint);
        canvas.drawText("Expiry date: "+insuranceInfo.getEffectiveDateAsDmy(),2290, 1700, paint);
        canvas.drawText("> SAR 1000",2290, 1900, paint);
        paint.setTextSize(convertToPixels(InsuranceInfoActivity.this, 70));
        canvas.drawText("800-166-2700",2820, 2610, paint);




//        canvas.drawText(global.GetValue("Username").replaceAll("u",""),3294, 1350, paint);
//        canvas.drawText(IdentityNo,3294, 1450, paint);
//        canvas.drawText(PositionAr,3294, 1550, paint);
//        canvas.drawText(NatioAr,3294, 1650, paint);
//        canvas.drawText(JoinDate,3294, 1750, paint);
//        canvas.drawText("الموقع : "+per.getResultObject().getLocationAr(),3780, 798, paint);
//        canvas.drawText("الإدارة : "+DepartmentAr,3780, 868, paint);
//
//
//        Calendar Current=Calendar.getInstance();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//
//        canvas.drawText(sdf.format(Current.getTime()),3400, 470, paint);
//
//
//        //English Name
//
//        paint.setTextAlign(Paint.Align.LEFT);
//        canvas.drawText(EmployeeName,670, 1270, paint);
//        canvas.drawText(global.GetValue("Username").replaceAll("u",""),670, 1350, paint);
//        canvas.drawText(IdentityNo,670, 1450, paint);
//        canvas.drawText(Position,670, 1550, paint);
//        canvas.drawText(Nationality,670, 1650, paint);
//        canvas.drawText(JoinDate,670, 1750, paint);
//        canvas.drawText("Location : "+per.getResultObject().getLocationEn(),50, 798, paint);
//        canvas.drawText("Department : "+Department,50, 868, paint);
//        float x=(float)1860.0;
//        float y=(float)0;
//
//
//
//        for (int i=0;i<BArabic.size();i++){
//            canvas.drawBitmap(writeTextOnRow(BArabic.get(i),BEnglish.get(i),BValue.get(i)),y,x+((i)*98),null);
//        }
//        canvas.drawBitmap(writeTextOnEnd(),y,x+((BArabic.size())*98)-3,null);



        return new BitmapDrawable(InsuranceInfoActivity.this.getResources(), bm);
    }


    public static int convertToPixels(Context context, int nDP)
    {
        final float conversionScale = context.getResources().getDisplayMetrics().density;

        return (int) ((nDP * conversionScale) + 0.5f) ;

    }
}