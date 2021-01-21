package zli.ch.lf.testplants;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import zli.ch.lf.testplants.Data.Data;
import zli.ch.lf.testplants.Data.EntityKlasse;

//https://developer.android.com/reference/android/app/DatePickerDialog
//https://developer.android.com/reference/android/app/TimePickerDialog
public class CreateReminder extends AppCompatActivity implements View.OnClickListener {
    Button btn_time, btn_date, btn_done;
    EditText editext_message;
    String timeNotification;
    Data data;

    @Override
    public void onClick(View view)
    {
        if (view == btn_time)
        {
            selectTime();
        } else if (view == btn_date)
        {
            selectDate();
        } else {
            submit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);
        btn_time = findViewById(R.id.btn_time);
        btn_date = findViewById(R.id.btn_date);
        btn_done = findViewById(R.id.btn_done);
        editext_message = findViewById(R.id.editext_message);
        btn_time.setOnClickListener(this);
        btn_date.setOnClickListener(this);
        btn_done.setOnClickListener(this);
        data = Data.getDatabase(getApplicationContext());

    }

    private void submit()
    {
        String text = editext_message.getText().toString().trim();
        // Verhindern vonn leeren values
        // @quelle: https://stackoverflow.com/questions/3851507/android-widget-how-to-change-the-text-of-a-button
        if (text.isEmpty())
        {
            if (btn_time.getText().toString().equals("Zeit der giessung") || btn_date.getText().toString().equals("Datum der giessung"))
            {
                //Zeigt Meldung wenn kein Datum oder keine Zeit ausgewählt wurde
                Toast.makeText(this, "Error, bitte wähle eine gültige Zeit und ein gültiges Datum", Toast.LENGTH_SHORT).show();
            } else {
                EntityKlasse entityKlasse = new EntityKlasse();
                String value = (editext_message.getText().toString().trim());
                String date = (btn_date.getText().toString().trim());
                String time = (btn_time.getText().toString().trim());
                entityKlasse.setPlantdate(date);
                entityKlasse.setPlantname(value);
                entityKlasse.setPlanttime(time);
                data.ReminderDao().insertAll(entityKlasse);
                setAlarm(date, value, time);
            }
        }
    }

    private void selectDate()
    {
        Calendar calendar = Calendar.getInstance();
        int jahr = calendar.get(Calendar.YEAR);
        int tag = calendar.get(Calendar.DAY_OF_MONTH);
        int monat = calendar.get(Calendar.MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int jahr, int monat, int tag)
            {
                btn_date.setText(tag + "-" + (monat + 1) + "-" + jahr);
            }
        }, jahr, monat, tag);
        datePickerDialog.show();
    }

    private void selectTime()
    {
        Calendar calendar = Calendar.getInstance();
        int stunde = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int j, int j2)
            {
                timeNotification = j + ":" + j2;
                btn_time.setText(FormatTime(j, j2));
            }
        }, stunde, minute, false);
        timePickerDialog.show();

    }

    public String FormatTime(int stunde, int minute)
    {

        String zeit;
        zeit = "";
        String Minute;

        if (minute / 10 == 0) {
            Minute = "0" + minute;
        } else {
            Minute = "" + minute;
        }

        return zeit;
    }

    private void setAlarm(String platzhalter, String datum, String zeit)
    {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), ReminderNotifyChannel.class);
        intent.putExtra("reminder", platzhalter);
        intent.putExtra("zeit", zeit);
        intent.putExtra("datum", datum);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = datum + " " + timeNotification;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try
        {
            Date datum2 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, datum2.getTime(), pendingIntent);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        finish();

    }

    //https://developer.android.com/training/basics/intents/result#java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                editext_message.setText(text.get(0));
            }
        }

    }



}
