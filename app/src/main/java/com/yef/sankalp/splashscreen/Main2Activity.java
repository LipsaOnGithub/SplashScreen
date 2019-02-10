package com.yef.sankalp.splashscreen;

import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Main2Activity extends AppCompatActivity {

    //variables declaration
    EditText nameData,emailData,messageData;
    Button send,details;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);

        //variables initializtion

        nameData= findViewById(R.id.editTextName);
        emailData= findViewById(R.id.editTextEmail);
        messageData= findViewById(R.id.editTextMessage);

        send= findViewById(R.id.buttonFeedback);
        details= findViewById(R.id.buttonDetails);

        Firebase.setAndroidContext(this);

        String uniqueID= Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        firebase=new Firebase("https://splashandfeedback.firebaseio.com/Users  " +uniqueID);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setEnabled(true);
                final String name= nameData.getText().toString();
                final String email= emailData.getText().toString();
                final String message= messageData.getText().toString();

                Firebase child_name=firebase.child("Name");
                child_name.setValue(name);
                if(name.isEmpty()){
                    nameData.setError("This is an required field");
                    send.setEnabled(false);
                }
                else {
                    nameData.setError(null);
                    send.setEnabled(true);
                }

                Firebase child_email=firebase.child("Email");
                child_email.setValue(email);
                if(email.isEmpty()){
                    emailData.setError("This is an required field");
                    send.setEnabled(false);
                }
                else {
                    emailData.setError(null);
                    send.setEnabled(true);
                }


                Firebase child_message=firebase.child("Message");
                child_message.setValue(message);
                if(message.isEmpty()){
                    messageData.setError("This is an required field");
                    send.setEnabled(false);
                }
                else {
                    messageData.setError(null);
                    send.setEnabled(true);
                }
                Toast.makeText(Main2Activity.this,"Your message send to server",Toast.LENGTH_SHORT).show();

                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    new AlertDialog.Builder(Main2Activity.this)
                            .setTitle("Sended Details")
                            .setMessage("Name = " + name +"\n\nEmail = " + email + "\n\n Message = " + message )
                            .show();

                    }
                });


            }
        });


    }
}
