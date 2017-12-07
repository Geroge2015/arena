package com.example.cm.testrv.media;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.cm.testrv.R;
import com.example.cm.testrv.utils.CommonUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by George on 2017/12/7.
 */

public class MyMediaActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int MY_MEDIA_REQUEST_CODE = 2;

    MediaPlayer mediaPlayer = new MediaPlayer();
    VideoView videoView;

    public static boolean startActivity(Context context) {
        Intent it = new Intent(context, MyMediaActivity.class);
        return CommonUtils.startActivity(context, it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_music_play);
        initView();
    }

    private void initView() {
        Button musicPlay = (Button) findViewById(R.id.music_play);
        Button musicPause = (Button) findViewById(R.id.music_pause);
        Button musicStop = (Button) findViewById(R.id.music_stop);
        Button videoPlay = (Button) findViewById(R.id.video_play);
        Button videoPause = (Button) findViewById(R.id.video_pause);
        Button videoReplay = (Button) findViewById(R.id.video_replay);
        videoView = (VideoView) findViewById(R.id.video_show_view);
        musicPlay.setOnClickListener(this);
        musicPause.setOnClickListener(this);
        musicStop.setOnClickListener(this);
        videoPlay.setOnClickListener(this);
        videoPause.setOnClickListener(this);
        videoReplay.setOnClickListener(this);
        //check permissons:
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this
                    , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_MEDIA_REQUEST_CODE);
        } else {
            initMediaPlayer();
            initVideoPlayer();
        }
    }

    private void initVideoPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
        videoView.setVideoPath(file.getPath());
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
            mediaPlayer.setDataSource(file.getPath());    // assign the path of the audio file.
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_MEDIA_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                    initVideoPlayer();
                } else {
                    Toast.makeText(this, "Failed because you denied the permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.music_pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.music_stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();    //  player.reset NOT player.stop
                    initMediaPlayer();
                }
                break;
            case R.id.video_play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.video_pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.video_replay:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
