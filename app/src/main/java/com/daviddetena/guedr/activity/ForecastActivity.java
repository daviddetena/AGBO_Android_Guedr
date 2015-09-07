package com.daviddetena.guedr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.daviddetena.guedr.R;
import com.daviddetena.guedr.model.Forecast;

public class ForecastActivity extends AppCompatActivity{

    // Modelo
    private Forecast mForecast;

    private ImageView mIcon;
    private TextView mMinTemp;
    private TextView mMaxTemp;
    private TextView mHumidity;
    private TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forecast);

        // Accedemos a las vistas
        mMaxTemp = (TextView) findViewById(R.id.max_temp);
        mMinTemp = (TextView) findViewById(R.id.min_temp);
        mHumidity = (TextView) findViewById(R.id.humidity);
        mDescription = (TextView) findViewById(R.id.forecast_description);
        mIcon = (ImageView) findViewById(R.id.forecast_image);

        // Sincronizamos modelo y vista
        setForecast(new Forecast(30, 15, 25, "Algunas nubes", "ico01"));
    }

    /**
     * Método para asignar Forecast con los datos que nos pasan, es decir, sincronizamos modelo y
     * vista.
     * @param forecast
     */
    public void setForecast(Forecast forecast){
        mForecast = forecast;

        // Utilizamos el format para pasarlo a @string y poder internacionalizar a idiomas
        mMaxTemp.setText(String.format(getString(R.string.max_temp_parameter), forecast.getMaxTemp()));
        mMinTemp.setText(String.format(getString(R.string.min_temp_parameter), forecast.getMinTemp()));
        mHumidity.setText(String.format(getString(R.string.humidity_parameter), forecast.getHumidity()));
        mDescription.setText(forecast.getDescription());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Creamos menú a partir del fichero xml forecast
        getMenuInflater().inflate(R.menu.forecast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Comprobamos que la opción de menú pulsada es la de ajustes
        if(item.getItemId() == R.id.menu_settings){
            // Si se pulsa el botón de ajustes del menú, lanzamos la pantalla de ajustes
            // Lanzamos la pantalla de ajustes sin esperar resultado
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
