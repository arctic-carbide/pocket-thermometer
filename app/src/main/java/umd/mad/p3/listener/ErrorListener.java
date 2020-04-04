package umd.mad.p3.listener;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class ErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Volley Error", error.getMessage());
    }
}
