package com.example.firestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<UserData> userDataa;
    private Context context;

    public MyAdapter(List<UserData> userData, Context context) {
        this.userDataa = userData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserData userData = userDataa.get(position);
        holder.email.setText(userData.getEmail());

    }

    @Override
    public int getItemCount() {
        return userDataa.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        TextView email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.emailSingleLayout);
        }
    }
}
