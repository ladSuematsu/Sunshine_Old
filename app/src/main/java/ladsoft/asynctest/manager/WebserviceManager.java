package ladsoft.asynctest.manager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ladsoft.asynctest.util.Constants;

/**
 * Handles webservice requests
 */
public class WebserviceManager {


    public static String weatherForecast() throws JSONException {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String jsonResponse = null; // Will contain the raw JSON response as a string.
        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are available at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");

            // Create the request to OpenWeatherMap, and open the connection.
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String.
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder stringBuilder =new StringBuilder();
            if (inputStream == null) {
                return null; // nothing to do
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                stringBuilder.append(line + "\n");
            }

            if(stringBuilder.length() == 0) {
                return null; // Stream was empty. No point in parsing.
            }
            jsonResponse = stringBuilder.toString();

            Log.d(Constants.CONST_LOG_D_TAG, "JSON response: " + jsonResponse);
        }
        catch(IOException e) {
            Log.e("weatherForecast()", "Error", e);

            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            return null;
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                }
                catch (final IOException e) {
                    Log.e("weatherForecast", "Error closing stream", e);
                }

            }
        }

        return jsonResponse;
    }

    public static double getMaxTemperatureForDay(String weatherJsonStr, int dayIndex)
            throws JSONException {
        // TODO: add parsing code here
        JSONObject jsonObject = new JSONObject(weatherJsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        JSONObject jsonWeather = jsonArray.getJSONObject(dayIndex);
        double maxTemp = jsonWeather.getJSONObject("temp")
                .getDouble("max");

        return maxTemp;
    }

}
