package zli.ch.lf.testplants.Data;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Dao;


import java.util.List;

/*
 * @author Luca Fahrni
 * @version 21.01.2021
 * @class: ReminderDAO
 *
 * Data Access Object Klasse von PlantsForDummies
 */
@Dao
public interface PlantReminderDAO {

    @Query("SELECT * FROM newTable")
    List<EntityKlasse> getAllData();

    @Insert
    void insertAll
            (EntityKlasse entityKlasse
            );


}

