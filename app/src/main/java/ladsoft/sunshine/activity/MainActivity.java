package ladsoft.sunshine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import ladsoft.sunshine.R;
//import ladsoft.asynctest.adapter.SectionsPagerAdapter;
import ladsoft.sunshine.fragment.CurrentForecastFragment;
import ladsoft.sunshine.fragment.WeeklyForecastFragment;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    //SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
//    private SlidingTabLayout mSlidingTabLayout;
//    private ViewPager mViewPager;
    private Context mContext;

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationDrawer;
    private FrameLayout mFrameLayout;
    private FragmentManager fragmentManager;

    private SharedPreferences mForecastPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mForecastPreferences = this.getSharedPreferences(SettingsActivity.FORECAST_SETTINGS, MODE_PRIVATE);

        if(mForecastPreferences.getString("place", null) == null) {
            mForecastPreferences.edit()
                    .putString(SettingsActivity.FORECAST_PLACE_SETTING, "94043")
                    .commit();
        }


        init(savedInstanceState);

    }

    private void init(Bundle savedInstanceState) {
        mContext = this;

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mNavigationDrawer = (NavigationView) findViewById(R.id.navigation_drawer);
        mFrameLayout = (FrameLayout) findViewById(R.id.main_frame_layout);
        fragmentManager = this.getSupportFragmentManager();

        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        //mActionBar.setHomeButtonEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black);
        mActionBar.setDisplayHomeAsUpEnabled(true);


        mNavigationDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                boolean itemSelected = true;
                Intent intent;

                switch(id) {
                    case R.id.navigation_item_1:
                        fragmentManager.beginTransaction()
                                .replace(R.id.main_frame_layout, new CurrentForecastFragment())
                                .commit();
                    break;

                    case R.id.navigation_item_2:
                        fragmentManager.beginTransaction()
                                .replace(R.id.main_frame_layout, new WeeklyForecastFragment())
                                .commit();
                        break;

                    case R.id.navigation_item_3:
                        intent = new Intent(mContext, SettingsActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_item_4:
                        intent = new Intent(mContext, AboutActivity.class);
                        startActivity(intent);
                    break;

                    default:
                        itemSelected = false;
                    break;

                }

                if(itemSelected){
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }

                return itemSelected;
            }


        });



        if(savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(mFrameLayout.getId(), new CurrentForecastFragment())
                    .commit();
        }

//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//        // Set up ViewPager reference into SlidingTabLayout
//        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
//        mSlidingTabLayout.setDistributeEvenly(true);
//        mSlidingTabLayout.setViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        mToolbar.inflateMenu(R.menu.menu_main);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id)
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
            return true;



//            case R.id.action_settings:
//            break;

//            case R.id.action_about:
//                Intent intent = new Intent(mContext, AboutActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//                PendingIntent pendingIntent = TaskStackBuilder.create(mContext)
//                        .addNextIntent(new Intent(mContext, MainActivity.class))
//                        .addNextIntent(intent);
                        //.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

//                PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

//                try {
//                    pendingIntent.send();
//                } catch (PendingIntent.CanceledException e) {
//                    e.printStackTrace();
//                }

//                startActivity(intent);

            //break;
        }

        return super.onOptionsItemSelected(item);
    }

}

