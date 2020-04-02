package umd.mad.p3.listener;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ArrayListener implements Response.Listener <JSONArray> {

    @Override
    public void onResponse(JSONArray response) {
        String message = "";
        JSONObject jsonObject;
        for (int i = 0; i < response.length(); i++) {
            try {
                jsonObject = response.getJSONObject(i);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
