package com.example.mainproject.elemfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mainproject.MyCallBack;
import com.example.mainproject.R;
import com.example.mainproject.db.SubstanceItem;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private ArrayList<SubstanceItem> items;
    private final LayoutInflater inflater;
    private MyCallBack callback;
    public SubstanceItem elements;

    public RecyclerAdapter(Context context, ArrayList<SubstanceItem> items){
        this.items = items;
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
        holder.titleTextView.setText(items.get(position).title);
        holder.formulaTextView.setText(items.get(position).formula);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elements = items.get(position);
                callback.print_elem();
            }
        });
    }

    public SubstanceItem getElements(){return elements;}

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTextView;
        public final TextView formulaTextView;
        public final Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView= (TextView) itemView.findViewById(R.id.title_textview);
            formulaTextView = (TextView) itemView.findViewById(R.id.formula_textview);
            button = (Button) itemView.findViewById(R.id.recycleritembutton);
        }
    }
}
