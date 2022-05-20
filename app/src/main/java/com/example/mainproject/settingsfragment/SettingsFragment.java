package com.example.mainproject.settingsfragment;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;


import com.example.mainproject.MainActivity;
import com.example.mainproject.MyCallBack;
import com.example.mainproject.R;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class SettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    MyCallBack callback;
    SwitchCompat aSwitch;
    Context context;
    boolean t=false;

    public void getcontext(Context context){
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.settings_layout, container, false);
        //clear_history
        inflateView.findViewById(R.id.clear_history).setOnClickListener(view -> {
            if(inflateView.findViewById(R.id.btn_delete_created).getVisibility()==View.GONE) {
                inflateView.findViewById(R.id.btn_delete_created).setVisibility(View.VISIBLE);
            } else inflateView.findViewById(R.id.btn_delete_created).setVisibility(View.GONE);

        });
        inflateView.findViewById(R.id.btn_delete_created).setOnClickListener(view -> {
            callback.clear();
            inflateView.findViewById(R.id.btn_delete_created).setVisibility(View.GONE);
        });
        inflateView.findViewById(R.id.leave_settings_btn).setOnClickListener(view -> {
            callback.leave_settings_fragment();
        });
        aSwitch = (SwitchCompat) inflateView.findViewById(R.id.switch_theme);
        if (t)aSwitch.setChecked(true);
        if (aSwitch!=null)aSwitch.setOnCheckedChangeListener(this);
        inflateView.findViewById(R.id.add_create_btn).setOnClickListener(view -> {
            callback.add_create_fragment();
        });
        return inflateView;
    }

    public void registerCallBack(MyCallBack callBack){
        this.callback = callBack;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        callback.theme();
    }
    public void setClick(){t=true;}
}
