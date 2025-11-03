package ucu.apps.algorithms.EmailMap;

import java.util.HashMap;

import ucu.apps.algorithms.Student;

public class EmailMap1 implements EmailMapStrategy {
    private HashMap<String, Student> emailMap = new HashMap<String, Student>();

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
