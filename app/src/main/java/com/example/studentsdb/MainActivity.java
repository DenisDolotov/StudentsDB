package com.example.studentsdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.studentsdb.Data.DatabaseHandler;
import com.example.studentsdb.Model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        fillDB(databaseHandler);
        List<Student> students = databaseHandler.getAllStudents();
        for (Student student : students) {
            Log.i("logCat",student.toString());
        }
    }

    private void fillDB(DatabaseHandler databaseHandler) {
        databaseHandler.addStudent(new Student("Sales", "Ivan", "Ivanov", "4.4"));
        databaseHandler.addStudent(new Student("Social Medicine", "Petr", "Petrov", "4.1"));
        databaseHandler.addStudent(new Student("Radiology", "Denis", "Super", "4.8"));
        databaseHandler.addStudent(new Student("Sales", "Oleg", "Sorov", "4.6"));
        databaseHandler.addStudent(new Student("Radiology", "Yulya", "Super", "4.7"));
    }
}
