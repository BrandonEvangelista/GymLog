package com.example.gymlogpractice.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gymlogpractice.database.entities.Gymlog;

import java.util.ArrayList;
import java.util.List;

//data access object
//actions to be performed on the database
@Dao
public interface GymLogDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Gymlog gymlog);

    @Query("Select * from " + GymLogDatabase.GYM_LOG_TABLE)
    List<Gymlog> getAllRecords();


}
