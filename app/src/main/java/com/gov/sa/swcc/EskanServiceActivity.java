package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gov.sa.swcc.model.PersonalResult;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EskanServiceActivity extends AppCompatActivity {

    ImageView addimage;
    EditText detials;
    Global global;
    int Image=0;
    Button submit;
    TextView removeimagetxt,addimagetxt;
    ImageView removeimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eskan_service);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


        TextView Header=(TextView)findViewById(R.id.header);
        String text = "<font color=#004C86>الدعم الفني /</font> <font color=#0066CC>خدمات الإسكان</font>";
        Header.setText(Html.fromHtml(text));





       // TextView back=(TextView)findViewById(R.id.back);
        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        });
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
//            }
//        });



        global=new Global(EskanServiceActivity.this);
        Spinner servicetype = (Spinner) findViewById(R.id.servicetype);
        Spinner city = (Spinner) findViewById(R.id.city);

        // Spinner click listener

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("خدمات الصيانة");
        categories.add("خدمات النظافة ومكافحة الحشرات");
        categories.add("طلب الأجهزة و المواد");
        categories.add("راصد");
        categories.add("إختر نوع الخدمة");


        List<String> citystring = new ArrayList<String>();
        citystring.add("إسكان راس الخير");
        citystring.add("إسكان الخبر");
        citystring.add("إسكان الجبيل");
        citystring.add("إسكان الشقيق");
        citystring.add("إسكان الشعيبة");
        citystring.add("إسكان جدة");
        citystring.add("إسكان وحدات الإنتاج");
        citystring.add("إسكان ينبع");
        citystring.add("إسكان بنقل الجبيل الرياض القصيم");
        citystring.add("إسكان العليا المركز الرئيسي");
        citystring.add("إسكان التخصصي المركز الرئيسي");
        citystring.add("إختر المدينة");


        // Creating adapter for spinner
        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<String>(this, R.layout.spinnericon,R.id.spinneritem, categories)
        {
            public View getDropDownView(int position, View convertView, ViewGroup parent) {



                return super.getDropDownView(position , convertView, parent);
            }



            public int getCount() {
                return categories.size() - 1;
            }
        };



        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, R.layout.spinnericon,R.id.spinneritem, citystring)
        {
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                return super.getDropDownView(position , convertView, parent);
            }

            public int getCount() {
                return citystring.size() - 1;
            }
        };

        // Drop down layout style - list view with radio button

        // attaching data adapter to spinner
        servicetype.setAdapter(serviceAdapter);
        city.setAdapter(cityAdapter);

        servicetype.setSelection(categories.size()-1);
        city.setSelection(citystring.size()-1);



        servicetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==categories.size()-1)
                ((TextView) adapterView.getChildAt(0).findViewById(R.id.spinneritem)).setTextColor(Color.parseColor("#CACCCE"));
                if(detials.getText().length()>3&&servicetype.getSelectedItemPosition()!=servicetype.getAdapter().getCount()
                        &&city.getSelectedItemPosition()!=city.getAdapter().getCount()){
                    submit.setBackgroundResource(R.drawable.blueroundfull);
                    submit.setEnabled(true);
                }else{
                    submit.setBackgroundResource(R.drawable.grayroundbtn);
                    submit.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==citystring.size()-1)
                    ((TextView) adapterView.getChildAt(0).findViewById(R.id.spinneritem)).setTextColor(Color.parseColor("#CACCCE"));
                if(detials.getText().length()>3&&servicetype.getSelectedItemPosition()!=servicetype.getAdapter().getCount()
                        &&city.getSelectedItemPosition()!=city.getAdapter().getCount()){
                    submit.setBackgroundResource(R.drawable.blueroundfull);
                    submit.setEnabled(true);
                }else{
                    submit.setBackgroundResource(R.drawable.grayroundbtn);
                    submit.setEnabled(false);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







         addimage=(ImageView) findViewById(R.id.addimage);
         addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStoragePermissionGranted()){
                    openCamera();
                }
         }
        });

        detials=(EditText)findViewById(R.id.detials);
         submit=(Button) findViewById(R.id.submit);

