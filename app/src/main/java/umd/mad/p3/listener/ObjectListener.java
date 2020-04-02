package umd.mad.p3.listener;

import android.util.Log;

import com.android.volley.Response;

import org.json.JSONObject;

public class ObjectListener implements Response.Listener <JSONObject> {
    @Override
    public void onResponse(JSONObject response) {
        Log.i("JSON Response", response.toString());
    }
}
