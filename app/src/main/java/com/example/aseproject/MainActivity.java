package com.example.aseproject;

//package com.example.android.testing.espresso.IntentsBasicSample;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {


    public static final String VALID_MESSAGE = "Hello World!";
    Button but1 ;
    ImageButton Mic;
    EditText edtxt;


    private static final int REQUEST_CODE_PICK = 16;
    //private static String VALID_MESSAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        but1 = (Button)findViewById(R.id.sendbutton1);
        Mic = (ImageButton)findViewById(R.id.mic);
        edtxt =(EditText)findViewById(R.id.et1) ;
        but1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //edtxt.setHint("enter message");
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("arg",String.valueOf(edtxt.getText()).trim());
                startActivityForResult(i, REQUEST_CODE_PICK);
                //startActivity(i);
            }
        });

    }

    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
       // if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //Toast.makeText(getApplicationContext(), " printed", Toast.LENGTH_LONG).show();
                edtxt.setText(result.get(0));
                edtxt.setSelection(edtxt.getText().length());
            }
       // }
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  Toast.makeText(getApplicationContext(), "onStart called", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {

        super.onResume();
       // Toast.makeText(getApplicationContext(), "onResumed called", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {

        super.onPause();
        super.onResume();
       // Toast.makeText(getApplicationContext(), "onPause called", Toast.LENGTH_LONG).show();

    }


}
//42
/*String outputFile;
    private MediaRecorder myAudioRecorder;
    long ButtDown = 0 ,ButtUp = 0;*/
//take permission for to access the audio recorder
    /*private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }*/
//72
//ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
//Complete code for audio recorder , and save audio file to external storege.
       /* // create a new button to play the audio
        but2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String value="1";
                edtxt.setText("@audio");

                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        // Record to the external cache directory for visibility
        outputFile = getExternalCacheDir().getAbsolutePath();
        File outFile=new File(outputFile);
        outputFile += "/audiorecordtest.3gp";
        // Take your voice input and save it external storage
        Mic.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub


                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        ButtDown = System.currentTimeMillis();
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            Log.e(LOG_TAG, "Delay makes sense ");
                        }
                        else {
                            Log.e(LOG_TAG, "here is the time when button pressed" + ButtDown);
                        try {
                                myAudioRecorder = new MediaRecorder();
                                myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                                myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                                myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                                myAudioRecorder.setOutputFile(outputFile);
                                myAudioRecorder.prepare();
                                myAudioRecorder.start();
                                prepareNotCalled = FALSE;
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.e(LOG_TAG, "prepare() failed");
                            } catch (IllegalStateException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                Log.e(LOG_TAG, "start() failed");
                            }

                            Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_SHORT).show();

                        }
                            return true;


                    case MotionEvent.ACTION_UP:


                            Log.e(LOG_TAG, "I'm here3");
                            try {
                                myAudioRecorder.stop();

                            } catch (IllegalStateException ise) {
                                outFile.delete();
                                ise.printStackTrace();
                                Log.e(LOG_TAG, "stop() failed");
                            }
                            myAudioRecorder.release();
                            myAudioRecorder = null;
                            Toast.makeText(getApplicationContext(), "Audio Recorded successfully", Toast.LENGTH_SHORT).show();
                            edtxt.setText("@audio");
                            break;
                        }


                return false;
            }
        });*/
