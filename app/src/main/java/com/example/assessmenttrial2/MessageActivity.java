package com.example.assessmenttrial2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editText;
    private android.widget.TextView txtchattingwith;
    private ProgressBar progressBar;
    private ImageView imgToolbar,imgSend;

    private ArrayList<Message> messages;

    String usernameofTheRoommate, emailofRoommate, chattingwithUser, chatroom;

    //kate lucy == katelucy

    //id of the chat room for kate lucy


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        usernameofTheRoommate = getIntent().getStringExtra("username_of_roommate");
        emailofRoommate = getIntent().getStringExtra("email_of_roommate");

        recyclerView = findViewById(R.id.recylerMessages);
        imgSend = findViewById(R.id.imgSend);
        txtchattingwith = findViewById(R.id.txtchattingwith);
        progressBar = findViewById(R.id.progressBar);
        imgToolbar = findViewById(R.id.img_toolbar);

        txtchattingwith.setText(usernameofTheRoommate);

        messages = new ArrayList<>();
    }

    private void setUpChatRoom(){
        FirebaseDatabase.getInstance().getReference("user/"+ FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String myUsername = snapshot.getValue(User.class).getUsername();
                if(usernameofTheRoommate.compareTo(myUsername)>0){  // KATE LUCY
                    String chatRoomId = myUsername + usernameofTheRoommate;
                }else if(usernameofTheRoommate.compareTo(myUsername) == 0){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}