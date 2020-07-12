package com.example.ks_internship.app.Base;

import android.content.Intent;
import android.provider.Settings;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.example.ks_internship.R;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;

    public void openSettings() {
        Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(settingsIntent);
    }

    public void sendEmail(String email, String subject, String message) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(intent);
    }

    public void openMenu(){
        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.actoin_settings:
                        openSettings();
                        return true;
                    case R.id.action_email:
                        sendEmail("keatmur@gmail.com", "Test message!", "Hello world!");
                        return true;
                    default:

                        return true;
                }
            }

        });

    }

    public void initToolbar(String title){
        toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        openMenu();

    }

    public void initToolbarWithNavigation(String title){
        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v->{
            onBackPressed();
        });
        openMenu();



    }
}
