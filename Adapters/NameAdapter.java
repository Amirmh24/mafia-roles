package com.example.mafia.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.mafia.Handler.NameHandler;
import com.example.mafia.Activities.NameActivity;
import com.example.mafia.R;

public class NameAdapter extends BaseAdapter {

    private Context context;
    private NameActivity nameActivity;

    public NameAdapter(Context context, NameActivity nameActivity) {
        this.context = context;
        this.nameActivity = nameActivity;
    }

    @Override
    public int getCount() {
        return NameHandler.getNames().size();
    }

    @Override
    public Object getItem(int i) {
        return NameHandler.getNames().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View rowView = LayoutInflater.from(context).inflate(R.layout.list_item_name, viewGroup, false);
        EditText editText = rowView.findViewById(R.id.editTextName);
        editText.setText(NameHandler.getNames().get(index));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NameHandler.setName(index, charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                NameHandler.setName(index, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Button butRemoveName = rowView.findViewById(R.id.buttonRemoveName);
        butRemoveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NameHandler.removeName(index);
                notifyDataSetChanged();
            }
        });
        return rowView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        nameActivity.refreshCount();
    }
}
