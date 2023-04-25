package com.sanket;

import com.sanket.services.FileService;
import com.sanket.sort.merge.MergeSort;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // read the input files
        List<Integer> list1 = FileService.getListFromFile("files/in1.txt");
        List<Integer> list2 = FileService.getListFromFile("files/in2.txt");

        MergeSort<Integer> mergeSorter = new MergeSort<>();
        // sort 2 lists
        List<Integer> sortedList1 = mergeSorter.sort(list1, 0, list1.size() - 1);
        List<Integer> sortedList2 = mergeSorter.sort(list2, 0, list2.size() - 1);
        // combine 2 sorted lists
        List<Integer> result = mergeSorter.merge(sortedList1, sortedList2);
        System.out.println(result);

        // write the result to file
        FileService.writeListToFile("files/out.txt", result);
    }
}
