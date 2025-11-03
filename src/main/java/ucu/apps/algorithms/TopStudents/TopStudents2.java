package ucu.apps.algorithms.TopStudents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import ucu.apps.algorithms.Student;

public class TopStudents2 implements TopStudentsStrategy {
    private TreeSet<Student> students = new TreeSet<Student>();

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
        ArrayList<Student> list = new ArrayList<>();
        Iterator<Student> studentIterator = students.descendingIterator();

        int count = 0;

        while (studentIterator.hasNext() && count < 100) {
            list.add(studentIterator.next());
            ++count;
        }

        return list;
    }
}