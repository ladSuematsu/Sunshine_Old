package ladsoft.asynctest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import ladsoft.asynctest.R;
//import ladsoft.asynctest.adapter.SectionsPagerAdapter;
import ladsoft.asynctest.fragment.CurrentForecastFragment;
import ladsoft.asynctest.fragment.SwipeRefreshFragment;


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
    private FrameLayout mFrameLayout;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), this);

        mFrameLayout = (FrameLayout) findViewById(R.id.main_frame_layout);
        FragmentManager fm = this.getSupportFragmentManager();


        if(savedInstanceState == null) {
            fm.beginTransaction()
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.action_settings:
            break;

            case R.id.action_about:
                Intent intent = new Intent(mContext, AboutActivity.class);
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

                startActivity(intent);

            break;
        }

        return super.onOptionsItemSelected(item);
    }

}

