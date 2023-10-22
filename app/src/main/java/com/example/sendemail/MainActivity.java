package com.example.sendemail;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText sendTo, subBox, msgBox;
    Button btn, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendTo = findViewById(R.id.sendTo);
        subBox = findViewById(R.id.subBox);
        msgBox = findViewById(R.id.msgBox);
        btn2 = findViewById(R.id.btn2);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener () {
            public void onClick(View view) {
                String emailSend = sendTo.getText().toString();
                String emailSub = subBox.getText().toString();
                String emailMsg = msgBox.getText().toString();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL, new String[] {emailSend});
                i.putExtra(Intent.EXTRA_SUBJECT, emailSub);
                i.putExtra(Intent.EXTRA_TEXT, emailMsg);
                i.setType("message/rfc822");
                startActivity(Intent.createChooser(i, "Choose an Email client: "));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                receiveEmail();
            }
        });
    }
    private void receiveEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_MAIN);
        emailIntent.addCategory(Intent.CATEGORY_APP_EMAIL);

        try {
            startActivity(Intent.createChooser(emailIntent, "Select an email app..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

}