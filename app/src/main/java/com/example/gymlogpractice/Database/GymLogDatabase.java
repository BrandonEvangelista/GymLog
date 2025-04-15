package com.example.gymlogpractice.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.gymlogpractice.Database.entities.Gymlog;

@Database(entities = {Gymlog.class}, version = 1, exportSchema = false)
public abstract class GymLogDatabase extends RoomDatabase {
    public static final String gymLogTable = "gymLogTable";
}
