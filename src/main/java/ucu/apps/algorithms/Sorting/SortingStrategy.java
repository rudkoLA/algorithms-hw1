package ucu.apps.algorithms.Sorting;

import java.util.List;

import ucu.apps.algorithms.Student;

public interface SortingStrategy {
    public List<Student> sortStudents(List<Student> students);
}
