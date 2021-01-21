package zli.ch.lf.testplants.Data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class Data_Impl extends Data {
  private volatile PlantReminderDAO _Plant_Plant_reminderDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `newTable` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `plantname` TEXT, `plantdate` TEXT, `planttime` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"a13da5b2d5d76226de724b65162a5ef4\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `newTable`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsNewTable = new HashMap<String, TableInfo.Column>(4);
        _columnsNewTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsNewTable.put("plantname", new TableInfo.Column("plantname", "TEXT", false, 0));
        _columnsNewTable.put("plantdate", new TableInfo.Column("plantdate", "TEXT", false, 0));
        _columnsNewTable.put("planttime", new TableInfo.Column("planttime", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNewTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNewTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNewTable = new TableInfo("newTable", _columnsNewTable, _foreignKeysNewTable, _indicesNewTable);
        final TableInfo _existingNewTable = TableInfo.read(_db, "newTable");
        if (! _infoNewTable.equals(_existingNewTable)) {
          throw new IllegalStateException("Migration didn't properly handle newTable(zli.ch.lf.testplants.Data.EntityKlasse).\n"
                  + " Expected:\n" + _infoNewTable + "\n"
                  + " Found:\n" + _existingNewTable);
        }
      }
    }, "a13da5b2d5d76226de724b65162a5ef4", "be6b594e3dbff20cb26f8b8b5a01cd2c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "newTable");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `newTable`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public PlantReminderDAO ReminderDao() {
    if (_Plant_Plant_reminderDAO != null) {
      return _Plant_Plant_reminderDAO;
    } else {
      synchronized(this) {
        if(_Plant_Plant_reminderDAO == null) {
          _Plant_Plant_reminderDAO = new PlantReminderDAO_Impl(this);
        }
        return _Plant_Plant_reminderDAO;
      }
    }
  }
}
