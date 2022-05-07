package com.example.mainproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private ArrayList<String> names;
    private final LayoutInflater inflater;
    private MyCallBack callback;

    public RecyclerAdapter(Context context, ArrayList<String> names){
        this.names = names;
        this.inflater = LayoutInflater.from(context);
    }

    public void registerCallBack(MyCallBack callback){this.callback = callback;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = names.get(position);
        holder.largeTextView.setText(name);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.callingback();
            }
        });
    }


    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView largeTextView;
        public final Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            largeTextView= (TextView) itemView.findViewById(R.id.largetextview);
            button = (Button) itemView.findViewById(R.id.recycleritembutton);
        }
    }
}
