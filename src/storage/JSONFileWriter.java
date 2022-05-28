package storage;

import model.Course;
import model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONFileWriter {

    public void write(Student student, double average) {

        JSONObject jsonStudent = new JSONObject();
        jsonStudent.put("id", student.getID().toString());
        jsonStudent.put("full-name", student.getFullName());

        JSONArray courseArray = new JSONArray();

        for (Course course : student.getCourses()) {
            JSONObject jsonCourse = new JSONObject();
            jsonCourse.put("name", course.getName());
            jsonCourse.put("unit", course.getUnit());
            jsonCourse.put("score", course.getScore());

            courseArray.add(jsonCourse);
        }

        jsonStudent.put("courses", courseArray);
        jsonStudent.put("average", average);

        try (FileWriter file = new FileWriter(String.format("results/%s.json", student.getID()))) {
            file.write(jsonStudent.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
