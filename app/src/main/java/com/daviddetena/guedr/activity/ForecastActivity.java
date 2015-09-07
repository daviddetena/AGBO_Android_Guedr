package com.daviddetena.guedr.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.daviddetena.guedr.R;

public class ForecastActivity extends AppCompatActivity{

    private ImageView mImageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.weather_image);
        final ToggleButton button = (ToggleButton) findViewById(R.id.change_system_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.setImageResource(button.isChecked() ? R.drawable.offline_weather : R.drawable.offline_weather2);
            }
        });
    }


}
