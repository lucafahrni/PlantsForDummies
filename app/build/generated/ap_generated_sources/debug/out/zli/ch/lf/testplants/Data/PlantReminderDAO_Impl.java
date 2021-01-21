package zli.ch.lf.testplants.Data;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class PlantReminderDAO_Impl implements PlantReminderDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfEntityKlasse;

  public PlantReminderDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEntityKlasse = new EntityInsertionAdapter<EntityKlasse>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `newTable`(`id`,`plantname`,`plantdate`,`planttime`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EntityKlasse value) {
        stmt.bindLong(1, value.id);
        if (value.getPlantname() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPlantname());
        }
        if (value.getPlantdate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPlantdate());
        }
        if (value.getPlanttime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPlanttime());
        }
      }
    };
  }

  @Override
  public void insertAll(EntityKlasse entityKlasse) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfEntityKlasse.insert(entityKlasse);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<EntityKlasse> getAllData() {
    final String _sql = "SELECT * FROM newTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfPlantname = _cursor.getColumnIndexOrThrow("plantname");
      final int _cursorIndexOfPlantdate = _cursor.getColumnIndexOrThrow("plantdate");
      final int _cursorIndexOfPlanttime = _cursor.getColumnIndexOrThrow("planttime");
      final List<EntityKlasse> _result = new ArrayList<EntityKlasse>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final EntityKlasse _item;
        _item = new EntityKlasse();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        final String _tmpPlantname;
        _tmpPlantname = _cursor.getString(_cursorIndexOfPlantname);
        _item.setPlantname(_tmpPlantname);
        final String _tmpPlantdate;
        _tmpPlantdate = _cursor.getString(_cursorIndexOfPlantdate);
        _item.setPlantdate(_tmpPlantdate);
        final String _tmpPlanttime;
        _tmpPlanttime = _cursor.getString(_cursorIndexOfPlanttime);
        _item.setPlanttime(_tmpPlanttime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
