package com.example.assessmenttrial2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendsActivity<usersAdapter> extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<com.example.chatapp.User> users;
    private ProgressBar progressBar;
    private UsersAdapter usersAdapter;
    UsersAdapter.OnUserClickListener onUserClickListener;


    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        progressBar = findViewById(R.id.progressBar);
        users = new ArrayList<>();
        recyclerView = findViewById(R.id.recyler);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        onUserClickListener = new UsersAdapter.OnUserClickListener() {
            @Override
            public void onUserClicked(int position) {
                startActivity(new Intent(FriendsActivity.this,MessageActivity.class));
                Toast.makeText(FriendsActivity.this, "User selected"+users.get(position).getUsername(), Toast.LENGTH_SHORT).show();
            }
        };

        getUsers();
    }


    @Override
    public  boolean onCreateOptionsMenu(Menu Menu) {
        getMenuInflater().inflate(R.menu.profile_menu, Menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_item_profile){
            startActivity(new Intent(FriendsActivity.this,Profile.class));
        }
        return super.onOptionsItemSelected(item);

    }

    private void getUsers(){
        users.clear();
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    users.add(dataSnapshot.getValue(com.example.chatapp.User.class));
                }
                usersAdapter = new UsersAdapter(users, FriendsActivity.this, onUserClickListener) {

                    @NonNull
                    @Override
                    public UserHolder onCreateViewHolder(int viewType, @NonNull ViewGroup parent) {
                        return onCreateViewHolder((ViewGroup) null, 0);
                    }
                };
                recyclerView.setLayoutManager(new LinearLayoutManager(FriendsActivity.this));
                recyclerView.setAdapter(usersAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
}