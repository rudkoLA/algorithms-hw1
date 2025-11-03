package ucu.apps.algorithms.TopStudents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import ucu.apps.algorithms.Student;

public class TopStudents1 implements TopStudentsStrategy {
    private PriorityQueue<Student> topStudents = new PriorityQueue<>();
    private PriorityQueue<Student> restStudents = new PriorityQueue<>(Collections.reverseOrder());

    @Override
    public void add(Student student) {
        if (topStudents.size() < 100) {
            topStudents.add(student);
        } else if (student.compareTo(topStudents.peek()) > 0) {
            restStudents.add(topStudents.poll());
            topStudents.add(student);
        } else {
            restStudents.add(student);
        }
    }

    @Override
    public boolean remove(Student student) {
        if (topStudents.remove(student)) {
            if (!restStudents.isEmpty()) {
                topStudents.add(restStudents.poll());
            }
            return true;
        }
        return restStudents.remove(student);
    }

    @Override
    public ArrayList<Student> getTopStudents() {
        ArrayList<Student> list = new ArrayList<>(topStudents);
        list.sort(Collections.reverseOrder());
        return list;
    }
}