package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {
String header;
int index,Round;


    int Image=0;

    ImageView addimage;

    TextView removeimagetxt,addimagetxt;
    ImageView removeimage;
    RadioButton radioButton1,radioButton2,radioButton3;
    Button submit,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        header=getIntent().getStringExtra("header");
        index=getIntent().getIntExtra("index",-1);
Round=getIntent().getIntExtra("Round",-1);
        TextView header=(TextView)findViewById(R.id.header);
        header.setText(""+this.header);
        EditText detials=(EditText)findViewById(R.id.detials);


        submit=(Button)findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detials.getText().length()>0){
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("index",index);
                    returnIntent.putExtra("Round",Round);

                    returnIntent.putExtra("details",detials.getText().toString());
                    String radio="1";
                    if(radioButton1.isChecked()){
                        radio="1";
                    }
                    if(radioButton2.isChecked()){
                        radio="2";
                    }
                    if(radioButton3.isChecked()){
                        radio="3";
                    }
                    returnIntent.putExtra("radio",radio);
                    if(Image==1){
                        returnIntent.putExtra("Image",ConvertToBase64());
                    }else {
                        returnIntent.putExtra("Image","");

                    }
                    setResult(1001,returnIntent);
                    finish();
                }else {


                }
            }
        });
        cancel=(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(AddNoteActivity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
        radioButton1=(RadioButton) findViewById(R.id.radioButton1);
        radioButton2=(RadioButton) findViewById(R.id.radioButton2);

        radioButton3=(RadioButton) findViewById(R.id.radioButton3);

        addimage=(ImageView) findViewById(R.id.addimage);
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStoragePermissionGranted()){
                    openCamera();
                }
            }
        });


        removeimagetxt=(TextView) findViewById(R.id.removeimagetxt);
        addimagetxt=(TextView)findViewById(R.id.addimagetxt);
        removeimage=(ImageView) findViewById(R.id.removeimage);
        removeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addimagetxt.setVisibility(View.VISIBLE);
                addimage.setVisibility(View.VISIBLE);
                removeimage.setVisibility(View.GONE);
                removeimagetxt.setVisibility(View.GONE);
                Image=0;

            }
        });



    }


    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Swcc","Permission is granted");
                return true;
            } else {

                Log.v("SWCC","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("SWCC","Permission is granted");
            return true;
        }
    }

    private String ConvertToBase64() {
//        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        BitmapDrawable drawable = (BitmapDrawable) addimage.getDrawable();
        Bitmap bm = drawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Log.d("Image Size", "" + bm.getByteCount());
        int fileSizeInKB = bm.getByteCount() / 1024;
        Log.d("Image Size in KB", "" + fileSizeInKB);

        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        int fileSizeInMB = fileSizeInKB / 1024;
        Log.d("Image Size in MB", "" + fileSizeInMB);
        if (fileSizeInMB < 8) {
            bm.compress(Bitmap.CompressFormat.JPEG, 7, baos);
        } else if (fileSizeInMB < 20) {
            bm.compress(Bitmap.CompressFormat.JPEG, 3, baos);
        } else {
            bm.compress(Bitmap.CompressFormat.JPEG, 1, baos);
        }
//        int lnth = bm.getByteCount();
//        ByteBuffer dst = ByteBuffer.allocate(lnth);
//        bm.copyPixelsToBuffer(dst);
//        byte[] barray = dst.array();

        byte[] byteArrayImage = baos.toByteArray();

        return Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
    }


    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //cameraIntent.putExtra( MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File pictureFile = null;
            try {
                pictureFile = getPictureFile();
            } catch (IOException ex) {
                Toast.makeText(AddNoteActivity.this,
                        "Photo file can't be created, please try again",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            Uri photoURI = FileProvider.getUriForFile(AddNoteActivity.this,
                    "com.gov.sa.swcc.fileprovider",
                    pictureFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(cameraIntent, 1001);
        }
    }

    String pictureFilePath;


    private File getPictureFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "SWCC_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(pictureFile, ".jpg", storageDir);
        pictureFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 1001:
                    if (resultCode == RESULT_OK) {
                        try {
                            File imgFile = new File(pictureFilePath);
                            Log.d("img", "" + pictureFilePath);

                            if (imgFile.exists()) {
                                Log.d("img", "true");
                                //                            imageView.setImageURI(Uri.fromFile(imgFile));
                                try {
                                    addimagetxt.setVisibility(View.GONE);
                                    addimage.setVisibility(View.GONE);
                                    removeimage.setVisibility(View.VISIBLE);
                                    removeimagetxt.setVisibility(View.VISIBLE);
                                    Image=1;
                                }catch (OutOfMemoryError ex){
                                }
                            }
                        }catch (Exception e){

                        }
                    }
                    break;




            }
        }
    }





}