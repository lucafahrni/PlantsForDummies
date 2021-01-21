package zli.ch.lf.testplants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
 * @quellen: keine
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button createReminder;
    Reminder reminder;
    RecyclerView recyclerview;
    Data database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createReminder = findViewById(R.id.btn_createReminder);
        recyclerview = findViewById(R.id.recyclerview);
        createReminder.setOnClickListener(this);
        database = Data.getDatabase(getApplicationContext());
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setPlantReminder();
    }

    private void setPlantReminder()
    {
        List<EntityKlasse> classList = database.ReminderDao().getAllData();
        reminder = new Reminder(getApplicationContext(), classList);
    }

    @Override
    public void onClick(View view)
    {
        if (view == createReminder) {
            CreatePlantReminder();
        }
    }

    private void CreatePlantReminder()
    {
        Intent intent = new Intent(getApplicationContext(), CreateReminder.class);
        startActivity(intent);
    }

}