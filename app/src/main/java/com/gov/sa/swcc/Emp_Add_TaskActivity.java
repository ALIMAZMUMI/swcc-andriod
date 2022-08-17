package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.gov.sa.swcc.model.CompleteTask.CompleteTask;
import com.gov.sa.swcc.model.CompleteTask.EmployeeAttachment;
import com.gov.sa.swcc.model.CreateTask.CreateTask;
import com.gov.sa.swcc.model.CreateTask.CreateTaskResponce;
import com.gov.sa.swcc.model.CreateTask.UploadAttachment;
import com.gov.sa.swcc.model.ExtendTask.ExtendTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Emp_Add_TaskActivity extends AppCompatActivity {
EditText Task_Titel,detials;
TextView date;
    LinearLayout addemp;
    final Calendar myCalendar= Calendar.getInstance();
    final Calendar Current= Calendar.getInstance();

    TextView removeimagetxt,addimagetxt;
    ImageView removeimage;
    ImageView addimage;
    LinearLayout emplist;
    Global global;
    static List<String> uid;
    static List<String> Name;
    ChipGroup empchips;
    Button submit;

    int Image=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_add_task);


        global=new Global(Emp_Add_TaskActivity.this);
        Task_Titel=(EditText)findViewById(R.id.Task_Titel);
        detials=(EditText)findViewById(R.id.detials);
        date=(TextView) findViewById(R.id.date);
        addemp=(LinearLayout) findViewById(R.id.addemp);
        emplist=(LinearLayout)findViewById(R.id.emplist);
        empchips=(ChipGroup)findViewById(R.id.empchips);
        submit=(Button)findViewById(R.id.submit);
        submit.setEnabled(false);
        uid=new ArrayList<>();




        ((ImageView)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEmpTask();
            }
        });
        addemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//int item=0;
//for (int i=0;i<emplist.getChildCount();i++){
//    if(emplist.getChildAt(i).){
//
//    }
//    if(((LinearLayout)emplist.getChildAt(i)).getChildCount()<2){
//        item++;
//        TextView valueTV = new TextView(Emp_Add_TaskActivity.this);
//        valueTV.setText("علي المزمومي");
//        valueTV.setTextSize(12);
//        valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));
//        valueTV.setTextColor(Color.parseColor("#0066CC"));
//        valueTV.setBackgroundResource(R.drawable.ligthblueborder);
//        valueTV.setGravity(Gravity.CENTER);
//
//        ((LinearLayout)emplist.getChildAt(i)).addView(valueTV);
//
//    }
//
//}
//
//if(item==0){
//
//    TextView valueTV = new TextView(Emp_Add_TaskActivity.this);
//    valueTV.setText("علي المزمومي");
//    valueTV.setTextSize(12);
//    valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT));
//    valueTV.setTextColor(Color.parseColor("#0066CC"));
//    valueTV.setBackgroundResource(R.drawable.ligthbluefull);
//    valueTV.setGravity(Gravity.CENTER);
//LinearLayout hor=new LinearLayout(Emp_Add_TaskActivity.this);
//hor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT));
//    hor.setGravity(Gravity.CENTER|Gravity.RIGHT);
//    hor.addView(valueTV);
//    hor.setOrientation(LinearLayout.HORIZONTAL);
//    emplist.addView(hor);
//
//}






startActivityForResult(new Intent(Emp_Add_TaskActivity.this,Task_Add_EmpActivity.class),1400);


            }
        });

        DatePickerDialog.OnDateSetListener date1 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                String myFormat="yyyy-MM-dd";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                date.setText(dateFormat.format(myCalendar.getTime()));
                CheckSubmit();
            }
        };
