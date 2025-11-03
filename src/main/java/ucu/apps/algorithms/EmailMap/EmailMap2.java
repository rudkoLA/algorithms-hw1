package ucu.apps.algorithms.EmailMap;

import java.util.concurrent.ConcurrentHashMap;

import ucu.apps.algorithms.Student;

public class EmailMap2 implements EmailMapStrategy {
    private ConcurrentHashMap<String, Student> emailMap = new ConcurrentHashMap<String, Student>();

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
