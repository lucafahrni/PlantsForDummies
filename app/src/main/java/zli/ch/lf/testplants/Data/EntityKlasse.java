package zli.ch.lf.testplants.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
 * @author Luca Fahrni
 * @version 21.01.2021
 * @class: ENtityKlasse
 *
 * Entitiy Klasse von PlantsforDummies
 * Beinhaltet getter und setter von name, datum und zeit
 */
@Entity(tableName = "newTable")
public class EntityKlasse {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "plantname")
    String plantname;
    @ColumnInfo(name = "plantdate")
    String plantdate;
    @ColumnInfo(name = "planttime")
    String planttime;


    public String getPlantname()
    {
        return plantname;
    }

    public void setPlantname(String plantname)
    {
        this.plantname = plantname;
    }

    public String getPlantdate()
    {
        return plantdate;
    }

    public void setPlantdate(String plantdate)
    {
        this.plantdate = plantdate;
    }

    public String getPlanttime()
    {
        return planttime;
    }

    public void setPlanttime(String planttime)
    {
        this.planttime = planttime;
    }
}

