package com.example.mainproject;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.LayoutTransition;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainproject.createFragment.CreateFragment;
import com.example.mainproject.databinding.ActivityMainBinding;
import com.example.mainproject.db.MyDBManager;
import com.example.mainproject.db.RDBManager;
import com.example.mainproject.db.ReactionItem;
import com.example.mainproject.db.SubstanceItem;
import com.example.mainproject.elemfragment.ElementFragment;
import com.example.mainproject.elemfragment.RecyclerAdapter;
import com.example.mainproject.settingsfragment.SettingsFragment;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity implements MyCallBack {
    ActivityMainBinding binding;
    ElementFragment elementFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private MyDBManager myDBManager;
    public ArrayList<SubstanceItem> elements;
    TextView reaction_text,formula_text;
    Button reaction_button, clear_button;
    RDBManager rdbManager;
    SettingsFragment settingsFragment;
    CreateFragment createFragment;
    RecyclerView recyclerView;
    Recycler2Adapter recyclerAdapter;
    String reactionItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        elements =  new ArrayList<>();
        fragmentManager = getFragmentManager();
        reaction_text = (TextView) findViewById(R.id.reaction_text);
        formula_text = (TextView) findViewById(R.id.tv_formula);
        elementFragment = new ElementFragment();
        settingsFragment = new SettingsFragment();
        settingsFragment.getcontext(this);
        createFragment = new CreateFragment();
        createFragment.set_context(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);

        if(AppCompatDelegate.getDefaultNightMode()==MODE_NIGHT_NO){
            settingsFragment.setClick();
        }


        //db
        myDBManager = new MyDBManager(this);
        myDBManager.openDB();
        elementFragment.SetMyDBManager(myDBManager);
        rdbManager = new RDBManager(this);
        rdbManager.openRDB();

        //реализация callback
        elementFragment.registerCallBack(this);
        settingsFragment.registerCallBack(this);
        createFragment.registerCallBack(this);


        //кнопка добавления фрагмента
        findViewById(R.id.add_element2).setOnClickListener(view -> {
            findViewById(R.id.pole).setVisibility(View.VISIBLE);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fragmentTransaction.replace(R.id.fragment_container, elementFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        //closeFragment
        findViewById(R.id.pole).setOnClickListener(view -> {
            leave();
        });
        //другого фрагмента
        findViewById(R.id.settings).setOnClickListener(view -> {
            findViewById(R.id.settings_pole).setVisibility(View.VISIBLE);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_out_left,R.animator.slide_out_right);
            fragmentTransaction.replace(R.id.settings_fragment_container, settingsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        //close settings
        findViewById(R.id.settings_pole).setOnClickListener(view -> {
            leave_settings_fragment();
        });

        //close create_fragment
        findViewById(R.id.create_pole).setOnClickListener(view -> {
            leave_create_fragment();
        });
        //reaction
        reaction_button = (Button) findViewById(R.id.btn_reaction);
        reaction_button.setOnClickListener(view -> {
            if (elements.size()==0){
                Toast.makeText(this, "Добавьте хотя бы один реагент", Toast.LENGTH_LONG).show();
            } else {
                String reaction_product = "";
                ArrayList<Integer> subjects = new ArrayList<>();
                for (int i = 0; i < elements.size(); i++) {
                    subjects.add(elements.get(i).unicode);
                }
                Collections.sort(subjects);
                for (int i = 0; i < subjects.size(); i++) {
                    reaction_product += "." + subjects.get(i) + ".";
                }
                SubstanceItem substanceItem = myDBManager.GetProduct(rdbManager.GetReaction(reaction_product), true);
                if (substanceItem != null) {
                    reaction_text.setText("");
                    reaction_text.append(substanceItem.title);
                    reaction_text.append(" (" + substanceItem.formula + ")");
                    formula_text.setText("");
                    for (int i = 0; i < elements.size(); i++) {
                        if (i!=elements.size()-1){
                            formula_text.append(elements.get(i).formula + " + ");
                        }else formula_text.append(elements.get(i).formula + " -> ");
                    }
                    formula_text.append(substanceItem.formula);
                } else {
                    reaction_text.setText("В базе данных нет такой реакции");
                    formula_text.setText("");
                    reaction_product = "."+Integer.toString(subjects.get((int)(Math.random()*elements.size())))+".";
                    reactionItem = rdbManager.getRandomReaction(reaction_product);
                    if (reactionItem!="0") {
                        reaction_text.setText("Возможно, вы имели в виду это:");
                        char[] z = reactionItem.toCharArray();
                        ArrayList<Integer> arrayList = new ArrayList<>();
                        String y = "";
                        for (int i = 0; i < z.length-1; i++) {
                            if (z[i+1]!='.'){
                                y+=Character.toString(z[i+1]);
                            }else {
                                if (y!="") {
                                    arrayList.add(Integer.parseInt(y));
                                    y = "";
                                }
                            }
                        }
                        for (int i = 0; i < arrayList.size(); i++) {
                            if (i!=arrayList.size()-1) {
                                formula_text.append(myDBManager.GetProduct(arrayList.get(i), false).formula + " + ");
                            } else formula_text.append(myDBManager.GetProduct(arrayList.get(i),false).formula);
                        }
                        formula_text.append(" -> " + myDBManager.GetProduct(rdbManager.GetReaction(reactionItem),false).formula);
                    }
                }
            }
        });
        //clear
        clear_button = (Button) findViewById(R.id.btn_clear);
        clear_button.setOnClickListener(view -> {
            reaction_text.setText("");
            elements.clear();
            formula_text.setText("");
            recyclerAdapter = new Recycler2Adapter(this, elements,this);
            recyclerView.setAdapter(recyclerAdapter);
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
    public void print_elem(boolean x) {
        //Элементы на экране
        SubstanceItem substanceItem;
        if(!x) {
            substanceItem = elementFragment.getElem();
            if (substanceItem!=null)elements.add(substanceItem);
        }

        for (int i = 0; i < elements.size(); i++) {
            for (int j = 0; j < elements.size(); j++) {
                if (i!=j&&elements.get(i)==elements.get(j)){
                    elements.remove(j);
                }
            }
        }
        //recycler
        recyclerAdapter = new Recycler2Adapter(this, elements,this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void leave() {
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

    @Override
    public void clear() {
        myDBManager.clearCreated();
    }

    @Override
    public void theme() {
        findViewById(R.id.settings_pole).setVisibility(View.GONE);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(settingsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
                if(AppCompatDelegate.getDefaultNightMode()==MODE_NIGHT_NO){
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
                    //findViewById(R.id.app_bar).setBackgroundResource(R.drawable.app_bar_background);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
                    //findViewById(R.id.app_bar).setBackgroundResource(R.drawable.app_bar_background);
                }
        }

    @Override
    public void add_create_fragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.create_in, R.animator.create_out);
        fragmentTransaction.replace(R.id.create_fragment_container,createFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        findViewById(R.id.create_pole).setVisibility(View.VISIBLE);
    }

    @Override
    public void create_substance(String name, String formula) {
        if(myDBManager.CreateProduct(name,formula))Toast.makeText(this, "Успешно", Toast.LENGTH_LONG).show();
        else Toast.makeText(this, "Вещество с таким названием или формулой уже есть", Toast.LENGTH_LONG).show();
    }

    @Override
    public void leave_create_fragment() {
        findViewById(R.id.create_pole).setVisibility(View.GONE);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.create_in, R.animator.create_out);
        ((EditText)findViewById(R.id.et_reagent_1)).getText().clear();
        ((EditText)findViewById(R.id.et_reagent_2)).getText().clear();
        ((EditText)findViewById(R.id.et_reagent_3)).getText().clear();
        ((EditText)findViewById(R.id.et_reagent_4)).getText().clear();
        ((EditText)findViewById(R.id.et_reagent_5)).getText().clear();
        ((EditText)findViewById(R.id.et_reagent_6)).getText().clear();
        ((EditText)findViewById(R.id.et_reaction_product)).getText().clear();
        ((EditText)findViewById(R.id.et_name)).getText().clear();
        ((EditText)findViewById(R.id.et_formula)).getText().clear();
        ((MaterialButtonToggleGroup)findViewById(R.id.create_toggle)).clearChecked();
        fragmentTransaction.remove(createFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void add_reaction(ArrayList<String> arrayList,String name) {
        if(myDBManager.isExist(name)){
            Integer name_unicode = myDBManager.getUnicode(name);
            if (name_unicode==null){
                Toast.makeText(this, "Указанного продукта нет в базе данных", Toast.LENGTH_SHORT).show();
                return;
            }
            ArrayList<Integer> reagents = new ArrayList<>();
            for (int i = 0; i < arrayList.size(); i++) {
                Integer reagent_unicode = myDBManager.getUnicode(arrayList.get(i));
                if (reagent_unicode!=null){
                    reagents.add(reagent_unicode);
                } else {
                    Toast.makeText(this, "Одного из указанных реагентов нет в базе данных", Toast.LENGTH_LONG).show();
                    reagents.clear();
                    return;
                }
            }
            if (reagents.size()>0){
                for (int i = 0; i < reagents.size(); i++) {
                    for (int j = 0; j < reagents.size(); j++) {
                        if (i!=j&&reagents.get(i)==reagents.get(j)){
                            reagents.remove(j);
                        }
                    }
                }
                Collections.sort(reagents);
                String s = "";
                for (int i = 0; i < reagents.size(); i++) {
                    s+="."+reagents.get(i)+".";
                }
                rdbManager.createReaction(s,name_unicode);

            }
        } else {
            Toast.makeText(this, "Реакция с этим продуктом уже существует", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void leave_settings_fragment() {
        findViewById(R.id.settings_pole).setVisibility(View.GONE);
        if (settingsFragment.isAdded()){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.animator.slide_out_left,R.animator.slide_out_right);
            fragmentTransaction.remove(settingsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}