package com.example.my1stapp;import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

class Workout {
    private String name;
    private int duration;

    public Workout(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return name + " (" + duration + " mins)";
    }
}

public class HomeActivity extends AppCompatActivity {

    private Spinner locationFilterSpinner, skillsFilterSpinner;
    private ListView userListView;
    private Button addWorkoutButton;
    private EditText workoutNameEditText, workoutDurationEditText;

    private ArrayAdapter<Workout> workoutAdapter;
    private List<Workout> workoutList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        locationFilterSpinner = findViewById(R.id.locationFilterSpinner);
        skillsFilterSpinner = findViewById(R.id.skillsFilterSpinner);
        userListView = findViewById(R.id.userListView);
        addWorkoutButton = findViewById(R.id.addWorkoutButton);
        workoutNameEditText = findViewById(R.id.workoutNameEditText);
        workoutDurationEditText = findViewById(R.id.workoutDurationEditText);

        setupFilterSpinners();
        setupUserListView();

        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWorkout();
            }
        });
    }

    private void setupFilterSpinners() {
        // Populate location and skills sets from user data
        // This part is already implemented in the original code
    }

    private void setupUserListView() {
        // Initialize adapter for the list of workouts
        workoutAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workoutList);
        userListView.setAdapter(workoutAdapter);
    }

    private void addWorkout() {
        String name = workoutNameEditText.getText().toString();
        String durationStr = workoutDurationEditText.getText().toString();

        if (name.isEmpty() || durationStr.isEmpty()) {
            // Show an alert if any field is empty
            showAlert("Please fill in all fields.");
            return;
        }

        int duration = Integer.parseInt(durationStr);
        // Add the new workout to the list
        workoutList.add(new Workout(name, duration));
        // Notify the adapter that the dataset has changed
        workoutAdapter.notifyDataSetChanged();
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}