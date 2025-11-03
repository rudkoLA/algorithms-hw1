package ucu.apps.algorithms.GroupMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ucu.apps.algorithms.Student;

public interface GroupMapStrategy {
    public String bestGroupName = null;

    @AllArgsConstructor
    public class Group implements Comparable<Group> {
        @Getter
        public String name;
        @Getter
        public int size;
        public double sum;

        public void add(double rating) {
            sum += rating;
            size++;

        }

        public void remove(double rating) {
            sum -= rating;
            size--;

        }

        public double getAverage() {
            return sum / size;
        }

        public int compareTo(Group other) {
            return Double.compare(this.getAverage(), other.getAverage());
        }
    }

    public void add(Student student);

    public boolean remove(Student student);

    public String getBestGroup();
}
