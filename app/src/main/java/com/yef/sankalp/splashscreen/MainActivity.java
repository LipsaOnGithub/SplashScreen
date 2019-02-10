package com.yef.sankalp.splashscreen;

import android.content.Intent;
import android.media.tv.TvContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private int Sleep_Timer = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Logo logoLauncher = new Logo();
        logoLauncher.start();
        setContentView(R.layout.activity_main);
    }

    private class Logo extends Thread {
        public void run() {
            try {
                sleep(2000 * Sleep_Timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    }
}

