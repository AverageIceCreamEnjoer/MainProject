package com.example.mainproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mainproject.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyCallBack {
    ActivityMainBinding binding;
    ElementFragment elementFragment = new ElementFragment();
    EditText temptext;
    private int Temp;
    private char[] Temptext;
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction;
    public ImageButton tempb, tempm;
    RecyclerView recyclerView;
    ArrayList<String> arrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        temptext = (EditText) findViewById(R.id.temptext);


        //реализация callback
        elementFragment.registerCallBack(this);
        //кнопка добавления фрагмента
        findViewById(R.id.add_element2).setOnClickListener(view -> {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fragmentTransaction.replace(R.id.fragment_container, elementFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            //recyclerview


        });

        //температура
        Temp = 0;
        tempb = (ImageButton) findViewById(R.id.tempb);
        tempb.setOnClickListener(view -> {
            long time = System.currentTimeMillis();
            Temp++;
            Temptext = Integer.toString(Temp).toCharArray();
            temptext.setText(Temptext, 0, Temptext.length);
        });
        tempm = (ImageButton) findViewById(R.id.tempm);
        tempm.setOnClickListener(view -> {
            if (Temp>0){
                Temp--;
                Temptext = Integer.toString(Temp).toCharArray();
                temptext.setText(Temptext, 0, Temptext.length);
            }
        });
    }



    public boolean isPressed(ImageButton tempb){
        return tempb.isPressed();
    }
    //кнопка удаления фрагмента
    @Override
    public void callingback() {
        fragmentTransaction  = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
        if (elementFragment.isAdded()) {
            fragmentTransaction.remove(elementFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}