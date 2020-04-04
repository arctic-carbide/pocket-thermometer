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
    TextView ambientTemperatureValue;
    TextView feelsLikeTemperatureValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weather_info);

        Intent starter = getIntent();
        String message = starter.getStringExtra("city_name");

        weather = new OpenWeatherAPIReader(this, message);
        weather.setUnits(OpenWeatherAPIReader.Units.FAHRENHEIT);
        weather.addOnRequestFinishedListener(this);
        weather.prepareRequest();

        cityName = findViewById(R.id.cityName);
        ambientTemperatureValue = findViewById(R.id.ambientTemperatureValue);
        feelsLikeTemperatureValue = findViewById(R.id.feelsLikeTemperatureValue);

        cityName.setText(message);

    }

    private void updateTemperatureValues() {
        try {
            ambientTemperatureValue.setText(weather.getAmbientTemperature()); // the current temperature in the area
            feelsLikeTemperatureValue.setText(weather.getTemperatureFeel()); // what the temperature feels like in the area
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
