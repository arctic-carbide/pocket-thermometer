package umd.mad.p3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

public class DisplayWeatherInfoActivity extends AppCompatActivity {

    OpenWeatherAPIReader weather;
    TextView cityName;
    TextView currentTemperatureValue;
    TextView ambientTemperatureValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weather_info);

        weather = new OpenWeatherAPIReader(this);

        cityName = findViewById(R.id.cityName);
        currentTemperatureValue = findViewById(R.id.currentTemperatureValue);
        ambientTemperatureValue = findViewById(R.id.ambientTemperatureValue);

        try {
            currentTemperatureValue.setText(weather.getTemperature());
            ambientTemperatureValue.setText(weather.getTemperatureHigh());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
