package umd.mad.p3;

import android.content.Context;
import android.util.Log;

import androidx.annotation.StringRes;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherAPIReader {

    private String api_url_format = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=%s&appid=%s";
    private String api_key = "d45484139b3f7d8c333a5a6742492183";
    private String city;
    private RequestQueue requestQueue;
    private String units = Units.KELVIN;
    private JSONObject response;


    public static class Units {
        public static final String FAHRENHEIT = "imperial";
        public static final String CELSIUS = "metric";
        public static final String KELVIN = "standard";
    }


    public OpenWeatherAPIReader(Context context, String city) {

        // instantiate the request queue
        requestQueue = Volley.newRequestQueue(context); // used to fulfill requests
        this.city = city;

    }

    public void setCity(String city) { // we built this city on rock and roll
        this.city = city;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void prepareRequest() {

        String api_url = String.format(api_url_format, city, units, api_key); // constructs the final url

        // create object request
        JsonObjectRequest jsonObjectRequest = // defines the kind of request we want to do and how to handle it
                new JsonObjectRequest(
                        Request.Method.GET, // the request method
                        api_url, // the URL
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) { // I guess I'm supposed to capture the request here somehow

                                try {

                                    Log.i("JSON response", response.toString());
                                    Log.i("Main", response.getJSONObject("main").toString());
                                    Log.i("Temp", (Double.toString(response.getJSONObject("main").getDouble("temp"))));
                                    OpenWeatherAPIReader.this.response = response.getJSONObject("main");

                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.e("Volley Error", error.toString());

                            }
                        }
                );

        // add request to the queue
        requestQueue.add(jsonObjectRequest); // actually processes the request for json data

    }

    public void addOnRequestFinishedListener(RequestQueue.RequestFinishedListener listener) {
        requestQueue.addRequestFinishedListener(listener);
    }

    public String getAmbientTemperature() throws JSONException {
        String temperature;

        temperature = Double.toString(response.getDouble("temp"));
        return temperature;
    }

    public String getTemperatureLow() throws JSONException {
        String low;

        low = Double.toString(response.getDouble("temp_min"));
        return low;
    }

    public String getTemperatureHigh() throws JSONException {
        String high;

        high = Double.toString(response.getDouble("temp_max"));
        return high;
    }

    public String getTemperatureFeel() throws JSONException {
        String feel;

        feel = Double.toString(response.getDouble("feels_like"));
        return feel;
    }

    public Object getAttribute(String name) throws JSONException {
        return response.get(name);
    }

}
