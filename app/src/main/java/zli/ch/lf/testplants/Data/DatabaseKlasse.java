package zli.ch.lf.testplants.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {EntityKlasse.class}, version = 1)
public abstract class DatabaseKlasse extends RoomDatabase {
    private static DatabaseKlasse INSTANCE;

    public abstract ReminderDAO ReminderDao();

    public static DatabaseKlasse getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseKlasse.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseKlasse.class,
                                    "product_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
