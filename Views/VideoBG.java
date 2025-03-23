package com.example.mafia.Views;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.VideoView;
import android.net.Uri;

import com.example.mafia.R;

public class VideoBG extends VideoView {
    public VideoBG(Context context) {
        super(context);
        create();
    }
    public VideoBG(Context context, AttributeSet attrs) {
        super(context, attrs);
        create();
    }

    public VideoBG(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        create();
    }
    private void create(){
//        Uri uri = Uri.parse("android.resource://com.example.mafia/" + R.raw.viss);
//        setVideoURI(uri);
        setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                start();
            }
        });
    }
}
