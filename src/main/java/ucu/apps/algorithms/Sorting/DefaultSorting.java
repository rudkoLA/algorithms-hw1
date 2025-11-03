package ucu.apps.algorithms.Sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ucu.apps.algorithms.Student;

public class DefaultSorting implements SortingStrategy {
    @Override
    public List<Student> sortStudents(List<Student> students) {
        List<Student> sortedStudents = new ArrayList<>(students);

        sortedStudents.sort(Comparator.comparing(Student::getSurname).thenComparing(Student::getName));

        return sortedStudents;
    }
}
