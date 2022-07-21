package com.example.assessmenttrial2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    private ArrayList<User> users;
    private Context context;
    private OnUserClickListener onUserClickListener;
    private ViewGroup parent;
    private int viewType;
    private LocalDate Glide;


    public UsersAdapter(ArrayList<User> users, Context context, OnUserClickListener onUserClickListener) {
        this.users = users;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    public UsersAdapter(ArrayList<User> users, FriendsActivity friendsActivity, OnUserClickListener onUserClickListener) {
    }

    @NonNull
    public abstract UserHolder onCreateViewHolder(int viewType, @NonNull ViewGroup parent);

    interface OnUserClickListener{
        void onUserClicked(int position);
    }


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        this.viewType = viewType;
        return (null);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.txtUsername.setText(users.get(position).getUsername());

    }

    @Override
    public int getItemCount() {

        return users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        TextView txtUsername;
        ImageView imageView;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUserClickListener.onUserClicked(getAdapterPosition());
                }
            });

            txtUsername = itemView.findViewById(R.id.txtUsername);
            imageView = itemView.findViewById(R.id.img_pro);
        }
    }

}
