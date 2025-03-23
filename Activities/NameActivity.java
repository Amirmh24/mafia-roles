package com.example.mafia.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mafia.Handler.DataHandler;
import com.example.mafia.Adapters.NameAdapter;
import com.example.mafia.Handler.GameHandler;
import com.example.mafia.Handler.NameHandler;
import com.example.mafia.Handler.ScreenHandler;
import com.example.mafia.R;


public class NameActivity extends AppCompatActivity {
    private TextView textViewNamesCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        ScreenHandler.setUpActivityDecor(getWindow());

        DataHandler.loadNames(this);

        textViewNamesCount = findViewById(R.id.textViewNamesCount);
        refreshCount();

        ListView listViewNames = findViewById(R.id.listViewNames);
        NameAdapter nameAdapter = new NameAdapter(this, this);
        listViewNames.setAdapter(nameAdapter);

        Button butAddName = findViewById(R.id.buttonAddName);
        butAddName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NameHandler.getCount() < GameHandler.getMaxPlayerCount()) {
                    NameHandler.add();
                    nameAdapter.notifyDataSetChanged();
                }
            }
        });

        Button butBack = findViewById(R.id.buttonBackName);
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        DataHandler.saveNames(this);
    }

    @SuppressLint("SetTextI18n")
    public void refreshCount() {
        textViewNamesCount.setText("" + NameHandler.getCount());
    }
}
