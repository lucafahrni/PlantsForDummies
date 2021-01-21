package zli.ch.lf.testplants.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/*
 * @author Luca Fahrni
 * @version 21.01.2021
 * @class: ReminderDAO
 *
 * Data Access Object Klasse von PlantsForDummies
 *
 * @quelle: https://projectnotes.org/it-projects/simple-reminder-android-application-with-source-code/
 */
@Dao
public interface ReminderDAO {

    @Query("SELECT * FROM newTable")
    List<EntityKlasse> getAllData();

    @Insert
    void insertAll
            ( EntityKlasse entityKlasse
    );


}

