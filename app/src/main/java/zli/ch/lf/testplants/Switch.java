package zli.ch.lf.testplants;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

/*
 * @author Luca Fahrni
 * @version 14.01.2021
 */
public class Switch extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    SwitchCompat mySwitch = null;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplant);
        mySwitch = (SwitchCompat) findViewById(R.id.switch1);
        mySwitch.setOnCheckedChangeListener(this);
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                    .createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);
            staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else {
            TextView every = (TextView) findViewById(R.id.textView3);
            EditText time =(EditText) findViewById(R.id.everytime);
            every.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
        }
    }
}
