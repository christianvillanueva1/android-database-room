package com.christian.crudandroid;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button creatorInfoButton = findViewById(R.id.button_creator_info);
        creatorInfoButton.setOnClickListener(v -> showCreatorInfoDialog());

    }

    private void showCreatorInfoDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.button_creator_info))
                .setMessage(getString(R.string.creator_info))
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}