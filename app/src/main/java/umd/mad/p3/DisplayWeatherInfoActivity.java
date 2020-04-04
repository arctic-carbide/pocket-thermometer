package umd.mad.p3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.json.JSONException;

public class DisplayWeatherInfoActivity extends AppCompatActivity implements RequestQueue.RequestFinishedListener {

    OpenWeatherAPIReader weather;
    TextView cityName;
    TextView currentTemperatureValue;
    TextView ambientTemperatureValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weather_info);

        Intent starter = getIntent();
        String message = starter.getStringExtra("city_name");

        weather = new OpenWeatherAPIReader(this, message);
        weather.addOnRequestFinishedListener(this);

        cityName = findViewById(R.id.cityName);
        currentTemperatureValue = findViewById(R.id.currentTemperatureValue);
        ambientTemperatureValue = findViewById(R.id.ambientTemperatureValue);

        cityName.setText(message);

    }

    private void updateTemperatureValues() {
        try {
            currentTemperatureValue.setText(weather.getTemperature());
            ambientTemperatureValue.setText(weather.getTemperatureHigh());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestFinished(Request request) {
        updateTemperatureValues();
    }
}
