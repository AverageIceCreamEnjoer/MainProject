package com.example.mainproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.mainproject.databinding.ActivityMainBinding;
import com.example.mainproject.db.MyDBManager;
import com.example.mainproject.db.RDBManager;
import com.example.mainproject.db.SubstanceItem;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity implements MyCallBack {
    ActivityMainBinding binding;
    ElementFragment elementFragment = new ElementFragment();
    EditText temptext;
    private int Temp;
    private char[] Temptext;
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction;
    public ImageButton tempb, tempm, settings;
    private MyDBManager myDBManager;
    public ArrayList<SubstanceItem> elements;
    TextView title_text, reaction_text;
    Button reaction_button, clean_button;
    RDBManager rdbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        temptext = (EditText) findViewById(R.id.temptext);
        title_text = (TextView) findViewById(R.id.title_text);
        elements =  new ArrayList<>();
        reaction_text = (TextView) findViewById(R.id.reaction_text);


        //db
        myDBManager = new MyDBManager(this);
        myDBManager.openDB();
        elementFragment.SetMyDBManager(myDBManager);
        rdbManager = new RDBManager(this);
        rdbManager.openRDB();

        //реализация callback
        elementFragment.registerCallBack(this);

        //кнопка добавления фрагмента
        findViewById(R.id.add_element2).setOnClickListener(view -> {
            findViewById(R.id.pole).setVisibility(View.VISIBLE);

            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fragmentTransaction.replace(R.id.fragment_container, elementFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        findViewById(R.id.pole).setOnClickListener(view -> {
            callingback();
        });

        //температура
        tempb = (ImageButton) findViewById(R.id.tempb);
        tempb.setOnClickListener(view -> {
            Temp = Integer.parseInt(String.valueOf(temptext.getText()));
            Temp++;;
            temptext.setText(Integer.toString(Temp));
        });
        tempm = (ImageButton) findViewById(R.id.tempm);
        tempm.setOnClickListener(view -> {
            Temp = Integer.parseInt(String.valueOf(temptext.getText()));
            if (Temp>0){
                Temp--;
                Temptext = Integer.toString(Temp).toCharArray();
                temptext.setText(Temptext, 0, Temptext.length);
            }
        });
        //reaction
        reaction_button = (Button) findViewById(R.id.btn_reaction);
        reaction_button.setOnClickListener(view -> {
            String reaction_product = "";
            ArrayList<Integer> subjects = new ArrayList<>();
            for (int i = 0; i < elements.size(); i++) {
                subjects.add(elements.get(i).unicode);
            }
            Collections.sort(subjects);
            for (int i = 0; i < subjects.size(); i++) {
                reaction_product+=subjects.get(i);
            }
            SubstanceItem substanceItem = myDBManager.GetProduct(rdbManager.GetReaction(reaction_product));
            if (substanceItem!=null) {
                reaction_text.append(substanceItem.title);
                reaction_text.append(" (" + substanceItem.formula + ")");
                reaction_text.append("\n");
            }
        });
        //clean
        clean_button = (Button) findViewById(R.id.btn_clear);
        clean_button.setOnClickListener(view -> {
            title_text.setText("");
            reaction_text.setText("");
            elements.clear();
        });
        //settings
        settings = (ImageButton) findViewById(R.id.settings);
        settings.setOnClickListener(view -> {
            myDBManager.clearCreated();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDBManager.closeDB();
        rdbManager.closeRDB();
    }
    //кнопка удаления фрагмента
    @Override
    public void callingback() {
        //Элементы на экране
        SubstanceItem substanceItem = elementFragment.getElem();
        if (substanceItem!=null)elements.add(substanceItem);
        title_text.setText("");
        for (int i = 0; i<elements.size();i++) {
            title_text.append(elements.get(i).title);
            title_text.append(" ("+ elements.get(i).formula + ")");
            title_text.append("\n");
        }
        //...
        findViewById(R.id.pole).setVisibility(View.GONE);
        //FT
        fragmentTransaction  = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
        if (elementFragment.isAdded()) {
            fragmentTransaction.remove(elementFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }
}