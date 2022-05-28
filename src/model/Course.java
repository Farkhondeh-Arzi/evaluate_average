package model;

public class Course {

    private final String name;
    private final int unit;
    private final double score;

    public Course(String name, int unit, double score) {
        this.name = name;
        this.unit = unit;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public int getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }
}
