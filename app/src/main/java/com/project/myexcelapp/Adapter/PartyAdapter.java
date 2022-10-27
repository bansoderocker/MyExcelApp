package com.project.myexcelapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.myexcelapp.Model.ModelClass;
import com.project.myexcelapp.Model.Party;
import com.project.myexcelapp.R;

import java.util.List;

public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.MyViewHolder> {

    private Context mContext;
    private List<Party> mData;

    public PartyAdapter(Context mContext, List<Party> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partylist_view_layout, parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtName.setText(mData.get(position).getPName());
        holder.txtSrNo.setText(""+(position+1));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView txtName,txtSrNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName =itemView.findViewById(R.id.txtName);
            txtSrNo =itemView.findViewById(R.id.txtSrNo);

        }
    }
}
