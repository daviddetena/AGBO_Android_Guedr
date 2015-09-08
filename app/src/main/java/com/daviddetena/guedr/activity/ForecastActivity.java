package com.daviddetena.guedr.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    // Atributo con el que muestro la temperatura en cada momento
    private int mCurrentMetrics;


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

        // Obtengo referencia a preferencias. Todas las activity tienen un método
        // getString(clave_pref_por_la_que_accedo, valor_defecto_si_no_hay_nada_seleccionado -[0:celsius])
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String stringMetrics = pref.getString(getString(R.string.metric_selection),
                String.valueOf(SettingsActivity.PREF_CELSIUS));

        mCurrentMetrics = Integer.valueOf(stringMetrics);

        // Sincronizamos modelo y vista
        setForecast(new Forecast(30, 15, 25, "Algunas nubes", "ico01"));
    }


    /**
     * Convertimos unidades C en F
     * @param celsius
     * @return
     */
    protected static float toFarenheit(float celsius){
        return (celsius * 1.8f) + 32;
    }


    /**
     * Método para asignar Forecast con los datos que nos pasan, es decir, sincronizamos modelo y
     * vista.
     * @param forecast
     */
    public void setForecast(Forecast forecast){
        mForecast = forecast;

        // Por defecto en celsius, y si no, convertimos a Farenheit
        float maxTemp = mCurrentMetrics == SettingsActivity.PREF_CELSIUS ? forecast.getMaxTemp() : toFarenheit(forecast.getMaxTemp());
        float minTemp = mCurrentMetrics == SettingsActivity.PREF_CELSIUS ? forecast.getMinTemp() : toFarenheit(forecast.getMinTemp());

        String metricString;
        if(mCurrentMetrics == SettingsActivity.PREF_CELSIUS){
            metricString = "ºC";
        }
        else{
            metricString = "ºF";
        }

        // Utilizamos el format para pasarlo a @string y poder internacionalizar a idiomas
        mMaxTemp.setText(String.format(getString(R.string.max_temp_parameter), maxTemp, metricString));
        mMinTemp.setText(String.format(getString(R.string.min_temp_parameter), minTemp, metricString));
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

    /**
     * Queremos que cuando se vuelva a mostrar esta vista se actualice el valor de las preferencias
     * de grados
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Obtengo referencia a preferencias. Todas las activity tienen un método
        // getString(clave_pref_por_la_que_accedo, valor_defecto_si_no_hay_nada_seleccionado -[0:celsius])
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String stringMetrics = pref.getString(getString(R.string.metric_selection),
                String.valueOf(SettingsActivity.PREF_CELSIUS));

        int metrics = Integer.valueOf(stringMetrics);

        // Si el usuario ha seleccionado otra opción de metricas, actualizo la variable y el
        // modelo. También meto una snackbar para indicar al usuario que los ajustes se han
        // modificado.
        if(metrics != mCurrentMetrics){
            final int previousMetrics = mCurrentMetrics;
            mCurrentMetrics = metrics;
            setForecast(mForecast);

            // Snackbar, similar a Toast. Necesitamos saber la vista raíz. Incluimos una acción
            // para deshacer los cambios en las preferencias de las métricas, al pulsar en botón
            // deshacer
            // Cambiamos commit() por apply() para que vaya a segundo plano y no necesite respuesta
            Snackbar.make(
                    findViewById(android.R.id.content),
                    R.string.updated_preferences,
                    Snackbar.LENGTH_LONG)
                    .setAction(R.string.undo, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Deshacemos cambios
                            // Entramos en modo edición
                            pref.edit().putString(getString(R.string.metric_selection),
                                        String.valueOf(previousMetrics))
                                        .apply();
                            mCurrentMetrics = previousMetrics;
                            setForecast(mForecast);
                        }
                    })
                    .show();
        }
    }
}
