package com.example.studentsdb.Model;

public class Student {
    private int id;
    private String faculty;
    private String lastName;
    private String firstName;
    private String gradePointAverage;


    @Override
    public String toString() {
        return "Student: "+lastName+" "+firstName+", Faculty: "+faculty+", GPA: "+gradePointAverage;
    }

    public Student() {
    }

    public Student(String faculty, String lastName, String firstName) {
        this.faculty = faculty;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Student(String faculty, String lastName, String firstName, String gradePointAverage) {
        this.faculty = faculty;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gradePointAverage = gradePointAverage;
    }

    public Student(int id, String faculty, String lastName, String firstName, String gradePointAverage) {
        this.id = id;
        this.faculty = faculty;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gradePointAverage = gradePointAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(String gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }
}
