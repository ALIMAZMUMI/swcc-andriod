package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.gov.sa.swcc.model.PersonalResult;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class EmpCardActivity extends Activity {

    Global global;
    TextView Empdept,EmpPic,EmpName,EmpID,EmpNameEn,EmpNat,EmpNatID,EmpBlood,JobTitle,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_card);
        this.setFinishOnTouchOutside(false);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        global=new Global(EmpCardActivity.this);

        Empdept=(TextView)findViewById(R.id.Empdept);
        EmpPic=(TextView)findViewById(R.id.EmpPic);
        EmpName=(TextView)findViewById(R.id.EmpName);
        EmpID=(TextView)findViewById(R.id.EmpID);
        EmpNameEn=(TextView)findViewById(R.id.EmpNameEn);
        EmpNat=(TextView)findViewById(R.id.EmpNat);
        EmpNatID=(TextView)findViewById(R.id.EmpNatID);
        EmpBlood=(TextView)findViewById(R.id.EmpBlood);
        JobTitle=(TextView)findViewById(R.id.JobTitle);
        back=(TextView)findViewById(R.id.back);



        PersonalResult per=global.GetPData("PersonalResult");

        Empdept.setText(per.getResultObject().getDepartment());
        EmpName.setText(per.getResultObject().getFullName());
        EmpID.setText(global.GetValue("Username").replaceAll("u",""));
        EmpNameEn.setText(per.getResultObject().getFirstNameEn()+" "+per.getResultObject().getMiddleNameEn()+" "+per.getResultObject().getLastNameEn());
        EmpNat.setText(per.getResultObject().getNationality());
        EmpNatID.setText(per.getResultObject().getNationalId());
        EmpBlood.setText("غير معرف");
        JobTitle.setText(per.getResultObject().getTitle());

        byte[] decodedString = Base64.decode(per.getResultObject().getPhoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Drawable d = new BitmapDrawable(getResources(), decodedByte);
        EmpPic.setBackground(d);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });





        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });



        ImageView qrcode=(ImageView) findViewById(R.id.textView2);

        // below line is for getting
        // the windowmanager service.
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        QRGEncoder qrgEncoder = new QRGEncoder(global.GetValue("Username").replaceAll("u",""), null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            qrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("QR++", e.toString());
        }

    }


}
