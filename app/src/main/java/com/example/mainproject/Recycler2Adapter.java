package com.example.mainproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainproject.db.SubstanceItem;
import com.example.mainproject.elemfragment.RecyclerAdapter;

import java.util.ArrayList;

public class Recycler2Adapter extends RecyclerView.Adapter<Recycler2Adapter.Recycler2ViewHolder> {
    private final LayoutInflater inflater;
    private ArrayList<SubstanceItem> items;
    MyCallBack callBack;

    public Recycler2Adapter(Context context, ArrayList<SubstanceItem> items,MyCallBack callBack){
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public Recycler2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler2_item, parent, false);
        return new Recycler2Adapter.Recycler2ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler2ViewHolder holder, int position) {
        holder.titleTextView.setText(items.get(position).title);
        holder.formulaTextView.setText(items.get(position).formula);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove(position);
                callBack.print_elem(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Recycler2ViewHolder extends RecyclerView.ViewHolder{
        public final TextView titleTextView;
        public final TextView formulaTextView;
        public final ImageButton button;

        public Recycler2ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView= (TextView) itemView.findViewById(R.id.title_textview2);
            formulaTextView = (TextView) itemView.findViewById(R.id.formula_textview2);
            button = (ImageButton) itemView.findViewById(R.id.delete_btn);
        }
    }
}
