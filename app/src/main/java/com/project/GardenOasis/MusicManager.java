package com.project.GardenOasis;

import android.content.Context;
import android.media.MediaPlayer;

/**Sets, plays and stops music*/
public class MusicManager extends Thread {
    private MediaPlayer mediaPlayer;
    private Context context;
    private int musicResId;

    public MusicManager(Context context, int musicResId) {
        this.context = context;
        this.musicResId = musicResId;
    }

    @Override
    public void run() {
        mediaPlayer = MediaPlayer.create(context, musicResId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
