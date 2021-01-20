package zli.ch.lf.testplants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zli.ch.lf.testplants.Data.DatabaseKlasse;
import zli.ch.lf.testplants.Data.EntityKlasse;
import zli.ch.lf.testplants.Adapter.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button createReminder;
    EventAdapter reminderAdapter;
    RecyclerView recyclerview;
    DatabaseKlasse database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createReminder = findViewById(R.id.btn_createReminder);
        recyclerview = findViewById(R.id.recyclerview);
        createReminder.setOnClickListener(this);
        database = DatabaseKlasse.getDatabase(getApplicationContext());
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setAdapter();
    }

    private void setAdapter()
    {
        List<EntityKlasse> classList = database.ReminderDao().getAllData();
        reminderAdapter = new EventAdapter(getApplicationContext(), classList);
        recyclerview.setAdapter(reminderAdapter);
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