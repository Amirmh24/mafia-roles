package com.example.mafia.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mafia.Handler.GameHandler;
import com.example.mafia.Handler.ScreenHandler;
import com.example.mafia.R;
import com.example.mafia.Views.NightDialog;


public class NightActivity extends AppCompatActivity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);
        ScreenHandler.setUpActivityDecor(getWindow());

        Button button = findViewById(R.id.buttonStartNight);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NightDialog.createDialog(context, GameHandler.nextRole());
            }
        });
    }
}
