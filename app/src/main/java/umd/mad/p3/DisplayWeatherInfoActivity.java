package umd.mad.p3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayWeatherInfoActivity extends AppCompatActivity {

    OpenWeatherAPIReader weather;
    TextView cityName;
    TextView currentTemperatureValue;
    TextView currentTemperatureUnitSymbol;
    TextView ambientTemperatureValue;
    TextView ambientTemperatureSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weather_info);

        weather = new OpenWeatherAPIReader(this);

        cityName = findViewById(R.id.cityName);
        currentTemperatureValue = findViewById(R.id.currentTemperatureValue);
        currentTemperatureUnitSymbol = findViewById(R.id.currentTemperatureUnit);
        ambientTemperatureValue = findViewById(R.id.ambientTemperatureValue);
        ambientTemperatureSymbol = findViewById(R.id.ambientTemperatureUnit);



    }
}
