package com.example.assessmenttrial2;

import android.widget.EditText;

public class Message {
//setting up sender and receiver
    private String sender;
    private String receiver;
    private String content;

    public Message(String email, String emailofRoommate, EditText edtMessageInput){

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}
