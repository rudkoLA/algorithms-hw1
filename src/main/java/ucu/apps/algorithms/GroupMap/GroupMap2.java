package ucu.apps.algorithms.GroupMap;

import java.util.HashMap;
import ucu.apps.algorithms.Student;

public class GroupMap2 implements GroupMapStrategy {
    private String bestGroupName = null;

    private HashMap<String, Group> groupMap = new HashMap<>();

    @Override
    public void add(Student student) {
        String groupName = student.getGroup();
        Group group = groupMap.computeIfAbsent(groupName, g -> new Group(g, 0, 0));
        group.add(student.getRating());

        if (bestGroupName == null ||
                groupName.equals(bestGroupName) ||
                group.getAverage() > groupMap.get(bestGroupName).getAverage()) {
            bestGroupName = groupName;
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

        if (groupName.equals(bestGroupName)) {
            bestGroupName = null;

            for (Group g : groupMap.values()) {
                if (bestGroupName == null || g.getAverage() > groupMap.get(bestGroupName).getAverage()) {
                    bestGroupName = g.getName();
                }
            }
        }

        return true;
    }

    @Override
    public String getBestGroup() {
        return bestGroupName;
    }
}