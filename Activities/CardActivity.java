package com.example.mafia.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mafia.Adapters.CardAdapter;
import com.example.mafia.Handler.CardHandler;
import com.example.mafia.Handler.NameHandler;
import com.example.mafia.Handler.ScreenHandler;
import com.example.mafia.R;

public class CardActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ScreenHandler.setUpActivityDecor(getWindow());

        CardHandler.shuffle();

        ListView listViewCards = findViewById(R.id.listViewCards);
        CardAdapter cardAdapter = new CardAdapter(this);
        listViewCards.setAdapter(cardAdapter);

        TextView textView=findViewById(R.id.textViewCardsCount);
        textView.setText(""+NameHandler.getCount());

        Button butRefreshRoles=findViewById(R.id.buttonRefreshRoles);
        butRefreshRoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardHandler.shuffle();
                cardAdapter.notifyDataSetChanged();
            }
        });

        Button butBack=findViewById(R.id.buttonBackCard);
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
    }
}
