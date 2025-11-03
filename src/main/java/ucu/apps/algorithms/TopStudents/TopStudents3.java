package ucu.apps.algorithms.TopStudents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import ucu.apps.algorithms.Student;

public class TopStudents3 implements TopStudentsStrategy {
    private PriorityQueue<Student> students = new PriorityQueue<Student>();

    @Override
    public void add(Student student) {
        students.add(student);
    }

    @Override
    public boolean remove(Student student) {
        return students.remove(student);
    }

    @Override
    public ArrayList<Student> getTopStudents() {
        ArrayList<Student> list = new ArrayList<>(students);

        list.sort(Collections.reverseOrder());

        return new ArrayList<>(list.subList(0, Math.min(100, list.size())));
    }
}