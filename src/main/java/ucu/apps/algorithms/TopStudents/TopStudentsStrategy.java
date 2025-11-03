package ucu.apps.algorithms.TopStudents;

import java.util.ArrayList;

import ucu.apps.algorithms.Student;

public interface TopStudentsStrategy {
    public void add(Student student);

    public boolean remove(Student student);

    public ArrayList<Student> getTopStudents();
}
