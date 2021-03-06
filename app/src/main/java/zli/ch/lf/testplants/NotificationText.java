package zli.ch.lf.testplants;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/*
 * @author Luca Fahrni
 * @version 21.01.2021
 *
 * NotificationText Klasse von Plants for Dummies
 *
 * OnCreate Methode der Textview Notification
 *
 * @quelle: https://projectnotes.org/it-projects/simple-reminder-android-application-with-source-code/
            https://moodle-2.zli.ch/course/view.php?id=646  (Beispiel: SQLite Datenbank mit Rooms)
 */
public class NotificationText extends AppCompatActivity {
    TextView textViewPlatzhalter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_platzhalter);
        textViewPlatzhalter = findViewById(R.id.tV_Platzhalter);
        Bundle bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        textViewPlatzhalter.setText(bundle.getString("platzhalter"));

    }
}