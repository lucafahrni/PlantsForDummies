package zli.ch.lf.testplants.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReminderDAO {

    @Insert
    void insertAll(EntityKlasse entityKlasse);

    @Query("SELECT * FROM myTable")
    List<EntityKlasse> getAllData();
}

