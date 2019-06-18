package com.example.studentsdb.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.studentsdb.Model.Student;
import com.example.studentsdb.Utils.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY, " +
                Util.KEY_FACULTY + " TEXT, " +
                Util.KEY_LAST_NAME + " TEXT, " +
                Util.KEY_FIRST_NAME + " TEXT, " +
                Util.KEY_GRADE_POINT_AVERAGE + " TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }

    public long addStudent(Student student) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            ContentValues contentValues = getContentValuesFromStudent(student);
            return db.insert(Util.TABLE_NAME, null, contentValues);
        }
    }

    public Student getStudent(int id) {
        try (SQLiteDatabase db = getReadableDatabase()) {
            Cursor cursor = db.query(
                    Util.TABLE_NAME,
                    new String[]{
                            Util.KEY_ID,
                            Util.KEY_FACULTY,
                            Util.KEY_LAST_NAME,
                            Util.KEY_FIRST_NAME,
                            Util.KEY_GRADE_POINT_AVERAGE},
                    Util.KEY_ID + "=?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return getStudentFromCursor(cursor);
        }
    }

    public int updateStudent(Student student) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            ContentValues contentValues = getContentValuesFromStudent(student);
            return db.update(Util.TABLE_NAME,
                    contentValues,
                    Util.KEY_ID + "=?",
                    new String[]{String.valueOf(student.getId())});
        }
    }

    public void deleteStudent(Student student) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[]{String.valueOf(student.getId())});
        }
    }

    public int getStudentCount() {
        try (SQLiteDatabase db = getReadableDatabase()) {
            String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
            Cursor cursor = db.rawQuery(countQuery, null);
            return cursor.getCount();
        }
    }
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        try(SQLiteDatabase db = getReadableDatabase()) {
            String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
            Cursor cursor = db.rawQuery(countQuery, null);
            if (cursor.moveToFirst()){
                do {
                    students.add(getStudentFromCursor(cursor));
                }while (cursor.moveToNext());
            }
            cursor.getCount();
        }
        return students;
    }

    private ContentValues getContentValuesFromStudent(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_FACULTY, student.getFaculty());
        contentValues.put(Util.KEY_LAST_NAME, student.getLastName());
        contentValues.put(Util.KEY_FIRST_NAME, student.getFirstName());
        contentValues.put(Util.KEY_GRADE_POINT_AVERAGE, student.getGradePointAverage());
        return contentValues;
    }

    private Student getStudentFromCursor(Cursor cursor) {
        return new Student(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
    }
}
