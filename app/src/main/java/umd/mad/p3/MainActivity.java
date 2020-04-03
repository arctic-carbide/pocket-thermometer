package umd.mad.p3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OpenWeatherAPIReader reader = new OpenWeatherAPIReader(this);

    }



    private void JSONObjectRequest(String url) {
        // instantiate the request queue
        requestQueue = Volley.newRequestQueue(this);

        // create object request
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET, // the request method
                        url, // the URL
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
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

    private void JSONArrayRequestTest(String url) {
        // instantiate the request queue
        requestQueue = Volley.newRequestQueue(this);

        // create array request
        final JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(
                        Request.Method.GET, // the request method
                        url, // the URL
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        Log.d("JSONArray", "onResponse: "
                                                + jsonObject.getString("id") + " "
                                                + jsonObject.getString("title"));
                                        boolean d = jsonObject.getBoolean("completed");
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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
        requestQueue.add(jsonArrayRequest);
    }
}
