package com.example.gymlogpractice.database;

import android.app.Application;
import android.util.Log;

import com.example.gymlogpractice.database.entities.Gymlog;
import com.example.gymlogpractice.MainActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GymLogRepository {
    private GymLogDAO gymLogDAO;

    private ArrayList<Gymlog> allLogs;

    public GymLogRepository(Application application){
        GymLogDatabase db = GymLogDatabase.getDatabase(application);
        this.gymLogDAO = db.gymLogDao();
        this.allLogs = (ArrayList<Gymlog>) this.gymLogDAO.getAllRecords();
    }

    public ArrayList<Gymlog> getAllLogs(){
        Future<ArrayList<Gymlog>> future = GymLogDatabase.databaseWriteExecutor.submit(new Callable<ArrayList<Gymlog>>() {
            @Override
            public ArrayList<Gymlog> call() throws Exception {
                return (ArrayList<Gymlog>) gymLogDAO.getAllRecords();
            }
        }

        );
        try{
            return future.get();
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
            Log.i(MainActivity.TAG, "Problem when getting all Gymlogs in the reposistory");

        }
        return null;

    }
    public void insertGymLog(Gymlog gymLog){
            GymLogDatabase.databaseWriteExecutor.execute(() ->
            {
                gymLogDAO.insert(gymLog);
            });
    }

}
