package com.example.mainproject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ElementFragment extends Fragment {
Button button;
    MyCallBack callback;
RecyclerView recyclerView;
RecyclerAdapter recyclerAdapter;
ArrayList<String> arrayList = new ArrayList<>();
private boolean recyclecreate = false;
public void registerCallBack(MyCallBack callback){this.callback = callback;}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInitialData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_element, container, false);
        recyclerView = (RecyclerView) inflateView.findViewById(R.id.recyclerview);
        recyclerAdapter = new RecyclerAdapter(inflateView.getContext(), arrayList);
        recyclerAdapter.registerCallBack(callback);
        recyclerView.setAdapter(recyclerAdapter);
        return inflateView;
    }
    private void setInitialData() {
        arrayList.add("R");
        arrayList.add("KOT");
        arrayList.add("CAT");
        arrayList.add("LOH");
        arrayList.add("AER");
        arrayList.add("SCX");
        arrayList.add("aA");
        arrayList.add("SGD");
    }

}