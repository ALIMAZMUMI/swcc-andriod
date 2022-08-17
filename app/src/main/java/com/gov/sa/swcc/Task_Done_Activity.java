package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gov.sa.swcc.model.CompleteTask.CompleteTask;
import com.gov.sa.swcc.model.CompleteTask.EmployeeAttachment;
import com.gov.sa.swcc.model.ExtendTask.ExtendTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Task_Done_Activity extends AppCompatActivity {
    TextView removeimagetxt,addimagetxt;
    ImageView removeimage;
    ImageView addimage;
    int Image=0;

    Global global;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_done);

        global=new Global(Task_Done_Activity.this);

        EditText detials=(EditText)findViewById(R.id.done_detials);
        Button submit=(Button)findViewById(R.id.submit);
        Button cancel=(Button)findViewById(R.id.cancel);
        submit.setEnabled(false);
        ID=getIntent().getExtras().getString("ID");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetTaskAddTime(ID,detials.getText().toString());
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task_Done_Activity.this.finish();
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
                if(detials.getText().length()>2){
                    submit.setEnabled(true);
                    submit.setBackgroundResource(R.drawable.blueroundfull);
                }else {
                    submit.setEnabled(false);
                    submit.setBackgroundResource(R.drawable.grayroundbtn);
                }
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


    }

    private void GetTaskAddTime(String ID,String Comment) {
        String Token=global.GetValue("TaskToken");

        CompleteTask completeTask=new CompleteTask();
        completeTask.setEmpTaskId(Integer.parseInt(ID));
        completeTask.setComment(Comment);
        if(Image==1)
        {
            EmployeeAttachment employeeAttachment=new EmployeeAttachment();
            employeeAttachment.setName("مرفق");
            employeeAttachment.setFileExtension("jpg");
            employeeAttachment.setImageBase(ConvertToBase64());
            List<EmployeeAttachment> employeeAttachments=new ArrayList<>();
            employeeAttachments.add(employeeAttachment);
            completeTask.setEmployeeAttachments(employeeAttachments);
        }else{

            EmployeeAttachment employeeAttachment=new EmployeeAttachment();
            List<EmployeeAttachment> employeeAttachments=new ArrayList<>();
            //employeeAttachments.add(employeeAttachment);
            completeTask.setEmployeeAttachments(employeeAttachments);
        }

        Call<ExtendTask> call = RetrofitClient.getInstance(Api.TaskURL).getMyApi().CompleteTask(Token,completeTask);
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
                        global.ShowMessageNF(response.body().getMessage(),Task_Done_Activity.this);


                    }else {
                        dialog.dismiss();
                        global.ShowMessage("");
                    }

                }else {try {
                    global.ShowMessageNF(response.body().getMessage(),Task_Done_Activity.this);
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
                Toast.makeText(Task_Done_Activity.this,
                        "Photo file can't be created, please try again",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            Uri photoURI = FileProvider.getUriForFile(Task_Done_Activity.this,
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

                                    addimagetxt.setVisibility(View.GONE);
                                    addimage.setVisibility(View.GONE);
                                    removeimage.setVisibility(View.VISIBLE);
                                    removeimagetxt.setVisibility(View.VISIBLE);
                                    Image=1;
                                    Log.d("img123", "true");


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