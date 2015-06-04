package ladsoft.asynctest.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;

import ladsoft.asynctest.entity.ForecastResult;
import ladsoft.asynctest.listener.AsyncOperatorCallback;
import ladsoft.asynctest.util.Constants;

/**
 * Handles async tasks
 */
public class AsyncTaskManager {

    public void getWeatherWebserviceRequest(final AsyncOperatorCallback<ForecastResult> callback){
        new AsyncTask<Void, Void, ForecastResult>(){
            @Override
            protected void onPreExecute () {
            super.onPreExecute();
                Log.i(Constants.WS_TAG, "getWeatherWebserviceRequest onPreExecute");
            }

            @Override
            protected ForecastResult doInBackground (Void...params){
                Log.i(Constants.WS_TAG, "getWeatherWebserviceRequest doInBackground");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Gson gson = new Gson();

                String jsonForecastResult = null;
                ForecastResult forecastResult = null;
                try {
                    jsonForecastResult = WebserviceManager.weatherForecast();
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
        }.execute();
    }
}
