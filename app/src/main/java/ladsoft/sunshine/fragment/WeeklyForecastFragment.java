package ladsoft.sunshine.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;

import ladsoft.sunshine.R;
import ladsoft.sunshine.activity.MainActivity;
import ladsoft.sunshine.activity.SettingsActivity;
import ladsoft.sunshine.adapter.ForecastRecyclerAdapter;
import ladsoft.sunshine.entity.ForecastResult;
import ladsoft.sunshine.listener.AsyncOperatorCallback;
import ladsoft.sunshine.manager.AsyncTaskManager;

public class WeeklyForecastFragment extends SwipeRefreshFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int SPAN_COUNT = 2;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ListView mListView;

    private Context mContext;

    private RecyclerView mRecyclerView;
    private FrameLayout mFrameLayout;
    private SharedPreferences mSharedPreferences;
    private String mPlace;
    private URL mURL;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER
        , LINEAR_LAYOUT_MANAGER
    }
    private LayoutManagerType mLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;

    private ForecastRecyclerAdapter mAdapter;
    private final AsyncOperatorCallback<ForecastResult> callback = new AsyncOperatorCallback<ForecastResult>() {
        @Override
        public void onOperationCompleteSuccess(ForecastResult forecastResult) {
            refreshData(forecastResult);
            setRefreshing(false);
        }

        @Override
        public void onOperationCompleteError() {
            setRefreshing(false);
        }
    };

    private void refreshData(ForecastResult forecastResult) {
        mAdapter = new ForecastRecyclerAdapter(forecastResult);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static WeeklyForecastFragment newInstance(int sectionNumber) {
        WeeklyForecastFragment fragment = new WeeklyForecastFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public WeeklyForecastFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mContext = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = mContext.getSharedPreferences(SettingsActivity.FORECAST_SETTINGS, Context.MODE_PRIVATE);
        mPlace = mSharedPreferences.getString(SettingsActivity.FORECAST_PLACE_SETTING, null);

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme("http")
                .authority("api.openweathermap.org")
                .appendPath("data")
                .appendPath("2.5")
                .appendPath("forecast")
                .appendPath("daily")
                .appendQueryParameter("q", mPlace)
                .appendQueryParameter("mode", "json")
                .appendQueryParameter("units","metric")
                .appendQueryParameter("cnt", "7");
        try {
            mURL = new URL(uriBuilder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.action_refresh:
                this.triggerSwipeRefresh();
                asyncRefresh(callback);
            break;
        }

        return true;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFrameLayout = (FrameLayout) view.findViewById(R.id.swipe_refresh_layout_content);

        mRecyclerView = (RecyclerView) getLayoutInflater(savedInstanceState).inflate(R.layout.weekly_forecast_fragment, null);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition =
                        (mRecyclerView == null || mRecyclerView.getChildCount() == 0) ? 0 : mRecyclerView.getChildAt(0).getTop();
                setEnabled(topRowVerticalPosition >= 0);
            }
        });
        mFrameLayout.addView(mRecyclerView);

        mAdapter = new ForecastRecyclerAdapter(new ForecastResult());
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        this.setRecyclerViewLayoutManager(mLayoutManagerType);

        this.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                asyncRefresh(callback);
            }
        });

        this.triggerSwipeRefresh();
        asyncRefresh(callback);
    }

    private void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        if(mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                                .findFirstCompletelyVisibleItemPosition();

        }

        switch(layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(this.getActivity(), SPAN_COUNT);
                mLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
            break;

            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(this.getActivity());
                mLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
            break;

            default:
                mLayoutManager = new LinearLayoutManager(this.getActivity());
                mLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }


    protected void asyncRefresh(final AsyncOperatorCallback callback){
        new AsyncTaskManager().getWeatherWebserviceRequest(mURL, callback);
    }


}