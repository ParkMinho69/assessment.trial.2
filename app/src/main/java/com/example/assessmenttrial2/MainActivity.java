package com.example.assessmenttrial2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatapp.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTextUsername, editTextTextPassword, editTextTextEmailAddress;
    private Button button;
    private TextView txtLonginInfo;

    private boolean isSigningUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextUsername = findViewById(R.id.editTextTextUsername);

        button = findViewById(R.id.button);

        txtLonginInfo = findViewById(R.id.textView3);

        if (FirebaseAuth.getInstance().getCurrentUser() !=null){
            startActivity(new Intent(MainActivity.this,FriendsActivity.class));
            finish();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextTextEmailAddress.getText().toString().isEmpty() || editTextTextPassword.getText().toString().isEmpty()){
                    if (isSigningUp && editTextTextUsername.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (isSigningUp){
                    handleSignup();
                }else {
                    handleLogin();
                }
            }
        });


        txtLonginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSigningUp) {
                    isSigningUp = false;
                    editTextTextUsername.setVisibility(View.GONE);
                    button.setText("Log in");
                    txtLonginInfo.setText("Don't have an account?");
                } else {
                    isSigningUp = true;
                    editTextTextUsername.setVisibility(View.VISIBLE);
                    button.setText("Sign up");
                    txtLonginInfo.setText("Already have an account?");
                }
            }
        });

    }

    private void handleSignup(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextTextEmailAddress.getText().toString(),editTextTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference("user/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new User(editTextTextUsername.getText().toString(),editTextTextEmailAddress.getText().toString(),""));
                    startActivity(new Intent(MainActivity.this,FriendsActivity.class));
                    Toast.makeText(MainActivity.this, "Sign up is Successful!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void handleLogin(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextTextEmailAddress.getText().toString(),editTextTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,FriendsActivity.class));
                    Toast.makeText(MainActivity.this, "Log in is Successful!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
