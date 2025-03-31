package com.example.mytaskapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private EditText titleInput, descriptionInput;
    private Button saveButton;
    private TaskDatabase db;
    private Task taskToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        db = new TaskDatabase(this);
        titleInput = findViewById(R.id.etTitle);
        descriptionInput = findViewById(R.id.etDescription);
        saveButton = findViewById(R.id.btnSave);

        // Check if we're editing an existing task
        if (getIntent().hasExtra("task_id")) {
            int taskId = getIntent().getIntExtra("task_id", -1);
            if (taskId != -1) {
                taskToEdit = db.getAllTasks().stream()
                    .filter(t -> t.getId() == taskId)
                    .findFirst()
                    .orElse(null);
                
                if (taskToEdit != null) {
                    titleInput.setText(taskToEdit.getTitle());
                    descriptionInput.setText(taskToEdit.getDescription());
                    setTitle("Edit Task");
                }
            }
        }

        saveButton.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        String title = titleInput.getText().toString().trim();
        String description = descriptionInput.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (taskToEdit != null) {
            // Update existing task
            taskToEdit.setTitle(title);
            taskToEdit.setDescription(description);
            db.updateTask(taskToEdit);
            Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
        } else {
            // Add new task
            Task newTask = new Task(0, title, description, null);
            db.addTask(newTask);
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}