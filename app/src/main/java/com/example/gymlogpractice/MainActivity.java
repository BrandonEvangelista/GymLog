package com.example.gymlogpractice;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gymlogpractice.database.GymLogRepository;
import com.example.gymlogpractice.database.entities.Gymlog;
import com.example.gymlogpractice.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private GymLogRepository repository;

    public static final String TAG = "GYMLOG";

    //global variables
    String mExercise = "";
    double mWeight = 0.0;
    int mReps = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = new GymLogRepository(getApplication());

        binding.logDisplayTextView.setMovementMethod(new ScrollingMovementMethod());

        binding.logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this, "IT WORKED", Toast.LENGTH_SHORT).show();
               // Log.d("MainActivity", "Log button clicked");
                getInformationFromDisplay();
                insertGymlogRecord();
                updateDisplay();
            }
        });

    }

    private void insertGymlogRecord(){
        Gymlog log = new Gymlog(mExercise, mWeight, mReps);
        repository.insertGymLog(log);

    }
    private void updateDisplay(){
        String currentInfo = binding.logDisplayTextView.getText().toString();
        // issues not binding data. lets log
        Log.d(TAG,"current info: "+ currentInfo);


        String newDisplay = String.format(Locale.US,"Exercise: %s%nWeight:%.2f%nReps: %d%n=-=-=-=-=-=%n%s", mExercise, mWeight, mReps, currentInfo);
        binding.logDisplayTextView.setText(newDisplay);;

    }

    private void getInformationFromDisplay() {
        /////////
        mExercise = binding.exerciseInputEditText.getText().toString();

        //watch our for number formatting. inorder to avoid crashing the app. set up TRY CATCH
        try {
            mWeight = Double.parseDouble(binding.weightInputEditText.getText().toString());
        }catch (NumberFormatException e){
            mWeight = 0.0;
            Log.d(TAG, "ERROR READING VALUE FROM WEIGHT EDIT TEXT: " + e.getMessage());
        }

        try {
            mReps = Integer.parseInt(binding.repInputEditText.getText().toString());
        }catch (NumberFormatException e){
            mWeight = 0.0;
            Log.d(TAG, "ERROR READING VALUE FROM REPS EDIT TEXT: " + e.getMessage());
        }

    }
}