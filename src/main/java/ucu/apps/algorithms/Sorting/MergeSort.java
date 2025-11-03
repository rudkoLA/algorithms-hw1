package ucu.apps.algorithms.Sorting;

import java.util.Arrays;
import java.util.List;

import ucu.apps.algorithms.Student;

public class MergeSort implements SortingStrategy {
    @Override
    public List<Student> sortStudents(List<Student> students) {
        Student[] studentsCopy = students.toArray(new Student[0]);

        mergeSort(studentsCopy, 0, studentsCopy.length, new Student[studentsCopy.length]);

        return Arrays.asList(studentsCopy);
    }

    public static void mergeSort(Student[] arr, int left, int right, Student[] aux) {
        if (right - left < 2)
            return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid, aux);
        mergeSort(arr, mid, right, aux);
        merge(arr, left, mid, right, aux);
    }

    private static void merge(Student[] arr, int left, int mid, int right, Student[] aux) {
        int i = left;
        int j = mid;
        int k = left;

        while (i < mid && j < right) {
            int surnameCompareResult = arr[i].getSurname().compareTo(arr[j].getSurname());
            if (surnameCompareResult < 0
                    || (surnameCompareResult == 0 && arr[i].getName().compareTo(arr[j].getName()) <= 0)) {
                aux[k] = arr[i];
                ++k;
                ++i;
            } else {
                aux[k] = arr[j];
                ++k;
                ++j;
            }
        }

        while (i < mid) {
            aux[k] = arr[i];
            ++k;
            ++i;
        }

        while (j < right) {
            aux[k] = arr[j];
            ++k;
            ++j;
        }

        for (i = left; i < right; ++i) {
            arr[i] = aux[i];
        }
    }
}
