package com.example.administrator.carstereo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


public class CarStereo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_stereo);
    }
    int i = 1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_stereo, menu);
        final SeekBar tuner = (SeekBar)findViewById(R.id.tuner);
        Button power = (Button) findViewById(R.id.powerButton);
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button power = (Button) findViewById(R.id.powerButton);
                TextView station = (TextView) findViewById(R.id.Station);
                TextView volume = (TextView) findViewById(R.id.volLevel);
                Button eject = (Button) findViewById(R.id.ejectButton);
                Switch amFm = (Switch)findViewById(R.id.amfmSwitch);
                if (i % 2 != 0) {
                    station.setBackgroundColor(Color.parseColor("#FF000000"));
                    volume.setBackgroundColor(Color.parseColor("#FF000000"));
                    power.setTextColor(Color.parseColor("#FF000000"));
                    eject.setTextColor(Color.parseColor("#FF000000"));
                    amFm.setEnabled(false);
                    tuner.setEnabled(false);

                } else {
                    power.setTextColor(Color.parseColor("#ff00df00"));
                    eject.setTextColor(Color.parseColor("#ff00df00"));
                    station.setBackgroundColor(Color.parseColor("#ff00df00"));
                    volume.setBackgroundColor(Color.parseColor("#ff00df00"));
                    amFm.setEnabled(true);
                    tuner.setEnabled(true);
                }
                i++;

            }
        });
        String suffix;
        String current;

        tuner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar arg0) {

            }
            public void onStartTrackingTouch(SeekBar arg0){

            }
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2)
            {
               Switch amFm = (Switch)findViewById(R.id.amfmSwitch);
                if(amFm.isChecked())
                {
                    int min = 881;
                    int max = 1079;
                    int step = 2;
                    String suffix = "MHz";
                    tuner.setMax(99);
                    String current = "" + (double)((step*tuner.getProgress())+(int)min)/10+suffix;
                    TextView station = (TextView) findViewById(R.id.Station);
                    station.setText(current);
                }
                else{
                    int min = 53;
                    int max = 170;
                    int step = 10;
                    String suffix = "kHz";
                    tuner.setMax(170-53);
                    String current = "" + (tuner.getProgress()+(int)min)*10+suffix;
                    TextView station = (TextView) findViewById(R.id.Station);
                    station.setText(current);
                }

            }
        });

        Switch amFm = (Switch)findViewById(R.id.amfmSwitch);
        amFm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SeekBar tuner = (SeekBar) findViewById(R.id.tuner);
                if (isChecked) {
                    int min = 881;
                    int max = 1079;
                    int step = 2;
                    String suffix = "MHz";
                    tuner.setMax(99);
                    String current = "" + (double)((step*tuner.getProgress())+min)/10+suffix;
                    TextView station = (TextView) findViewById(R.id.Station);
                    station.setText(current);

                }
                else {
                    int min = 53;
                    int max = 170;
                    int step = 10;
                    String suffix = "kHz";
                    tuner.setMax(170-53);
                    String current = "" + (tuner.getProgress()+min)*10+suffix;
                    TextView station = (TextView) findViewById(R.id.Station);
                    station.setText(current);
                }
            }
        });

        return true;
        }







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
