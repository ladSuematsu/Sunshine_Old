package ladsoft.sunshine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ladsoft.sunshine.R;
import ladsoft.sunshine.entity.City;
import ladsoft.sunshine.entity.Forecast;
import ladsoft.sunshine.entity.ForecastResult;
import ladsoft.sunshine.entity.Temperature;
import ladsoft.sunshine.entity.Weather;
import ladsoft.sunshine.util.Constants;

/**
 * Created by suematsu on 6/7/15.
 */
public class ForecastRecyclerAdapter extends RecyclerView.Adapter<ForecastRecyclerAdapter.ViewHolder>{


    private ForecastResult forecastResult;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;

        public ViewHolder(View v) {

            super(v);

            mView = v;
        }

        protected View getView() { return mView; };
    }

    public ForecastRecyclerAdapter(ForecastResult forecastResult) {
        this.forecastResult = forecastResult;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_main, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View v = holder.getView();
        this.setView(v, this.forecastResult, position);
    }

    @Override
    public int getItemCount() {
        Forecast[] forecasts = forecastResult.getList();
        return forecasts != null ? forecasts.length : 0;
    }

    public void setForecastResult(ForecastResult forecastResult) {
        this.forecastResult = forecastResult;
    }

    protected void setView(View v, ForecastResult forecastResult, int position){
        TextView txtPlace = (TextView) v.findViewById(R.id.fragment_main_place);
        TextView txtStatus = (TextView) v.findViewById(R.id.fragment_main_weather_status);
        TextView txtDate = (TextView) v.findViewById(R.id.fragment_main_date);
        TextView txtMinTemp = (TextView) v.findViewById(R.id.fragment_main_min_temp);
        TextView txtMaxTemp = (TextView) v.findViewById(R.id.fragment_main_max_temp);

        City city = forecastResult.getCity();

        Forecast[] forecastList = forecastResult.getList();
        Forecast forecast = forecastList[position];


        Weather[] weatherList = forecast.getWeather();
        Weather weather = weatherList[0];

        Temperature temperature = forecast.getTemp();

        Date date = new Date(forecast.getDt());
        Context context = v.getContext();
        SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.date_format));

        Log.i(Constants.WS_TAG, "Setting views");

        txtPlace.setText(city.getName() + ", " + city.getCountry());
        txtStatus.setText(weather.getMain());
        txtDate.setText(dateFormat.format(date));
        txtMaxTemp.setText(temperature.getMax().toString());
        txtMinTemp.setText(temperature.getMin().toString());
    }

}
