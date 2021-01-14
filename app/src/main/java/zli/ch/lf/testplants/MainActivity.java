package zli.ch.lf.testplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/*
 * @author Luca Fahrni
 * @version 14.01.2021
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){

        Intent intent = new Intent(MainActivity.this, PlantReminder.class);
        startActivity(intent);

    }
}