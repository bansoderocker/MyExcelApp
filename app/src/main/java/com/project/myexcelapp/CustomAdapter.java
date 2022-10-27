package com.project.myexcelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.myexcelapp.Model.ModelClass;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context mContext;
    private List<ModelClass> mData;

    public CustomAdapter(Context mContext, List<ModelClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_layout, parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtName.setText(mData.get(position).getName());
        holder.txtPhone.setText(mData.get(position).getPhone());
        holder.txtAddress.setText(mData.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView txtName,txtPhone,txtAddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName =itemView.findViewById(R.id.txtName);
            txtPhone =itemView.findViewById(R.id.txtPhone);
            txtAddress =itemView.findViewById(R.id.txtAddress);
        }
    }
}
