package com.gov.sa.swcc;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gov.sa.swcc.model.LoginResult;

import java.nio.charset.StandardCharsets;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

Global global;
    public Login() {
        // Required empty public constructor
    }

EditText user,pass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

global=new Global(getContext());
        View view= inflater.inflate(R.layout.fragment_login, container, false);

        user=(EditText)view.findViewById(R.id.user);
        pass=(EditText)view.findViewById(R.id.password);



        Button login=(Button)view.findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(!global.GetPData("PersonalResult").equals("")){

    HomeInfo nextFrag= new HomeInfo();
    Bundle bundle = new Bundle();
    nextFrag.setArguments(bundle);
    getActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.main_container, nextFrag, "findThisFragment")
            .addToBackStack(null)
            .commit();

}else {
    if (user.getText().toString().length() > 0 && pass.getText().toString().length() > 0) {

        CallLogin();
    }

}
            }
        });





        return view;
    }


    private void CallLogin() {



//        global.SaveValue("Username","u190250");
//        global.SaveValue("Password","SA.RUH.1409");
//        Otp nextFrag= new Otp();
//
//        Bundle bundle = new Bundle();
//        bundle.putString("Mobile", "0568935924");
//        nextFrag.setArguments(bundle);
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_container, nextFrag, "findThisFragment")
//                .addToBackStack(null)
//                .commit();


        byte[] data = pass.getText().toString().getBytes(StandardCharsets.UTF_8);
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);

        Log.d("base64",base64+"");

        Call<LoginResult> call = RetrofitClient.getInstance().getMyApi().Login("u"+user.getText().toString().trim(),base64.trim(),"null");
        ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "يرجى الإنتظار", true);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Response<LoginResult> response, Retrofit retrofit) {
                Log.d("Resp",response.message()+"");
                dialog.dismiss();
        if(response.isSuccess())
        {
            if(response.body().getResultMessage().equals("Success: Message has been sent")){

                global.SaveValue("Username","u"+user.getText().toString().trim());
                global.SaveValue("Password",pass.getText().toString());


                Otp nextFrag= new Otp();
                Bundle bundle = new Bundle();
                bundle.putString("Mobile", response.body().getMoreDetails());
                nextFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();

               // ShowMessage("تم تسجيل الدخول");
            }else {
                ShowMessage("الرقم الوظيفي و كلمة المرور غير صحيحة");

            }

        }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                Log.d("Reeeeeeeeeee",t.getMessage()+"");

            }


        });
    }


public void ShowMessage(String Message){



    AlertDialog alertDialog1 = new AlertDialog.Builder(
            getActivity()).create();

    // Setting Dialog Title
    alertDialog1.setTitle("");

    // Setting Dialog Message
    alertDialog1.setMessage(Message);

    alertDialog1.setButton("موافق", new DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialog, int which) {

        }
    });

    // Showing Alert Message
    alertDialog1.show();

}
//    @Override
//    public void onResponse(Response<LoginResult> response, Retrofit retrofit) {
//        Log.d("Resp",response.isSuccess()+"");
//
//        if(response.isSuccess())
//        {
//            Log.d("URLCall",response.body().toString());
//
//        }
//
//
//    }
//
//    @Override
//    public void onFailure(Throwable t) {
//
//    }
}
