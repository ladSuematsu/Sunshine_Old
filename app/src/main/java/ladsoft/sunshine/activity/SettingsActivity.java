package ladsoft.sunshine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import ladsoft.sunshine.R;


public class SettingsActivity extends AppCompatActivity {

    public static final String FORECAST_SETTINGS = "forecast_settings";
    public static final String FORECAST_PLACE_SETTING = "place";


    private Context mContext;
    private Toolbar mToolbar;
    private SharedPreferences mSharedPreferences;
    private EditText mEditTextForecastPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mSharedPreferences = getSharedPreferences(FORECAST_SETTINGS, MODE_PRIVATE);


        this.init();
    }

    private void init() {
        mContext = this;

        // Sets Toolbar.
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        //mActionBar.setHomeButtonEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        // Sets Options
        mEditTextForecastPlace = (EditText) findViewById(R.id.edittext_place_settings);
        mEditTextForecastPlace.setText(mSharedPreferences.getString(FORECAST_PLACE_SETTING, ""));
        mEditTextForecastPlace.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String value = v.getText().toString();

                if(value == null || value == ""){
                    return false;
                }

                mSharedPreferences.edit()
                    .putString(FORECAST_PLACE_SETTING, value)
                    .commit();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);

                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                }
                else {
                    NavUtils.navigateUpTo(this, upIntent);
                    //NavUtils.navigateUpFromSameTask(this);
                }
            break;
        }


        return super.onOptionsItemSelected(item);
    }

//    @Nullable
//    @Override
//    public Intent getParentActivityIntent() {
//        //return super.getParentActivityIntent();
//        Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        return intent;
//    }
}
