package com.example.assessmenttrial2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTextEmailAddress2, editTextTextPassword, editTextTextEmailAddress;
    private Button button2;
    private TextView txtLongininfo;

    private boolean isSigningUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2);

        button2 = findViewById(R.id.button2);

        txtLongininfo = findViewById(R.id.textView3);

        txtLongininfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSigningUp) {
                    isSigningUp = false;
                    editTextTextEmailAddress2.setVisibility(View.GONE);
                    button2.setText("Log in");
                    txtLongininfo.setText("Don't have an account? Sign up");
                }else {
                    isSigningUp = true;
                    editTextTextEmailAddress2.setVisibility(View.VISIBLE);
                    button2.setText("Sign up");
                    txtLongininfo.setText("Already have an account? Log in");
                }

            }
        });

    }
}