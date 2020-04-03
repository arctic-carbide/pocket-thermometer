package umd.mad.p3;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherAPIReader {

    private String api_url_format = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private String api_key = "d45484139b3f7d8c333a5a6742492183";
    private Context context;
    private String city;
    private RequestQueue requestQueue;
    private JSONObject lastObject;
    private JsonReader jsonReader;

    private JSONObject main;




    public OpenWeatherAPIReader(Context context) {

        this.context = context;
        this.city = context.getResources().getStringArray(R.array.cities)[0];

        RequestJSONObject();
    }

    public void setCity(String city) { // we built this city on rock and roll
        this.city = city;
        RequestJSONObject();
    }

    private void RequestJSONObject() {

        String api_url = String.format(api_url_format, city, api_key); // constructs the final url

        // instantiate the request queue
        requestQueue = Volley.newRequestQueue(context); // used to fulfill requests

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
                                    lastObject = response;
                                    main = response.getJSONObject("main");

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

    public String getTemperature() throws JSONException {
        String temperature;

        temperature = Double.toString(main.getDouble("temp"));
        return temperature;
    }

    public String getTemperatureLow() throws JSONException {
        String low;

        low = Double.toString(main.getDouble("temp_min"));
        return low;
    }

    public String getTemperatureHigh() throws JSONException {
        String high;

        high = Double.toString(main.getDouble("temp_max"));
        return high;
    }

}