date.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        new DatePickerDialog(Emp_Add_TaskActivity.this,date1,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }
});


        removeimagetxt=(TextView) findViewById(R.id.removeimagetxt);
        addimagetxt=(TextView)findViewById(R.id.addimagetxt);
        addimage=(ImageView) findViewById(R.id.addimage);
        removeimage=(ImageView) findViewById(R.id.removeimage);
        removeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addimagetxt.setVisibility(View.VISIBLE);
                addimage.setVisibility(View.VISIBLE);
                removeimage.setVisibility(View.GONE);
                removeimagetxt.setVisibility(View.GONE);
                Image=0;
                CheckSubmit();

            }
        });
        addimagetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStoragePermissionGranted()){
                    openCamera();
                }
            }
        });



        Task_Titel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CheckSubmit();
            }
        });
        detials.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CheckSubmit();
            }
        });


    }


    public void CheckSubmit(){
        if(Task_Titel.getText().toString().length()>3&&
        detials.getText().toString().length()>3&&
        uid.size()>0&&
        date.getText().toString().contains("-")){
            submit.setBackgroundResource(R.drawable.blueroundfull);
            submit.setEnabled(true);
        }else {
            submit.setBackgroundResource(R.drawable.grayroundbtn);
            submit.setEnabled(false);
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
                Toast.makeText(Emp_Add_TaskActivity.this,
                        "Photo file can't be created, please try again",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            Uri photoURI = FileProvider.getUriForFile(Emp_Add_TaskActivity.this,
                    "com.gov.sa.swcc.fileprovider",
                    pictureFile);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(cameraIntent, 1001);
        }
    }





    private void CreateEmpTask() {
        String Token=global.GetValue("TaskToken");

        CreateTask completeTask=new CreateTask();
        completeTask.setCreatedBy(global.GetValue("Username").replaceAll("u",""));
        completeTask.setTaskName(Task_Titel.getText().toString());
        completeTask.setFrom(Current.get(Calendar.YEAR)+"-"+(Current.get(Calendar.MONTH)+1)+"-"+Current.get(Calendar.DAY_OF_MONTH));
        completeTask.setTo(date.getText().toString());
        completeTask.setTaskDecription(detials.getText().toString());
        completeTask.setUids(uid);
        if(Image==1)
        {
            UploadAttachment employeeAttachment=new UploadAttachment();
            employeeAttachment.setName("مرفق");
            employeeAttachment.setFileExtension("jpg");
            employeeAttachment.setImageBase(ConvertToBase64());
            List<UploadAttachment> employeeAttachments=new ArrayList<>();
            employeeAttachments.add(employeeAttachment);
            completeTask.setUploadAttachments(employeeAttachments);
        }else{

            UploadAttachment employeeAttachment=new UploadAttachment();
            List<UploadAttachment> employeeAttachments=new ArrayList<>();
           // employeeAttachments.add(employeeAttachment);
            completeTask.setUploadAttachments(employeeAttachments);
        }

        Call<CreateTaskResponce> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().CreateTasks(Token,completeTask);
        PorgressDilog dialog =  new PorgressDilog(this);
        dialog.show();
        call.enqueue(new Callback<CreateTaskResponce>() {
            @Override
            public void onResponse(Call<CreateTaskResponce> call, Response<CreateTaskResponce> response) {
                Log.d("Resp",response.message()+"");
                if(response.isSuccessful())
                {

                    if(response.body().getStatusCode()==1){
                        dialog.dismiss();
                        Intent intent=new Intent();
                        intent.putExtra("update",true);
                        setResult(1002,intent);
                        global.ShowMessageNF(response.body().getMessage(),Emp_Add_TaskActivity.this);


                    }else {
                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CreateTaskResponce>call, Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
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

                                addimagetxt.setVisibility(View.GONE);
                                addimage.setVisibility(View.GONE);
                                removeimage.setVisibility(View.VISIBLE);
                                removeimagetxt.setVisibility(View.VISIBLE);
                                Image=1;
                                Log.d("img123", "true");
CheckSubmit();

                            }
                        }catch (Exception e){

                        }
                    }
                    break;

                case 1400:
                   EmpList();

                    break;




            }
        }
    }


    public void EmpList(){
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed
        };

        int[] colors = new int[] {
                Color.parseColor("#1C0066CC"),
                Color.parseColor("#1C0066CC"),
                Color.parseColor("#1C0066CC"),
                Color.parseColor("#1C0066CC"),
        };

        ColorStateList myList = new ColorStateList(states, colors);
        empchips.removeAllViews();
        for (int i=0;i<Name.size();i++) {
            Chip chip = new Chip(empchips.getContext());
            chip.setText(Name.get(i));
            chip.setChipBackgroundColorResource(R.color.white);
            chip.setCloseIconVisible(true);
            chip.setTextColor(Color.parseColor("#004C86"));
            chip.setChipStrokeColor(myList);
            chip.setChipStrokeWidth(1);
chip.setTag(i+"");
            chip.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            chip.setChipIconResource(R.drawable.personicon);
            chip.setCloseIconResource(R.drawable.closes);
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uid.remove(Integer.parseInt(view.getTag().toString()));
                    Name.remove(Integer.parseInt(view.getTag().toString()));
                    EmpList();
                }
            });


            //chip.setTextAppearance(R.style.ChipTextAppearance);
            empchips.addView(chip);
        }

        CheckSubmit();

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