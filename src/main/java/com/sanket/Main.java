package com.sanket;

import com.sanket.services.FileReaderService;
import com.sanket.services.FileWriterService;
import com.sanket.services.InputFileNameFilter;
import com.sanket.sort.merge.MergeSort;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // read input files from given path
        File path = new File("files");
        String[] fileNames = path.list(InputFileNameFilter.getInstance());

        if (!Objects.isNull(fileNames)) {

            List<Integer> list1;
            List<Integer> list2 = null;
            List<Integer> result;

            // initialize executor service with fixed thread pool
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            for (String file1 : fileNames) {

                // maintain separate thread for reading input files
                FileReaderService fileReaderService = new FileReaderService("files/" + file1);
                Future<List<Integer>> futureList1 = executorService.submit(fileReaderService);
                list1 = futureList1.get();

                MergeSort<Integer> mergeSorter1 = new MergeSort<>(list1);
                MergeSort<Integer> mergeSorter2 = new MergeSort<>(list2);

                // wait for future object/promise
                Future<List<Integer>> leftSortedFuture = executorService.submit(mergeSorter1);
                Future<List<Integer>> rightSortedFuture = executorService.submit(mergeSorter2);

                // sort 2 lists
                List<Integer> sortedList1 = leftSortedFuture.get();
                List<Integer> sortedList2 = rightSortedFuture.get();

                // combine 2 sorted lists
                MergeSort<Integer> merger = new MergeSort<>(null);
                result = merger.merge(sortedList1, sortedList2);
                list2 = result;
            }

            result = list2;
            System.out.println("total elements: " + result.size());
            System.out.println(result);

            // maintain separate thread for writing result to output file
            // write the result to file
            FileWriterService fileWriterService = new FileWriterService("files/out.txt", result);
            Future<?> fileWriterFuture = executorService.submit(fileWriterService);
            fileWriterFuture.get();

            // shutdown the executor service
            executorService.shutdown();
        }
    }
}
