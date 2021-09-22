package models;

import lombok.Data;

@Data
public class Course {
    private String name;

    private int duration;

    public Course(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("COURSE: \"%s\" DURATION: %s (hrs)\n", name, duration);
    }
}
