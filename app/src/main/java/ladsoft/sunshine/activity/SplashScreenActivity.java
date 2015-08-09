package ladsoft.sunshine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import ladsoft.animator.AnimatorHelper;
import ladsoft.sunshine.R;
//import ladsoft.sunshine.activity.util.SystemUiHider;

/**
 * Basic splash screen.
 */
public class SplashScreenActivity extends AppCompatActivity {

    /**
     * Splash screen activity show delay.
     */
    private static final int SPLASH_DELAY_MILLIS = 6000;


    /**
     * Views
     */
    private TextView mLogoView;
    private TextView mSubtitleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        final View contentView = findViewById(R.id.splashscreen_title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

       // if(savedInstanceState == null) { initViews(); }


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        initViews();


        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                startMainActivity();
            }
        }, SPLASH_DELAY_MILLIS);
    }

    /**
     * View mapping.
     */
    private void initViews() {

        mLogoView = (TextView) findViewById(R.id.splashscreen_title) ;
        mSubtitleView = (TextView) findViewById(R.id.splashscreen_subtitle);

        mLogoView.setVisibility(View.GONE);
        AnimatorHelper.fadeContent(mLogoView, true, AnimatorHelper.LONG_ANIMATION_DURATION);

        AnimatorHelper.slideContent(mSubtitleView
                , AnimatorHelper.SLIDE_BOTTOM_IN
                , false
                , AnimatorHelper.MEDIUM_ANIMATION_DURATION);


    }

    private void startMainActivity() {
        //finish();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, R.anim.abc_fade_out);

    }


}
