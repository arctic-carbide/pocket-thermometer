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

import org.json.JSONObject;

public class OpenWeatherAPIReader {

    private String api_url_format = "api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private String api_key = "d45484139b3f7d8c333a5a6742492183";
    private Context context;
    private String city;
    private RequestQueue requestQueue;
    private JsonReader jsonReader;




    public OpenWeatherAPIReader(Context context) {

        this.context = context;
        this.city = context.getResources().getStringArray(R.array.cities)[0];

    }

    public void setCity(String city) { // we built this city on rock and roll
        this.city = city;
    }

    private void RequestJSONObject(String url) {
        // instantiate the request queue
        requestQueue = Volley.newRequestQueue(context);

        // create object request
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET, // the request method
                        url, // the URL
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) { // I guess I'm supposed to capture the request here somehow
                                Log.i("JSON response", response.toString());
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
        requestQueue.add(jsonObjectRequest);

    }

    public String getTemperature() {
        String temperature = "";



        return temperature;
    }

    public String getAmbientTemperature() {

    }

}
