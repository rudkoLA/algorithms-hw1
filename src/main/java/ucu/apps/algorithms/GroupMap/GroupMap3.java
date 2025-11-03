package ucu.apps.algorithms.GroupMap;

import java.util.Map;
import java.util.TreeMap;

import ucu.apps.algorithms.Student;

public class GroupMap3 implements GroupMapStrategy {
    private TreeMap<String, Group> groupMap = new TreeMap<String, Group>();

    @Override
    public void add(Student student) {
        String groupName = student.getGroup();
        Group group = groupMap.get(groupName);

        if (group == null) {
            groupMap.put(groupName, new Group(groupName, 1, student.getRating()));
        } else {
            group.add(student.getRating());
        }
    }

    @Override
    public boolean remove(Student student) {
        String groupName = student.getGroup();
        Group group = groupMap.get(groupName);
        if (group == null)
            return false;

        group.remove(student.getRating());
        if (group.getSize() == 0)
            groupMap.remove(groupName);

        return true;
    }

    @Override
    public String getBestGroup() {
        if (groupMap.isEmpty()) {
            return null;
        }

        String bestGroup = null;
        double bestAvg = Double.NEGATIVE_INFINITY;

        for (Map.Entry<String, Group> entry : groupMap.entrySet()) {
            double avg = entry.getValue().getAverage();
            if (avg > bestAvg) {
                bestAvg = avg;
                bestGroup = entry.getKey();
            }
        }

        return bestGroup;
    }
}