detials.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//
                if(detials.getText().length()>3&&servicetype.getSelectedItemPosition()!=servicetype.getAdapter().getCount()
                        &&city.getSelectedItemPosition()!=city.getAdapter().getCount()){
                    submit.setBackgroundResource(R.drawable.blueroundfull);
                    submit.setEnabled(true);
                }else{
                    submit.setBackgroundResource(R.drawable.grayroundbtn);
                    submit.setEnabled(false);
                }
            }
        });
        submit.setBackgroundResource(R.drawable.grayroundbtn);
        submit.setEnabled(false);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(global.CheckInternet(EskanServiceActivity.this)) {
                }else if(servicetype.getSelectedItemPosition()==(categories.size()-1)||city.getSelectedItemPosition()==(citystring.size()-1) || detials.getText().toString().length()==0){
                    global.ShowMessage("ارجو اكمال البيانات المطلوبة");
                }else {
try {
    if(Image==0)
        ISCom("",categories.get(servicetype.getSelectedItemPosition()),citystring.get(city.getSelectedItemPosition()),"");
    else
        ISCom("",categories.get(servicetype.getSelectedItemPosition()),citystring.get(city.getSelectedItemPosition()),ConvertToBase64());

}catch (Exception e){

}


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


    private void ISCom(String Title,String TOR,String City,String Image12) throws IOException, XmlPullParserException {


        String user=global.GetValue("Username");
        PersonalResult per=global.GetPData("PersonalResult");

        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                try {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String Email=global.GetEmail(user);

                    String post="[{'UserId' : '"+user+"'," +
                            "'Description' : '"+detials.getText().toString()+"'," +
                            "'Phone' : '"+per.getResultObject().getMobile()+"'," +
                            "'FirstName' : '"+per.getResultObject().getFirstNameEn()+"'," +
                            "'LastName' : '"+per.getResultObject().getLastNameEn()+"'," +
                            "'Title' : '"+TOR+"'," +
                            "'Department' : '"+per.getResultObject().getDepartment()+"'," +
                            "'Email' : '"+Email+"'," +
                            "'City' : '"+per.getResultObject().getLocationAr()+"'," +
                            "'TypeRequest' : '"+TOR+"'," +
                            "'Location' : '"+City+"'," +
                            "'FileExtention' : 'jpg'," +
                            "'FileAttached' : '"+Image12+"'" +
                            "}]";
                    post=post.replaceAll("'","\"");

                    //Log.d("Ex------",post);
                    URL url = new URL("https://"+Api.Domain+"/GatewayControlPanel/FootPrint/CreateIndustrialSecurityRequest");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // Set timeout as per needs
                    connection.setConnectTimeout(60000);
                    connection.setReadTimeout(60000);

                    // Set DoOutput to true if you want to use URLConnection for output.
                    // Default is false
                    connection.setDoOutput(true);

                    connection.setUseCaches(true);
                    connection.setRequestMethod("POST");

                    // Set Headers
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("apitoken", "26EAADIT-310F-48E4-87C6-C827E73A4A00");

                    // Write XML
                    OutputStream outputStream = connection.getOutputStream();
                    byte[] b = post.getBytes("UTF-8");
                    outputStream.write(b);
                    outputStream.flush();
                    outputStream.close();

                    InputStream inputStream = connection.getInputStream();
                    dialog.dismiss();

                    Log.d("Ex------","33");


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            String line = "";
                            try {
                                line = bufferedReader.readLine();
                                if(connection.getResponseCode()==200){
                                    global.ShowMessageNF("رقم الطلب :"+line,EskanServiceActivity.this);
                                }else {
                                    global.ShowMessage("حدث مشكلة اثناء الاتصال");

                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });


                }catch (Exception ex){

                    Log.d("Ex--++",ex.toString());
                }

            }


        });

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
                Toast.makeText(EskanServiceActivity.this,
                        "Photo file can't be created, please try again",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            Uri photoURI = FileProvider.getUriForFile(EskanServiceActivity.this,
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
}