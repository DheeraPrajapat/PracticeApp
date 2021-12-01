package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.viewHolder> {
    private Context context;
    private ArrayList<PhoneList> arrayList;

    public PhoneAdapter(Context context, ArrayList<PhoneList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PhoneAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.phone_item_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.viewHolder holder, int position) {
        PhoneList list=arrayList.get(position);
        holder.phoneName.setText(list.getName());
        holder.phoneImage.setImageResource(list.getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        ImageView phoneImage;
        TextView phoneName;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            phoneImage=itemView.findViewById(R.id.cardImage);
            phoneName=itemView.findViewById(R.id.cardName);
        }
    }
}
