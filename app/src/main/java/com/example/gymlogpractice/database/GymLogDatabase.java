package com.example.gymlogpractice.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.gymlogpractice.database.entities.Gymlog;
import com.example.gymlogpractice.MainActivity;
import com.example.gymlogpractice.database.typeConverters.LocalDateTypeConverter;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@TypeConverters(LocalDateTypeConverter.class)
@Database(entities = {Gymlog.class}, version = 1, exportSchema = false)
public abstract class GymLogDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "GymLog_database";
    public static final String GYM_LOG_TABLE = "gymLogTable";

    private static volatile GymLogDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static GymLogDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (GymLogDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            GymLogDatabase.class,
                                    DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();



                }
            }

        }
        return INSTANCE;
    }
    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(MainActivity.TAG, "Database created");
           //good practice to put in dummy data
            ///
            ///
//            databaseWriteExecutor.execute(() -> {
//                GymLogDAO dao = INSTANCE.gymLogDAO();
//                dao.insert(new Gymlog("Bench Press", 225, 5));
//                dao.insert(new Gymlog("Squat", 315, 5));
//                dao.insert(new Gymlog("Deadlift", 405, 5));
//            });
        }
    };

    public  GymLogDAO gymLogDao() {
        return null;
    };
}
