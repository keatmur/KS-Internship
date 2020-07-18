package com.example.ks_internship.app.activity;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.os.Bundle;

import com.example.ks_internship.R;
import com.example.ks_internship.app.base.BaseActivity;
import com.example.ks_internship.app.fragment.FirstFragment;
import com.example.ks_internship.app.fragment.SecondFragment;

public class FourthActivity extends BaseActivity {

    public AppCompatButton add;
    public AppCompatButton remove;
    public AppCompatButton show;
    public AppCompatButton hide;
    public AppCompatButton replace;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        initToolbarWithNavigation(getString(R.string.app_name));

        initView();

        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();

        setLisner();

    }

    public void initView() {
        add = findViewById(R.id.addBtn);
        show = findViewById(R.id.showBtn);
        replace = findViewById(R.id.replaceBtn);
        remove = findViewById(R.id.removeBtn);
        hide = findViewById(R.id.hideBtn);
    }

    public void setLisner() {
        add.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fraqment_container, firstFragment)
                    .addToBackStack(null)
                    .commit();
        });
        replace.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fraqment_container, secondFragment)
                    .commit();
        });

        show.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.show(secondFragment)
                    .commit();

        });
        remove.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.remove(firstFragment)
                    .addToBackStack(null)
                    .commit();
        });

        hide.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(secondFragment)
                    .addToBackStack(null)
                    .commit();

        });

    }
}