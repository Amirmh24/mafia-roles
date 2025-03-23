package com.example.mafia.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.mafia.Handler.NameHandler;
import com.example.mafia.Handler.RoleHandler;
import com.example.mafia.Handler.ScreenHandler;
import com.example.mafia.R;
//import com.example.mafia.Views.VideoBG;


public class MainActivity extends AppCompatActivity {
//    private VideoBG videoBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenHandler.setUpActivityDecor(getWindow());

        getScreenSize();
//        videoBG = findViewById(R.id.videoBG);
//        videoBG.start();
        Button butMafia = findViewById(R.id.buttonMafia);
        Button butName = findViewById(R.id.buttonEnterNames);
        Button butRole = findViewById(R.id.buttonSelectRoles);
        Button butCard = findViewById(R.id.buttonPickCard);
        Button butGame = findViewById(R.id.buttonStartGame);


        butMafia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        Intent nameIntent = new Intent(this, NameActivity.class);
        butName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(nameIntent);
            }
        });

        Intent roleIntent = new Intent(this, RoleActivity.class);
        butRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(roleIntent);
            }
        });

        Intent cardIntent = new Intent(this, CardActivity.class);
        butCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(NameHandler.getCount() + " " + RoleHandler.getCount());
                if (NameHandler.getCount() == RoleHandler.getCount())
                    startActivity(cardIntent);
            }
        });

        Intent gameIntent = new Intent(this, NightActivity.class);
        butGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(gameIntent);
            }
        });
    }

    private void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ScreenHandler.setScreenSize(displayMetrics.heightPixels, displayMetrics.widthPixels);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        videoBG.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        videoBG.stopPlayback();
    }
}
