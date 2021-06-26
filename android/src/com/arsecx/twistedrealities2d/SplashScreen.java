package com.arsecx.twistedrealities2d;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class SplashScreen extends Activity implements MediaPlayer.OnCompletionListener
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        String fileName = "android.resource://"+  getPackageName() +"/raw/splash_animation";
        VideoView splashPlayer = this.findViewById(R.id.splashPlayer);
        splashPlayer.setVideoURI(Uri.parse(fileName));
        splashPlayer.setOnCompletionListener(this);
        splashPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
        Intent intent = new Intent(this, AndroidLauncher.class);
        startActivity(intent);
        finish();
    }
}