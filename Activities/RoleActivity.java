package com.example.mafia.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mafia.Adapters.RoleAdapter;
import com.example.mafia.Handler.DataHandler;
import com.example.mafia.Handler.RoleHandler;
import com.example.mafia.Handler.ScreenHandler;
import com.example.mafia.R;
import com.example.mafia.Roles.Side;

import java.util.ArrayList;


public class RoleActivity extends AppCompatActivity {
    private ArrayList<ImageView> slots = new ArrayList<>();
    private RelativeLayout layout;
    private int rows = RoleHandler.rows, cols = RoleHandler.cols;
    private int slotW, slotH;
    private int dw, dh;
    private TextView textViewRolesCount;
    private RoleAdapter roleAdapterG, roleAdapterW, roleAdapterB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        ScreenHandler.setUpActivityDecor(getWindow());

        layout = findViewById(R.id.layout_role);
        textViewRolesCount = findViewById(R.id.textViewRolesCount);

        setSlotDimension();
        DataHandler.loadRoles(this);
        buildImageViews();
        refreshCount();

        ListView roleListG = findViewById(R.id.listViewRoleG);
        roleAdapterG = new RoleAdapter(this, this, Side.GRAY);
        roleListG.setAdapter(roleAdapterG);

        ListView roleListW = findViewById(R.id.listViewRoleW);
        roleAdapterW = new RoleAdapter(this, this, Side.WHITE);
        roleListW.setAdapter(roleAdapterW);

        ListView roleListB = findViewById(R.id.listViewRoleB);
        roleAdapterB = new RoleAdapter(this, this, Side.BLACK);
        roleListB.setAdapter(roleAdapterB);

        Button butClearRoles = findViewById(R.id.buttonClearRoles);
        butClearRoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearRoles();
            }
        });

        Button butBack=findViewById(R.id.buttonBackRole);
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
        DataHandler.saveRoles(this);
    }

    private void setSlotDimension() {
        int w = 0;
        int W = ScreenHandler.getWidth() * 3 / 8;
        while (w < W)
            w += 10;
        slotW = w;
        slotH = 2 * w;
        dw = slotW / 4;
        dh = slotW / 3;
    }

    private void buildImageViews() {
        for (int i = RoleHandler.maxCount - 1; i >= 0; i--) {
            ImageView imageView = new ImageView(this);
            String role = RoleHandler.getRole(i);
            int id = getResources().getIdentifier(role, "drawable", getPackageName());
            imageView.setImageResource(id);
            int row = i / cols;
            int col = i % cols;
            addView(imageView, row, col);
            slots.add(0, imageView);
        }
    }

    private void addView(ImageView imageView, int row, int col) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(slotW, slotH);
        int midW = ScreenHandler.getWidth() / 2 - slotW / 2;
        int d = dw;
        if (col % 2 == 0)
            d = -d;
        params.setMargins(midW - d * (int) ((col + 1) / 2), ((rows - 1) * dh + dh / 4) - row * dh + 175, midW + d * (int) ((col + 1) / 2), 0);
        imageView.setLayoutParams(params);
        layout.addView(imageView);
    }

    @SuppressLint("ShowToast")
    public void addRole(String role) {
        int i = RoleHandler.getNextNullSlot();
        if (i == -1)
            Toast.makeText(this, "Slots are full", Toast.LENGTH_LONG);
        else {
            int id = getResources().getIdentifier(role, "drawable", getPackageName());
            slots.get(i).setImageResource(id);
            RoleHandler.setRole(i, role);
        }
    }

    public void removeRole(int i) {
        slots.get(i).setImageResource(R.drawable.role_null);
        RoleHandler.removeRole(i);
    }

    @SuppressLint("SetTextI18n")
    public void refreshCount() {
        textViewRolesCount.setText("" + RoleHandler.getCount());
    }

    private void clearRoles() {
        RoleHandler.clearRoles();
        for (ImageView imageView : slots)
            imageView.setImageResource(R.drawable.role_null);
        roleAdapterG.notifyDataSetChanged();
        roleAdapterW.notifyDataSetChanged();
        roleAdapterB.notifyDataSetChanged();
        refreshCount();
    }
}