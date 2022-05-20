package com.example.mainproject.createFragment;

import android.animation.LayoutTransition;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.mainproject.MyCallBack;
import com.example.mainproject.R;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;

public class CreateFragment extends Fragment {
    MaterialButtonToggleGroup buttonToggleGroup;
    Button create_button;
    MyCallBack callBack;
    EditText et_name, et_formula;
    Context context;
    Boolean checked;
    ImageButton add_reagent, delete_reagent;
    public void set_context(Context context){this.context=context;}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.create_fragment, container, false);
        //анимация в create_fragment
        LayoutTransition layoutTransition_toolbar = ((Toolbar) inflateView.findViewById(R.id.create_fragment_toolbar)).getLayoutTransition();
        layoutTransition_toolbar.setDuration(400);
        layoutTransition_toolbar.enableTransitionType(LayoutTransition.CHANGING);
        LayoutTransition layoutTransition_cardview = ((CardView) inflateView.findViewById(R.id.create_fragment_cardview)).getLayoutTransition();
        layoutTransition_cardview.setDuration(400);
        layoutTransition_cardview.enableTransitionType(LayoutTransition.CHANGING);
        //кнопка
        buttonToggleGroup= inflateView.findViewById(R.id.create_toggle);
        buttonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked){
                    switch (checkedId){
                        case R.id.substance_toggle_btn:
                            inflateView.findViewById(R.id.reaction_layout).setVisibility(View.GONE);
                            inflateView.findViewById(R.id.substance_name_input).setVisibility(View.VISIBLE);
                            inflateView.findViewById(R.id.substance_formula_input).setVisibility(View.VISIBLE);
                            inflateView.findViewById(R.id.create_btn).setVisibility(View.VISIBLE);
                            checked = true;
                            break;
                        case R.id.reaction_toggle_btn:
                            inflateView.findViewById(R.id.substance_name_input).setVisibility(View.GONE);
                            inflateView.findViewById(R.id.substance_formula_input).setVisibility(View.GONE);
                            inflateView.findViewById(R.id.reaction_layout).setVisibility(View.VISIBLE);
                            inflateView.findViewById(R.id.create_btn).setVisibility(View.VISIBLE);
                            checked = false;
                            break;
                    }
                } else {
                    if (group.getCheckedButtonId() == View.NO_ID) {
                        checked = null;
                        inflateView.findViewById(R.id.substance_name_input).setVisibility(View.GONE);
                        inflateView.findViewById(R.id.substance_formula_input).setVisibility(View.GONE);
                        inflateView.findViewById(R.id.reaction_layout).setVisibility(View.GONE);
                        inflateView.findViewById(R.id.create_btn).setVisibility(View.GONE);
                    }
                }
            }
        });
        //create veshestvo
        et_name = (EditText) inflateView.findViewById(R.id.et_name);
        et_formula = (EditText) inflateView.findViewById(R.id.et_formula);
        //create reaction
        //create reagent
        add_reagent = (ImageButton) inflateView.findViewById(R.id.add_reagent);
        delete_reagent = (ImageButton) inflateView.findViewById(R.id.delete_reagent);
        add_reagent.setOnClickListener(view -> {
            if (inflateView.findViewById(R.id.reagent_layout_2).getVisibility()==View.GONE){
                inflateView.findViewById(R.id.reagent_layout_2).setVisibility(View.VISIBLE);
            } else {
                if (inflateView.findViewById(R.id.reagent_layout_3).getVisibility()==View.GONE){
                   inflateView.findViewById(R.id.reagent_layout_3).setVisibility(View.VISIBLE);
                } else {
                    if (inflateView.findViewById(R.id.reagent_layout_4).getVisibility()==View.GONE){
                        inflateView.findViewById(R.id.reagent_layout_4).setVisibility(View.VISIBLE);
                    } else {
                        if (inflateView.findViewById(R.id.reagent_layout_5).getVisibility()==View.GONE){
                            inflateView.findViewById(R.id.reagent_layout_5).setVisibility(View.VISIBLE);
                        } else {
                            if (inflateView.findViewById(R.id.reagent_layout_6).getVisibility()==View.GONE){
                                inflateView.findViewById(R.id.reagent_layout_6).setVisibility(View.VISIBLE);
                            } else{
                                Toast.makeText(context, "Максимум 6 реагентов", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });
        delete_reagent.setOnClickListener(view -> {
            if (inflateView.findViewById(R.id.reagent_layout_6).getVisibility()==View.VISIBLE){
                inflateView.findViewById(R.id.reagent_layout_6).setVisibility(View.GONE);
                ((EditText)inflateView.findViewById(R.id.et_reagent_6)).getText().clear();
            } else {
                if (inflateView.findViewById(R.id.reagent_layout_5).getVisibility()==View.VISIBLE){
                    inflateView.findViewById(R.id.reagent_layout_5).setVisibility(View.GONE);
                    ((EditText)inflateView.findViewById(R.id.et_reagent_5)).getText().clear();
                } else {
                    if (inflateView.findViewById(R.id.reagent_layout_4).getVisibility()==View.VISIBLE){
                        inflateView.findViewById(R.id.reagent_layout_4).setVisibility(View.GONE);
                        ((EditText)inflateView.findViewById(R.id.et_reagent_4)).getText().clear();
                    } else {
                        if (inflateView.findViewById(R.id.reagent_layout_3).getVisibility()==View.VISIBLE){
                            inflateView.findViewById(R.id.reagent_layout_3).setVisibility(View.GONE);
                            ((EditText)inflateView.findViewById(R.id.et_reagent_3)).getText().clear();
                        } else {
                            if (inflateView.findViewById(R.id.reagent_layout_2).getVisibility()==View.VISIBLE){
                                inflateView.findViewById(R.id.reagent_layout_2).setVisibility(View.GONE);
                                ((EditText)inflateView.findViewById(R.id.et_reagent_2)).getText().clear();
                            } else{
                                Toast.makeText(context, "Минимум 1 реагент", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });
        //кнопка добавления
        create_button = (Button) inflateView.findViewById(R.id.create_btn);

        create_button.setOnClickListener(view -> {
            if (checked) {
                if (!(String.valueOf(et_name.getText()).equals("") || String.valueOf(et_formula.getText()).equals(""))) {
                    callBack.create_substance(String.valueOf(et_name.getText()), String.valueOf(et_formula.getText()));
                } else Toast.makeText(context, "Поля названия и формулы должны быть заполнены", Toast.LENGTH_LONG).show();
            } else {
                ArrayList<String> reagents = new ArrayList<>();
                if (!((EditText)inflateView.findViewById(R.id.et_reagent_1)).getText().toString().equals("")){
                    if (!((EditText)inflateView.findViewById(R.id.et_reagent_2)).getText().toString().equals("")){
                        if (!((EditText)inflateView.findViewById(R.id.et_reagent_3)).getText().toString().equals("")){
                            if (!((EditText)inflateView.findViewById(R.id.et_reagent_4)).getText().toString().equals("")){
                                if (!((EditText)inflateView.findViewById(R.id.et_reagent_5)).getText().toString().equals("")){
                                    if(!((EditText)inflateView.findViewById(R.id.et_reagent_6)).getText().toString().equals("")){
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_1)).getText().toString());
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_2)).getText().toString());
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_3)).getText().toString());
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_4)).getText().toString());
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_5)).getText().toString());
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_6)).getText().toString());
                                    } else {
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_1)).getText().toString());
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_2)).getText().toString());
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_3)).getText().toString());
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_4)).getText().toString());
                                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_5)).getText().toString());
                                    }
                                } else {
                                    reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_1)).getText().toString());
                                    reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_2)).getText().toString());
                                    reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_3)).getText().toString());
                                    reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_4)).getText().toString());
                                }
                            } else{
                                reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_1)).getText().toString());
                                reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_2)).getText().toString());
                                reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_3)).getText().toString());
                            }
                        } else {
                            reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_1)).getText().toString());
                            reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_2)).getText().toString());
                        }
                    } else{
                        reagents.add(((EditText)inflateView.findViewById(R.id.et_reagent_1)).getText().toString());
                    }
                } else Toast.makeText(context, "Нужно ввести хотя бы один реагент", Toast.LENGTH_SHORT).show();
                if (reagents.size()>0 && !((EditText)inflateView.findViewById(R.id.et_reaction_product)).getText().toString().equals("")){
                    callBack.add_reaction(reagents, ((EditText)((EditText) inflateView.findViewById(R.id.et_reaction_product))).getText().toString());
                } else Toast.makeText(context, "Нужно ввести продукт реакции", Toast.LENGTH_SHORT).show();
            }
        });
        //leave
        inflateView.findViewById(R.id.leave_create_btn).setOnClickListener(view -> {
            callBack.leave_create_fragment();
        });

        return inflateView;
    }

    public void registerCallBack(MyCallBack callBack) {
        this.callBack = callBack;
    }
}
