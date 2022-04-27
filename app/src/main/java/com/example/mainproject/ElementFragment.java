package com.example.mainproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ElementFragment extends Fragment {
    public boolean buttonlife=true;
Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_element, container, false);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        button = (Button) inflateView.findViewById(R.id.leave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonlife=false;
            }
        });

        return inflateView;
    }


}