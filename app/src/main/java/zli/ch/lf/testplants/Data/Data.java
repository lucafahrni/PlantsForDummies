package zli.ch.lf.testplants.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/*
 * @author Luca Fahrni
 * @version 21.01.2021
 *
 * Data
 *
 * @quelle: https://projectnotes.org/it-projects/simple-reminder-android-application-with-source-code/
 * @quelle: https://moodle-2.zli.ch/course/view.php?id=646  (Beispiel: SQLite Datenbank mit Rooms)
 */
@Database(entities = {EntityKlasse.class}, version = 1)
public abstract class Data extends RoomDatabase {
    private static Data INSTANCE;

    public abstract PlantReminderDAO PlantReminderDao();

    public static Data getDatabase(final Context context)
    {
        if (INSTANCE == null) {
            synchronized (Data.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Data.class, "product_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
