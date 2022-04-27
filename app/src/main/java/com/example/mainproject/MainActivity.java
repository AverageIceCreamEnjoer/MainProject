package com.example.mainproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.mainproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ElementFragment elementFragment = new ElementFragment();

    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DrawerLayout drawerLayout = binding.drawer;

        findViewById(R.id.add_element).setOnClickListener(view -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        findViewById(R.id.add_element2).setOnClickListener(view -> {
            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fragmentTransaction.replace(R.id.fragment_container, elementFragment);
            findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
            //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            /*while(!elementFragment.isRemoving()){
                if (!elementFragment.buttonlife){
                    fragmentTransaction.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_in_left);
                    findViewById(R.id.fragment_container).setVisibility(View.GONE);
                    fragmentTransaction.remove(elementFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }*/
        });

    }
}