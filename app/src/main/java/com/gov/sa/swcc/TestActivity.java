package com.gov.sa.swcc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class TestActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test_activity);
//    }

    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    //    private EditText editText;
    private EditText output;
    private Button micButton;
    private boolean isActive = false;
    private int maxLength = 200; //for test

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }

//        editText = findViewById(R.id.text);
        output = findViewById(R.id.output);
        micButton = findViewById(R.id.button);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ar");// Locale.getDefault());
//        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);


        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);


        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 60000); // value to wait
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 60000); // value to wait


        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                Log.i("-----","1");
//                output.setText(""+"\n"+output.getText().toString());
            }

            @Override
            public void onBeginningOfSpeech() {
                Log.i("-----","2");
//                editText.setText("");
//                editText.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {
//                Log.i("-----","3");
            }

            @Override
            public void onBufferReceived(byte[] bytes) {
                Log.i("-----","4");
            }

            @Override
            public void onEndOfSpeech() {
                Log.i("-----","5");
            }

            @Override
            public void onError(int i) {
                Log.i("-----","6");
                isActive = false;
                micButton.setText("صوتي");
            }

            @Override
            public void onResults(Bundle bundle) {
                Log.i("-----","7");

//                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                if(data !=null && data.size()>0) {
//                    String newStr = data.get(0);
//                    String lastStr = output.getText().toString();
//                    String fullStr = lastStr + " " + newStr;
//                    if (fullStr.length() <= maxLength) {
//                        output.setText(fullStr);
//                    }
//                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {
                Log.i("-----","8");

                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String newStr =  data.get(0);
                String lastStr = output.getText().toString();
                if(!lastStr.endsWith(newStr)){
                    String fullStr = lastStr+" "+newStr;
                    if(fullStr.length() <= maxLength) {
                        output.setText(fullStr);
                    }
                }
                Log.i("-----:",data.size()+"onPR:"+newStr);
            }

            @Override
            public void onEvent(int i, Bundle bundle) {
                Log.i("-----","9");
            }
        });

        micButton.setOnClickListener(v -> {
            if(isActive){
                speechRecognizer.stopListening();
                micButton.setText("صوتي");
            }else{
                speechRecognizer.startListening(speechRecognizerIntent);
                micButton.setText("تحدث");
            }
            isActive = !isActive;
        });
//        micButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
//                    speechRecognizer.stopListening();
//                    micButton.setText("صوتي");
//                }
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
////                    micButton.setImageResource(R.drawable.plus);//ic_mic_black_24dp
//                    speechRecognizer.startListening(speechRecognizerIntent);
//                    micButton.setText("تحدث");
//                }
//                return false;
//            }
//        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }

}