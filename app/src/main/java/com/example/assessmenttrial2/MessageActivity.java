package com.example.assessmenttrial2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
//creating chatroom where we'll be chatting with other users
    private RecyclerView recyclerView;
    private EditText editText;
    private android.widget.TextView txtchattingwith;
    private ProgressBar progressBar;
    private ImageView imgToolbar,imgSend;
    private EditText edtMessageInput;

    private MessageAdapter messageAdapter;
    private ArrayList<Message> messages;

    String usernameofTheRoommate, emailofRoommate, chattingwithUser, chatroom, chatRoomId;

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

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("messages/"+chatRoomId).push().setValue(new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(),emailofRoommate,edtMessageInput.getText().toString()));
                edtMessageInput.setText("");
            }
        });

        messageAdapter = new MessageAdapter(messages,getIntent().getStringExtra("my_img"), getIntent().getStringExtra("img_of_roomate"),MessageActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        Glide.with(MessageActivity.this).load(getIntent().getStringExtra("img_of_roomate")).placeholder(R.drawable.account_img).error(R.drawable.account_img).into(imgToolbar);

        setUpChatRoom();

    }
//this will be creating a chatroom with specific users
    private void setUpChatRoom(){
        FirebaseDatabase.getInstance().getReference("user/"+ FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String myUsername = snapshot.getValue(User.class).getUsername();
                if(usernameofTheRoommate.compareTo(myUsername)>0){
                    chatRoomId = myUsername + usernameofTheRoommate;
                }else if(usernameofTheRoommate.compareTo(myUsername) == 0){
                    chatRoomId = myUsername + usernameofTheRoommate;
                }else {
                    chatRoomId = usernameofTheRoommate + myUsername;
                }
                attachMessageListener(chatRoomId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void attachMessageListener(String chatRoomId){
        FirebaseDatabase.getInstance().getReference("messages/"+ chatRoomId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    messages.add(dataSnapshot.getValue(Message.class));
                }
                messageAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(messages.size()-1);
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}