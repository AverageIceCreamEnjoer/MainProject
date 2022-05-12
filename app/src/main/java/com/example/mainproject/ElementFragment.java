package com.example.mainproject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mainproject.db.MyDBManager;
import com.example.mainproject.db.SubstanceItem;

import java.util.ArrayList;


public class ElementFragment extends Fragment {

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
        //setInitialData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_element, container, false);



        //recycler
        recyclerView = (RecyclerView) inflateView.findViewById(R.id.recyclerview);
        recyclerAdapter = new RecyclerAdapter(inflateView.getContext(), myDBManager.GetFromDB());
        recyclerAdapter.registerCallBack(callback);
        recyclerView.setAdapter(recyclerAdapter);
        return inflateView;
    }
    public ArrayList<SubstanceItem> getElem(){return recyclerAdapter.getElements();}
}