package eu.artandroidapps.mvvm_tmdb.moviesapp.ui.Settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import eu.artandroidapps.mvvm_tmdb.moviesapp.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupToolbar();
    }

    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }
}
