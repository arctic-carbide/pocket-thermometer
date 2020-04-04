package umd.mad.p3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;

public class DisplayWeatherInfoActivity extends AppCompatActivity implements AsyncLayoutInflater.OnInflateFinishedListener {

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


    }

    @Override
    public void onInflateFinished(@NonNull View view, int resid, @Nullable ViewGroup parent) {

        try {
            currentTemperatureValue.setText(weather.getTemperature());
            ambientTemperatureValue.setText(weather.getTemperatureHigh());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
