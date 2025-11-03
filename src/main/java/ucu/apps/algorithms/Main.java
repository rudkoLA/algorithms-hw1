package ucu.apps.algorithms;

public class Main {
    public static void main(String[] args) {
        DataBase data = new DataBase("./students.csv");

        data.getTopStudents();

    }
}
