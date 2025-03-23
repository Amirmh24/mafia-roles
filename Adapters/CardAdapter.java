package com.example.mafia.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.mafia.Handler.CardHandler;
import com.example.mafia.R;
import com.example.mafia.Views.CardDialog;

public class CardAdapter extends BaseAdapter {

    private Context context;

    public CardAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return CardHandler.getShuffledNames().size();
    }

    @Override
    public Object getItem(int i) {
        return CardHandler.getShuffledNames().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View rowView = LayoutInflater.from(context).inflate(R.layout.list_item_card, viewGroup, false);
        Button button = rowView.findViewById(R.id.buttonNameCard);
        button.setText(CardHandler.getShuffledNames().get(index));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardDialog cardDialog = new CardDialog(context, CardHandler.getShuffledRoles().get(index));
                CardHandler.seen(index);
                notifyDataSetChanged();
                cardDialog.show();
            }
        });
        return rowView;
    }
}
