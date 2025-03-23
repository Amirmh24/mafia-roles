package com.example.mafia.Views;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mafia.Adapters.PlayerAdapter;
import com.example.mafia.Handler.GameHandler;
import com.example.mafia.Handler.ScreenHandler;
import com.example.mafia.R;
import com.example.mafia.Utils;

import java.util.Objects;

public class NightDialog extends Dialog {

    private NightDialog(@NonNull Context context, String role) {
        super(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_night);
        ScreenHandler.setUpDialogRoleDecor(Objects.requireNonNull(getWindow()));
        ImageView imgRole = findViewById(R.id.imageViewDialogRoleNight);
        TextView txtName = findViewById(R.id.textViewDialogRoleName);
        TextView txtRole = findViewById(R.id.textViewDialogRoleRole);
        ListView listView = findViewById(R.id.listViewPlayers);

        txtName.setText(GameHandler.getNameByRole(role));
        txtRole.setText(Utils.firstCapital(role));
        int id = context.getResources().getIdentifier("role_" + role, "drawable", context.getPackageName());
        imgRole.setImageResource(id);
        int w = (int)((double)ScreenHandler.getWidth() *0.6);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, 2 * w);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMarginStart(-w/10);
        imgRole.setLayoutParams(params);
        PlayerAdapter playerAdapter = new PlayerAdapter(context, GameHandler.getAlivePlayersNames());
        listView.setAdapter(playerAdapter);
        Button buttonNext = findViewById(R.id.buttonNextRole);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                NightDialog.createDialog(context, GameHandler.nextRole());
            }
        });

    }

    public static void createDialog(Context context, String role) {
        if (role.equals("end"))
            GameHandler.resetNight();
        else {
            NightDialog nightDialog = new NightDialog(context, role);
            nightDialog.show();
        }
    }
}
