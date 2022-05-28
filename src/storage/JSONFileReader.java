package storage;

import model.Course;
import model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class JSONFileReader {

    JSONParser parser = new JSONParser();
    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> read() {
        try (FileReader reader = new FileReader("resources/list.json")) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            jsonArray.forEach(student -> parseStudent((JSONObject) student));

            return students;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void parseStudent(JSONObject jsonStudent) {

        UUID ID = UUID.fromString((String) jsonStudent.get("id"));
        String fullName = (String) jsonStudent.get("full-name");

        Student student = new Student(ID, fullName);

        JSONArray courseArray = (JSONArray) jsonStudent.get("courses");

        courseArray.forEach(course -> student.addCourse(parseCourse((JSONObject) course)));

        students.add(student);
    }

    private Course parseCourse(JSONObject jsonCourse) {
        String name = (String) jsonCourse.get("name");
        int unit = (int)(long) jsonCourse.get("unit");
        double score = (double) jsonCourse.get("score");

        return new Course(name, unit, score);
    }
}
