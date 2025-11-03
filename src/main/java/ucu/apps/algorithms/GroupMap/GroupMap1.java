package ucu.apps.algorithms.GroupMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import ucu.apps.algorithms.Student;

public class GroupMap1 implements GroupMapStrategy {

    private HashMap<String, Group> groupMap = new HashMap<>();
    private TreeMap<Double, List<Group>> ratingTreeMap = new TreeMap<>();

    @Override
    public void add(Student student) {
        String groupName = student.getGroup();
        Group group = groupMap.get(groupName);
        double oldAvg = 0;

        if (group == null) {
            group = new Group(groupName, 0, 0);
            groupMap.put(groupName, group);
        } else {
            oldAvg = group.getAverage();

            ratingTreeMap.get(oldAvg).remove(group);
            if (ratingTreeMap.get(oldAvg).isEmpty()) ratingTreeMap.remove(oldAvg);
        }

        group.add(student.getRating());

        ratingTreeMap.computeIfAbsent(group.getAverage(), k -> new ArrayList<>()).add(group);
    }

    @Override
    public boolean remove(Student student) {
        String groupName = student.getGroup();
        Group group = groupMap.get(groupName);
        if (group == null) return false;

        double oldAvg = group.getAverage();
        ratingTreeMap.get(oldAvg).remove(group);
        if (ratingTreeMap.get(oldAvg).isEmpty()) ratingTreeMap.remove(oldAvg);

        group.remove(student.getRating());

        if (group.getSize() == 0) {
            groupMap.remove(groupName);
        } else {
            ratingTreeMap.computeIfAbsent(group.getAverage(), k -> new ArrayList<>()).add(group);
        }

        return true;
    }

    @Override
    public String getBestGroup() {
        if (ratingTreeMap.isEmpty()) return null;

        List<Group> topGroups = ratingTreeMap.lastEntry().getValue();
        if (topGroups.size() > 1) {
            topGroups.sort((a, b) -> Integer.compare(b.getSize(), a.getSize()));
        }

        return topGroups.get(0).getName();
    }
}