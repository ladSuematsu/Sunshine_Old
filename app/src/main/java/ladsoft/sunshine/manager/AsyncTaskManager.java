package ladsoft.sunshine.manager;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import ladsoft.sunshine.entity.ForecastResult;
import ladsoft.sunshine.listener.AsyncOperatorCallback;
import ladsoft.sunshine.util.Constants;

/**
 * Handles async tasks
 */
public class AsyncTaskManager {

    public void getWeatherWebserviceRequest(URL url, final AsyncOperatorCallback<ForecastResult> callback){
        new AsyncTask<URL, Void, ForecastResult>(){

            @Override
            protected void onPreExecute () {
            super.onPreExecute();
                Log.i(Constants.WS_TAG, "getWeatherWebserviceRequest onPreExecute");
            }

            @Override
            protected ForecastResult doInBackground (URL ...params){
                Log.i(Constants.WS_TAG, "getWeatherWebserviceRequest doInBackground");

                // Only for testing purposes
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Gson gson = new Gson();

                String jsonForecastResult = null;
                ForecastResult forecastResult = null;
                try {
                    //URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");

                    jsonForecastResult = WebserviceManager.weatherForecast(params[0]);

                    forecastResult = gson.fromJson(jsonForecastResult, ForecastResult.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return forecastResult;
            }

            @Override
            protected void onPostExecute (ForecastResult result){

                callback.onOperationCompleteSuccess(result);

                Log.i(Constants.WS_TAG, "getWeatherWebserviceRequest onPostExecute");
            }
        }.execute(url);
    }
}
