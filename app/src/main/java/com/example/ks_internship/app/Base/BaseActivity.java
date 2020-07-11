package com.example.ks_internship.app.Base;

import android.widget.Toolbar;

import com.example.ks_internship.R;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;

    public void initToolbar(String title){
        toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle(title);

    }

    public void initToolbarWithNavigation(String title){
        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v->{
            onBackPressed();
        });



    }
}
