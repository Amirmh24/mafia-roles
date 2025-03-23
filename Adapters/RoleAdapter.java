package com.example.mafia.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mafia.Handler.RoleHandler;
import com.example.mafia.R;
import com.example.mafia.Activities.RoleActivity;
import com.example.mafia.Roles.Side;

public class RoleAdapter extends BaseAdapter {

    private Context context;
    private RoleActivity roleActivity;
    private Side side;

    public RoleAdapter(Context context, RoleActivity roleActivity, Side side) {
        this.context = context;
        this.roleActivity = roleActivity;
        this.side = side;
    }

    @Override
    public int getCount() {
        return RoleHandler.getRoles(side).size();
    }

    @Override
    public Object getItem(int i) {
        return RoleHandler.getRoles(side).get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View rowView = LayoutInflater.from(context).inflate(R.layout.list_item_role, viewGroup, false);
        TextView textViewRole = rowView.findViewById(R.id.textViewRole);
        textViewRole.setText(getItem(index).toString());
        String role = "role_" + textViewRole.getText();
        Button buttonAddRole = rowView.findViewById(R.id.buttonAddRole);
        if (RoleHandler.getSelectedRoles().contains(role))
            buttonAddRole.setBackgroundResource(R.drawable.full_box);
        else
            buttonAddRole.setBackgroundResource(R.drawable.null_box);

        buttonAddRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String role = "role_" + textViewRole.getText();
                int i = RoleHandler.getIndex(role);
                if (i == -1) {
                    if (RoleHandler.getCount() < RoleHandler.maxCount) {
                        roleActivity.addRole(role);
                        buttonAddRole.setBackgroundResource(R.drawable.full_box);
                    }
                } else {
                    roleActivity.removeRole(i);
                    buttonAddRole.setBackgroundResource(R.drawable.null_box);
                }
                roleActivity.refreshCount();
            }
        });
        return rowView;
    }
}
