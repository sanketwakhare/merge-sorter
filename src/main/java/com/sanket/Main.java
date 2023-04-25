package com.sanket;

import com.sanket.services.FileService;
import com.sanket.services.InputFileNameFilter;
import com.sanket.sort.merge.MergeSort;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        // read input files from given path
        File path = new File("files");
        String[] fileNames = path.list(InputFileNameFilter.getInstance());

        if (!Objects.isNull(fileNames)) {

            MergeSort<Integer> mergeSorter = new MergeSort<>();
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2;
            List<Integer> result;

            for (String file1 : fileNames) {
                list2 = FileService.getListFromFile("files/" + file1);

                // sort 2 lists
                List<Integer> sortedList1 = mergeSorter.sort(list1, list1.size() > 0 ? 0 : -1, list1.size() > 0 ? list1.size() - 1 : -1);
                List<Integer> sortedList2 = mergeSorter.sort(list2, list2.size() > 0 ? 0 : -1, list2.size() > 0 ? list2.size() - 1 : -1);

                // combine 2 sorted lists
                result = mergeSorter.merge(sortedList1, sortedList2);
                list1 = result;
            }

            result = list1;
            System.out.println("total elements: " + result.size());
            System.out.println(result);

            // write the result to file
            FileService.writeListToFile("files/out.txt", result);
        }
    }
}
