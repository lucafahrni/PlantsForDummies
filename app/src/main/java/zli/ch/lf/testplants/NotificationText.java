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
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_text);
        textView = findViewById(R.id.tv_message);
        Bundle bundle = getIntent().getExtras();
        textView.setText(bundle.getString("platzhalter"));

    }
}