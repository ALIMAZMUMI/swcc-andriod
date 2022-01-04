package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.gov.sa.swcc.model.PersonalResult;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRCodeActivity extends AppCompatActivity {
Global global;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        global=new Global(QRCodeActivity.this);
        PersonalResult per=global.GetPData("PersonalResult");
        data="BEGIN:VCARD\n" +
                "\n" +
                "VERSION:3.0\n" +
                "\n" +
                "N:"+per.getResultObject().getLastNameEn()+";"+per.getResultObject().getFirstNameEn()+"\n" +
                "\n" +
                "FN:"+per.getResultObject().getFirstNameEn()+ " "+per.getResultObject().getLastNameEn()+"\n" +
                "\n" +
                "TEL;CELL:+"+per.getResultObject().getMobile()+"\n" +
                "\n" +
                "TITLE:"+per.getResultObject().getTitle()+"\n" +
                "\n" +
                "TEL;WORK;VOICE:\n" +
                "\n" +
                "EMAIL:\n" +
                "\n" +
                "ORG:Saline Water Conversion Corporation (SWCC)\n" +
                "\n" +
                "END:VCARD";

        ImageView qrcode=(ImageView) findViewById(R.id.qrcode);

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
           QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, dimen);
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