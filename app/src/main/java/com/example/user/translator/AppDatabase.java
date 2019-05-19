package com.example.user.translator;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Update;
import android.content.Context;

import java.util.List;

@Dao
interface TranslateDataDao {

    @Query("SELECT * FROM TranslateData")
    List<TranslateData> getAll();

    @Query("SELECT * FROM TranslateData WHERE id = :id")
    TranslateData getById(int id);

    @Insert
    void insert(TranslateData translateData);

    @Update
    void update(TranslateData translateData);

    @Delete
    void delete(TranslateData translateData);
}


@Database(entities = {TranslateData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TranslateDataDao translateDataDao();
    public static AppDatabase create(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "database.db").build();
    }
}

@Entity
class TranslateData {
    @PrimaryKey
    int id;
    String lang;
    String text;
    String translatedText;

    public TranslateData(int id, String lang, String text, String translatedText) {
        this.id = id;
        this.lang = lang;
        this.text = text;
        this.translatedText = translatedText;
    }
}


