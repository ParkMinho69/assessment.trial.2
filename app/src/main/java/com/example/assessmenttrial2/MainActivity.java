package com.example.assessmenttrial2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSigningUp){
                    handleSignup();
            }else {
                    handleLogin();
                }
            }
        });

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

    private void handleSignup(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextTextEmailAddress.getText().toString().editTextTextPassword.getText().toString().addOnCompleteListener(new OnCompleteListener<AuthResult>()));
                @Override
                public void OnComplete;(@NonNull Task<AuthResult> Task) {
            if (task.isSuccessful()) {
                Toast.makeText(MainActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void handleLogin(){

    }
}