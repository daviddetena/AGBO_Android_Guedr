package com.daviddetena.guedr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = (ImageView) findViewById(R.id.weather_image);
        Button changeButton = (Button) findViewById(R.id.change_system_button);

        // Clase anónima para el listener del botón
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiamos imagen al pulsar el botón
                imageView.setImageResource(R.drawable.offline_weather2);
            }
        });
    }
}
