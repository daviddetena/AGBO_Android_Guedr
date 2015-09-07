package com.daviddetena.guedr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.weather_image);
        findViewById(R.id.system1_button).setOnClickListener(this);
        findViewById(R.id.system2_button).setOnClickListener(this);
    }

    /**
     * Comprobamos el botón pulsado y comunicamos nuestro controlador con la vista
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.system1_button:
                mImageView.setImageResource(R.drawable.offline_weather);
                break;

            case R.id.system2_button:
                mImageView.setImageResource(R.drawable.offline_weather2);
                break;
        }
    }
}
