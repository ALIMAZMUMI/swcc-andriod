package com.gov.sa.swcc;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class PorgressDilog extends Dialog {

    public Activity c;
    public Dialog d;

    public PorgressDilog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.progressloading);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
setCancelable(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
