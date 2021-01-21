package zli.ch.lf.testplants;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zli.ch.lf.testplants.Data.Data;
import zli.ch.lf.testplants.Data.EntityKlasse;

/*
 * @author Luca Fahrni
 * @version 21.01.2021
 * @class: ReminderDAO
 *
 * Main Activity von PlantsForDummies
 * onResume, on Create, setPlantReminder, CreatePlantReminder und onClick Methoden
 * sorgen dafür das Button-Klicks, PlantReminders und alles andere auch richtig erkannt und verarbeitet wird
 *
 *  @quelle: https://projectnotes.org/it-projects/simple-reminder-android-application-with-source-code/
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    ReminderAdapter reminderAdapter;
    RecyclerView recyclerview;
    Data data;
    Button createPlantReminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        recyclerview = findViewById(R.id.recyclerview);
        createPlantReminder.setOnClickListener(this);
        data = Data.getDatabase(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createPlantReminder = findViewById(R.id.btn_newPlantReminder);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setReminderAdapter();
    }

    private void setReminderAdapter()
    {
        List<EntityKlasse> classList = data.PlantReminderDao().getAllData();
        reminderAdapter = new ReminderAdapter(getApplicationContext(), classList);
        recyclerview.setAdapter(reminderAdapter);
    }

    @Override
    public void onClick(View view)
    {
        if (view == createPlantReminder) {
            CreatePlantReminder();
        }
    }

    private void CreatePlantReminder()
    {
        Intent intent = new Intent(getApplicationContext(), CreatePlantReminder.class);
        startActivity(intent);
    }
}