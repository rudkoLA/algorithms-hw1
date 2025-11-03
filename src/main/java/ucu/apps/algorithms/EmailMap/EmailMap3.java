package ucu.apps.algorithms.EmailMap;

import java.util.TreeMap;

import ucu.apps.algorithms.Student;

public class EmailMap3 implements EmailMapStrategy {
    private TreeMap<String, Student> emailMap = new TreeMap<String, Student>();

    @Override
    public void add(Student student) {
        emailMap.put(student.getEmail(), student);
    }

    @Override
    public boolean remove(Student student) {
        return emailMap.remove(student.getEmail()) != null;
    }

    @Override
    public Student get(String email) {
        return emailMap.get(email);
    }
}
