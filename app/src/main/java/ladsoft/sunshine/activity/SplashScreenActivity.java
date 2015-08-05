package ladsoft.sunshine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import ladsoft.sunshine.R;
import ladsoft.sunshine.activity.util.SystemUiHider;

/**
 * Basic splash screen.
 */
public class SplashScreenActivity extends AppCompatActivity {

    /**
     * Splash screen activity show delay.
     */
    private static final int SPLASH_DELAY_MILLIS = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        final View contentView = findViewById(R.id.fullscreen_content);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                startMainActivity();
            }
        }, SPLASH_DELAY_MILLIS);

    }

    private void startMainActivity() {
        //finish();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, R.anim.abc_fade_out);

    }


}
