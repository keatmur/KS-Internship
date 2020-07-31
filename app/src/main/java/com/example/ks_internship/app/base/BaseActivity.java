package com.example.ks_internship.app.base;

import android.content.Intent;
import android.provider.Settings;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.example.ks_internship.R;
import com.example.ks_internship.app.activity.MainActivity;
import com.example.ks_internship.app.activity.ThirdActivity;
import com.example.ks_internship.app.utils.Constants;

import androidx.appcompat.app.AppCompatActivity;

public  class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;


    public void openActivityHistory(){
        Intent intent = new Intent(BaseActivity.this, ThirdActivity.class);
        startActivityForResult(intent,Constants.RESULT_COD);
    };

    public void openMenu() {
        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId()== R.id.actoin_history) {
                    openActivityHistory();
                }
                return true;
            }

        });

    }

    public void initToolbar(String title) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        openMenu();

    }

    public void initToolbarWithNavigation(String title) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });


    }

    public Toolbar getToolbar() {
        return toolbar;
    }


}
