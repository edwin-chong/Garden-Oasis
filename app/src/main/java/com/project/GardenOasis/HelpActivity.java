package com.project.GardenOasis;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**Contains description of game and method which plays music at the very beginning of this scene*/
public class HelpActivity extends AppCompatActivity
{
    private TextView descriptionTextView;
    private TextView titleTextView;
    private MusicManager helpMusicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //checks if a MenuInflater object exists in memory, used to delete double bar icons
        getMenuInflater();
        // Permanently leaves screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        playMusic();
        descriptionTextView = findViewById(R.id.gameDescriptionTextView);
        descriptionTextView.setText(getText());
        titleTextView = findViewById(R.id.howToPlayTextView);
    }

    private String getText()
    {
        String text =
                "The main goal of the game is complete 3 planting quests.\n\n You win once you have completed them all.\n\n" +
                "Once you complete quest you get coins, needed for planting seeds. You can also get coins by collecting grown plants\n \n"+
                "To remind yourself a quest click parchment in the upper left corner. \n\n"+
                "To show more plants click an arrow pointing up \n \n"+
                "You don't need more hints to play. Wish you a lot of luck!";

        return text;
    }

    private String getTextTitle()
    {
        String title = "How to play the game?";
        return title;
    }

    public void text(View view)
    {
        descriptionTextView.setText(getText());
        titleTextView.setText(getTextTitle());
    }

    public void playGame(View view)
    {
        startActivity(new Intent(HelpActivity.this, PlayGameActivity.class));
        helpMusicManager.stopMusic();
    }

    public void exitGame (View view)
    {
        finishAffinity();
    }

    private void playMusic()
    {
        helpMusicManager = new MusicManager(this, R.raw.menu);
        helpMusicManager.run();
    }
}
