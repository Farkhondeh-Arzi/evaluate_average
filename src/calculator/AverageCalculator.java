package calculator;

import model.Course;
import model.Student;
import storage.FileWorker;

import java.util.ArrayList;
import java.util.Date;

public class AverageCalculator {

    private final FileWorker fileWorker = new FileWorker();
    private final ArrayList<Student> students = fileWorker.read();
    private final int n = students.size();

    public void parallelCalculating() {
        Date start = new Date();

        Thread firstThread = new Thread(() -> partCalculating(0, n / 4));
        Thread secondThread = new Thread(() -> partCalculating(n / 4, n / 2));
        Thread thirdThread = new Thread(() -> partCalculating(n / 2, 3 * n / 4));
        Thread forthThread = new Thread(() -> partCalculating(3 * n / 2, n));

        firstThread.start();
        secondThread.start();
        thirdThread.start();
        forthThread.start();

        try {
            firstThread.join();
            secondThread.join();
            thirdThread.join();
            forthThread.join();

            Date end = new Date();

            System.out.println(getDateDiff(start, end) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void partCalculating(int i, int j) {
        for (int k = i; k < j; k++) {
            Student student = students.get(k);
            fileWorker.store(student, getAverage(student.getCourses()));
        }
    }

    private double getAverage(ArrayList<Course> courses) {

        double totalSum = 0;
        double unitSum = 0;

        for (Course course : courses) {
            int unit = course.getUnit();
            double score = course.getScore();

            unitSum += unit;
            totalSum += getCourseResult(unit, score);
        }

        return totalSum / unitSum;
    }

    private double getCourseResult(int unit, double score) {
        return unit * score;
    }

    private long getDateDiff(Date start, Date end) {
        return end.getTime() - start.getTime();
    }
}
