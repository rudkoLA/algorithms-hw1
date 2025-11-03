package ucu.apps.algorithms;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import lombok.NoArgsConstructor;
import ucu.apps.algorithms.EmailMap.EmailMap1;
import ucu.apps.algorithms.EmailMap.EmailMapStrategy;
import ucu.apps.algorithms.GroupMap.GroupMap2;
import ucu.apps.algorithms.GroupMap.GroupMapStrategy;
import ucu.apps.algorithms.Sorting.DefaultSorting;
import ucu.apps.algorithms.Sorting.SortingStrategy;
import ucu.apps.algorithms.TopStudents.TopStudents3;
import ucu.apps.algorithms.TopStudents.TopStudentsStrategy;

// Варіант завдання 2    A    B    C     Варіант завдання 3
//           V5          50   10   5              S4

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class DataBase {
    public ArrayList<Student> students = new ArrayList<Student>();

    public TopStudentsStrategy topStudents = new TopStudents3();
    public EmailMapStrategy emailMap = new EmailMap1();
    public GroupMapStrategy groupMap = new GroupMap2();

    public SortingStrategy studentSorter = new DefaultSorting();

    DataBase(String fileName) {
        this(fileName, -1);
    }

    DataBase(String fileName, int size) {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] line;

            reader.readNext();

            int count = 0;

            while ((line = reader.readNext()) != null) {
                if (count++ > size && size != -1) {
                    break;
                }

                String name = line[0];
                String surname = line[1];
                String email = line[2];
                int birthYear = Integer.parseInt(line[3]);
                int birthMonth = Integer.parseInt(line[4]);
                int birthDay = Integer.parseInt(line[5]);
                String group = line[6];
                double rating = Double.parseDouble(line[7]);
                String phoneNumber = line[8];

                Student student = new Student(name, surname, email, birthYear, birthMonth, birthDay, group, rating,
                        phoneNumber);

                addStudent(student);
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        topStudents.add(student);
        emailMap.add(student);
        groupMap.add(student);
    }

    public boolean removeStudent(Student student) {
        if (topStudents.remove(student)) {
            emailMap.remove(student);
            groupMap.remove(student);
            return true;
        }
        return false;
    }

    public List<Student> getTopStudents() {
        return topStudents.getTopStudents();
    }

    public boolean updateRating(String email, double newRating) {
        Student student = emailMap.get(email);

        if (student == null) {
            return false;
        }

        topStudents.remove(student);
        groupMap.remove(student);

        student.setRating(newRating);

        topStudents.add(student);
        groupMap.add(student);

        return true;
    }

    public String getBestGroup() {
        return groupMap.getBestGroup();
    }

    public void changeTopStudentsStrategy(TopStudentsStrategy strategy) {
        this.topStudents = strategy;
        for (Student student : students) {
            topStudents.add(student);
        }
    }

    public void changeEmailMapStrategy(EmailMapStrategy strategy) {
        this.emailMap = strategy;
        for (Student student : students) {
            emailMap.add(student);
        }
    }

    public void changeGroupMapStrategy(GroupMapStrategy strategy) {
        this.groupMap = strategy;
        for (Student student : students) {
            groupMap.add(student);
        }
    }

    public List<Student> sortStudents() {
        return studentSorter.sortStudents(students);
    }
}