package com.example.mainproject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mainproject.db.MyDBManager;
import com.example.mainproject.db.SubstanceItem;
import com.google.android.material.button.MaterialButtonToggleGroup;


public class ElementFragment extends Fragment {
    MaterialButtonToggleGroup buttonToggleGroup;
    MyCallBack callback;
RecyclerView recyclerView;
RecyclerAdapter recyclerAdapter;
MyDBManager myDBManager;
public void registerCallBack(MyCallBack callback){this.callback = callback;}

    public void SetMyDBManager(MyDBManager myDBManager){
    this.myDBManager = myDBManager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_element, container, false);

        //recycler
        recyclerView = (RecyclerView) inflateView.findViewById(R.id.recyclerview);

        //toggle_btn
        buttonToggleGroup = inflateView.findViewById(R.id.toggle_base_btn);
        buttonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked){
                    switch (checkedId){
                        case R.id.btn_based:
                            recyclerAdapter = new RecyclerAdapter(inflateView.getContext(), myDBManager.GetBasedFromDB());
                            recyclerAdapter.registerCallBack(callback);
                            recyclerView.setAdapter(recyclerAdapter);
                            inflateView.findViewById(R.id.btn_based).setClickable(false);
                            inflateView.findViewById(R.id.btn_created).setClickable(true);

                            break;
                        case R.id.btn_created:
                            recyclerAdapter = new RecyclerAdapter(inflateView.getContext(), myDBManager.GetCreatedFromDB());
                            recyclerAdapter.registerCallBack(callback);
                            recyclerView.setAdapter(recyclerAdapter);
                            inflateView.findViewById(R.id.btn_created).setClickable(false);
                            inflateView.findViewById(R.id.btn_based).setClickable(true);
                    }
                }
            }
        });
        inflateView.findViewById(R.id.btn_based).performClick();
        return inflateView;
    }
    public SubstanceItem getElem(){return recyclerAdapter.getElements();}
}