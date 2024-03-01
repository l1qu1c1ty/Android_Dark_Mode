package com.melihcandemir.darkmode;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isDarkTheme = false;

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("MainActivity", "onConfigurationChanged called");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load theme preference
        SharedPreferences preferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        isDarkTheme = preferences.getBoolean("is_dark_theme", false);

        setTheme(isDarkTheme ? R.style.AppTheme_Dark : R.style.AppTheme_Light);
        setContentView(R.layout.activity_main);

        Button toggleThemeButton = findViewById(R.id.toggleThemeButton);
        toggleThemeButton.setOnClickListener(v -> toggleTheme());
    }

    private void toggleTheme() {
        isDarkTheme = !isDarkTheme;
        saveTheme(isDarkTheme);
        recreate(); // Apply theme change
    }

    private void saveTheme(boolean isDarkTheme) {
        SharedPreferences preferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("is_dark_theme", isDarkTheme);
        editor.apply();
    }
}
