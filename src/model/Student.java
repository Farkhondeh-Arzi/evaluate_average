package model;

import java.util.ArrayList;
import java.util.UUID;

public class Student {

    UUID ID;
    String fullName;
    ArrayList<Course> courses;

    public Student(UUID ID, String fullName) {
        this.ID = ID;
        this.fullName = fullName;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getFullName() {
        return fullName;
    }

    public UUID getID() {
        return ID;
    }
}
