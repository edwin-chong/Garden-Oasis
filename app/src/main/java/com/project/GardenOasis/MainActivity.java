package com.project.GardenOasis;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**Main game menu class. Contains all methods connected with buttons on the main GUI and method which enables playing the music*/
public class MainActivity extends AppCompatActivity
{
    private MusicManager menuMusicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Permanently leaves screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
        playMusic();

        //checks if a MenuInflater object exists in memory, used to delete double bar icons
        getMenuInflater();
    }

    public void playAction(View view)
    {
        startActivity(new Intent(MainActivity.this, PlayGameActivity.class));
        menuMusicManager.stopMusic();
    }

    public void helpAction(View view)
    {
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
        startActivity(intent);
        menuMusicManager.stopMusic();
    }

    public void exitGame (View view)
    {
        finish();
    }

    private void playMusic()
    {
        menuMusicManager = new MusicManager(this, R.raw.menu);
        menuMusicManager.run();
    }
}
