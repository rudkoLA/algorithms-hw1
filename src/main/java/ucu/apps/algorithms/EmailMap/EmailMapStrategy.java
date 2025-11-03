package ucu.apps.algorithms.EmailMap;

import ucu.apps.algorithms.Student;

public interface EmailMapStrategy {
    public void add(Student student);

    public boolean remove(Student student);

    public Student get(String email);
}
