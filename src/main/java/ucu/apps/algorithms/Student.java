package ucu.apps.algorithms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Comparable<Student> {
    private String name;
    private String surname;
    private String email;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private String group;
    private double rating;
    private String phoneNumber;

    @Override
    public int compareTo(Student other) {
        if (other == null) {
            throw new NullPointerException();
        }

        return Double.compare(this.rating, other.rating);
    }
}
