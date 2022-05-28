package storage;

import model.Student;

import java.util.ArrayList;

public class FileWorker {

    JSONFileReader reader = new JSONFileReader();
    JSONFileWriter writer = new JSONFileWriter();

    public void store(Student student, double average) {
        writer.write(student, average);
    }

    public ArrayList<Student> read() {
        return reader.read();
    }
}
