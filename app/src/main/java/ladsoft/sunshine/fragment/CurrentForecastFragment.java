package ladsoft.sunshine.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ladsoft.sunshine.R;
import ladsoft.sunshine.entity.City;
import ladsoft.sunshine.entity.Forecast;
import ladsoft.sunshine.entity.ForecastResult;
import ladsoft.sunshine.entity.Temperature;
import ladsoft.sunshine.entity.Weather;
import ladsoft.sunshine.listener.AsyncOperatorCallback;
import ladsoft.sunshine.manager.AsyncTaskManager;
import ladsoft.sunshine.util.Constants;

public class CurrentForecastFragment extends SwipeRefreshFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ListView mListView;

    private Context mContext;

    private View mContentView;
    private FrameLayout mFrameLayout;

    private final AsyncOperatorCallback<ForecastResult> callback = new AsyncOperatorCallback<ForecastResult>() {
        @Override
        public void onOperationCompleteSuccess(ForecastResult forecastResult) {
            setViews(forecastResult);
            setRefreshing(false);
        }

        @Override
        public void onOperationCompleteError() {
            setRefreshing(false);
        }
    };

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CurrentForecastFragment newInstance(int sectionNumber) {
        CurrentForecastFragment fragment = new CurrentForecastFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CurrentForecastFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mContext = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        mContentView = getLayoutInflater(savedInstanceState).inflate(R.layout.current_forecast_fragment, null);

        View contentView = getLayoutInflater(savedInstanceState).inflate(R.layout.fragment_main, null);
        FrameLayout fl = (FrameLayout) mContentView.findViewById(R.id.refresh_layout_content);
        fl.addView(contentView);

        mFrameLayout.addView(mContentView);

        this.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                asyncRefresh(callback);
            }
        });

        this.triggerSwipeRefresh();
        asyncRefresh(callback);
    }



    protected void asyncRefresh(final AsyncOperatorCallback callback){
        new AsyncTaskManager().getWeatherWebserviceRequest(callback);
    }

    protected void setViews(ForecastResult forecastResult){
        TextView txtPlace = (TextView) mContentView.findViewById(R.id.fragment_main_place);
        TextView txtStatus = (TextView) mContentView.findViewById(R.id.fragment_main_weather_status);
        TextView txtDate = (TextView) mContentView.findViewById(R.id.fragment_main_date);
        TextView txtMinTemp = (TextView) mContentView.findViewById(R.id.fragment_main_min_temp);
        TextView txtMaxTemp = (TextView) mContentView.findViewById(R.id.fragment_main_max_temp);

        City city = forecastResult.getCity();

        Forecast[] forecastList = forecastResult.getList();
        Forecast forecast = forecastList[0];


        Weather[] weatherList = forecast.getWeather();
        Weather weather = weatherList[0];

        Temperature temperature = forecast.getTemp();

        Date date = new Date(forecast.getDt());
        SimpleDateFormat dateFormat = new SimpleDateFormat(mContext.getString(R.string.date_format));

        Log.i(Constants.WS_TAG, "Setting views");

        txtPlace.setText(city.getName() + ", " + city.getCountry());
        txtStatus.setText(weather.getMain());
        txtDate.setText(dateFormat.format(date));
        txtMaxTemp.setText(temperature.getMax().toString());
        txtMinTemp.setText(temperature.getMin().toString());
    }

}