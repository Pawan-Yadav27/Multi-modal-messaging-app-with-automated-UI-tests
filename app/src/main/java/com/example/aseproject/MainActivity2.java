package com.example.aseproject;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.net.Proxy.Type.HTTP;

public class MainActivity2 extends AppCompatActivity //implements TextWatcher
 {

    EditText eText;
    Button sendButton;
    public static String passedMessage, input;
    int Flag = 0;
    //boolean isOnTextChanged = false , notValid = false ;
    //long delay = 1000; // 1
    //long last_text_edit = 0 , lastInvalid = 0 , lastValid = 0;
    //Handler handler = new Handler();
    private static final String LOG_TAG = "Check";
    static final String KEY_NUMBER = "key_number";
    static final String ErrorMessage = "Enter a valid Email address or phone number";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eText = (EditText)findViewById(R.id.et2);
        //eText.addTextChangedListener(this);
        sendButton = (Button)findViewById(R.id.sendbutton2);

        passedMessage = getIntent().getExtras().getString("arg");
        Log.e(LOG_TAG, passedMessage);


        sendButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                input = String.valueOf(eText.getText()).trim();
                if(isEmailValid(input)) {
                    Flag = 1;
                    Log.e(LOG_TAG, "5"+Flag);
                }

                else if(isNumberValid(input)){
                    Flag = 2;
                    Log.e(LOG_TAG, "7"+Flag);
                }
                else {
                    eText.setError(ErrorMessage);
                    Flag = 0;
                    Log.e(LOG_TAG, "8 "+Flag+" "+input);
                }

                if(Flag == 1) {

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                            Uri.fromParts("mailto", input, null));
                    //emailIntent.putExtra(Intent.EXTRA_SUBJECT, text);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, passedMessage);
                    startActivity(Intent.createChooser(emailIntent, "Send via.."));
                    Toast.makeText(getApplicationContext() , "Sending Email.." ,
                            Toast.LENGTH_SHORT).show();
                }
                if(Flag == 2) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                            Uri.fromParts("sms", input, null));
                    //emailIntent.putExtra(Intent.EXTRA_SUBJECT, text);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, passedMessage);
                    startActivity(Intent.createChooser(emailIntent, "Send via.."));
                    Toast.makeText(getApplicationContext(), "Sending SMS..",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


/*

    @VisibleForTesting
    static Intent createResultData() {
        final Intent resultData = new Intent();
        resultData.putExtra(KEY_NUMBER, passedMessage);
        return resultData;
    }
*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       // return super.onOptionsItemSelected(item);
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return false;
    }

    public static boolean isEmailValid(String email) {
        String expression = "[a-zA-Z0-9]+@([a-zA-Z0-9]+)(\\.[A-Z]{2,4}){1,2}";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isNumberValid(String email) {
        String expression = "([1-9]{1}[0-9]{9})";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /*    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        Log.e(LOG_TAG, "1");
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        isOnTextChanged = true;
        Log.e(LOG_TAG, "2");
    }

    @Override
    public void afterTextChanged(Editable editable) {

        Log.e(LOG_TAG, "3");
        String input = editable.toString().trim();
        if(isGmailValid(input) || isEmailValid(input) || isNumberValid(input)){
            notValid = false;
        }

        last_text_edit = System.currentTimeMillis();
        if( notValid && ((last_text_edit - lastInvalid) >2000) ){

            lastInvalid = last_text_edit;
            Toast.makeText(getApplicationContext() , "Input is invalid" ,
                    Toast.LENGTH_SHORT).show();
        }

        if ( !notValid && ((last_text_edit - lastValid) >2000)) {

            //last_text_edit = System.currentTimeMillis();
            //handler.postDelayed(input_finish_checker, delay);

            isOnTextChanged = false;
            if(isEmailValid(input)) {
                Toast.makeText(getApplicationContext() , "Email is Valid" ,
                        Toast.LENGTH_SHORT).show();
                lastValid = last_text_edit;
                Log.e(LOG_TAG, "5");
            }
            else if(isGmailValid(input)){
                Toast.makeText(getApplicationContext() , "Gmail is Valid" ,
                        Toast.LENGTH_SHORT).show();
                lastValid = last_text_edit;
                Log.e(LOG_TAG, "6");

            }
            else if(isNumberValid(input)){
                Toast.makeText(getApplicationContext() , "Number is Valid" ,
                        Toast.LENGTH_SHORT).show();
                lastValid = last_text_edit;
                Log.e(LOG_TAG, "7");
            }
            else {
                Toast.makeText(getApplicationContext(), "Input is not Valid",
                        Toast.LENGTH_SHORT).show();
                notValid = true;
                lastInvalid = System.currentTimeMillis();
                Log.e(LOG_TAG, "8");
            }
        }


    }*/


}