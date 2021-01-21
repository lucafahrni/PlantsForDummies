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
        setReminder();
    }

    private void setReminder()
    {
        List<EntityKlasse> classList = database.ReminderDao().getAllData();
        reminder = new Reminder(getApplicationContext(), classList);
        recyclerview.setAdapter(reminder);
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