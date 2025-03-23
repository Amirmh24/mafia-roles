package com.example.mafia.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.mafia.R;

import java.util.ArrayList;

public class PlayerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> inputArrayList;
    private ArrayList<String> outputArrayList;

    public PlayerAdapter(Context context, ArrayList<String> inputArrayList) {
        this.context = context;
        this.inputArrayList = inputArrayList;
        this.outputArrayList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return inputArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return inputArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View rowView = LayoutInflater.from(context).inflate(R.layout.list_item_list, viewGroup, false);
        Button button = rowView.findViewById(R.id.buttonItemList);
        button.setText(inputArrayList.get(i));
        if (outputArrayList.contains((String) button.getText()))
            button.setBackgroundResource(R.drawable.item_selected);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (outputArrayList.contains((String) button.getText())) {
                    outputArrayList.remove((String) button.getText());
                    button.setBackgroundResource(R.drawable.item);
                } else {
                    outputArrayList.add((String) button.getText());
                    button.setBackgroundResource(R.drawable.item_selected);
                }
            }
        });
        return rowView;
    }

    public ArrayList<String> getSelectedItems() {
        return outputArrayList;
    }
}
